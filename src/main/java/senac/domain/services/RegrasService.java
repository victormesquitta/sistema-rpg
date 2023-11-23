package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.requests.RegrasRequestDTO;
import senac.domain.dtos.responses.RegrasResponseDTO;
import senac.domain.mappers.ParticipanteMapper;
import senac.domain.mappers.RegrasMapper;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.RegrasModel;
import senac.domain.repositories.RegrasRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegrasService {
    private final RegrasRepository regrasRepository;
    private final RegrasMapper regrasMapper;
    private final ParticipanteMapper participanteMapper;
    private final ParticipanteService participanteService;
    @Autowired
    public RegrasService(RegrasRepository regrasRepository, RegrasMapper regrasMapper, ParticipanteMapper participanteMapper, ParticipanteService participanteService) {
        this.regrasRepository = regrasRepository;
        this.regrasMapper = regrasMapper;
        this.participanteMapper = participanteMapper;
        this.participanteService = participanteService;
    }


    // Obter todas as Regras
    public List<RegrasResponseDTO> listarRegrasResponse() {
        List<RegrasModel> regras = regrasRepository.findAll();
        if(regras.isEmpty()){
            throw new EntityNotFoundException("Nenhuma regra cadastrada.");
        }
        return regras.stream()
                .map(regrasMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<RegrasRequestDTO> listarRegrasRequest() {
        List<RegrasModel> regras = regrasRepository.findAll();
        if(regras.isEmpty()){
            throw new EntityNotFoundException("Nenhuma regra cadastrada.");
        }
        return regras.stream()
                .map(regrasMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    // Obter Regras por ID
    public RegrasResponseDTO obterRegrasPorIdResponse(Integer codRegras) {
        listarRegrasResponse();
        Optional<RegrasModel> regrasOptional =  regrasRepository.findById(codRegras);
        regrasOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma regra encontrado para o ID fornecido."));
        return regrasOptional.map(regrasMapper::toResponseDto).orElse(null);
    }
    public RegrasRequestDTO obterRegrasPorIdResquest(Integer codRegras) {
        listarRegrasResponse();
        Optional<RegrasModel> regrasOptional =  regrasRepository.findById(codRegras);
        regrasOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma regra encontrado para o ID fornecido."));
        return regrasOptional.map(regrasMapper::toRequestDto).orElse(null);
    }

    public RegrasModel obterRegrasModelPorId(Integer codRegras){
        listarRegrasResponse();
        Optional<RegrasModel> regrasOptional =  regrasRepository.findById(codRegras);
        regrasOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma regra encontrado para o ID fornecido."));
        RegrasRequestDTO regrasRequestDTO = regrasOptional.map(regrasMapper::toRequestDto).orElse(null);
        RegrasModel regrasModel = regrasMapper.toEntity(regrasRequestDTO);
        regrasModel.setParticipanteModel(participanteService.obterParticipanteModelPorId(regrasRequestDTO.getCodParticipante()));
        return regrasModel;
    }


    public void criarRegras(RegrasRequestDTO regrasRequestDTO) {
        //ver como fazer toda a validação toda jwt
        Integer codParticipante = regrasRequestDTO.getCodParticipante();

        ParticipanteModel participanteExistente = participanteService.obterParticipanteModelPorId(codParticipante);

        if(!(participanteExistente.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para adicionar regras.");
        }

        RegrasModel regrasModel = regrasMapper.toEntity(regrasRequestDTO);
        regrasModel.setParticipanteModel(participanteExistente);
        regrasRepository.save(regrasModel);
    }

    public void atualizarRegras(Integer codRegras, RegrasRequestDTO regrasRequestDTO) {

        RegrasResponseDTO regrasResponseDTO = obterRegrasPorIdResponse(codRegras);
        ParticipanteModel participanteModel = participanteService.obterParticipanteModelPorId(regrasRequestDTO.getCodParticipante());

        if(!(participanteModel.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para atualizar regras.");
        }
        else if(!(regrasResponseDTO.getCodParticipante().equals(regrasRequestDTO.getCodParticipante()))){
            throw new RuntimeException("Não é possível alterar por quem foi escrita as regras.");
        }

        RegrasModel regrasModel = regrasMapper.toEntity(regrasRequestDTO);
        regrasModel.setParticipanteModel(participanteModel);
        regrasModel.setCodRegras(codRegras);

        regrasRepository.save(regrasModel);
    }

    public void excluirRegras(Integer codRegras) {
        obterRegrasPorIdResquest(codRegras);
        regrasRepository.deleteById(codRegras);
    }
}