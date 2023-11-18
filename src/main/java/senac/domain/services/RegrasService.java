package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.ParticipanteDTO;
import senac.domain.dtos.ambos.RegrasDTO;
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
    public List<RegrasDTO> listarRegras() {
        List<RegrasModel> regras = regrasRepository.findAll();
        if(regras.isEmpty()){
            throw new EntityNotFoundException("Nenhuma regra cadastrada.");
        }
        return regras.stream()
                .map(regrasMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    // Obter Regras por ID
    public RegrasDTO obterRegrasPorId(Integer codRegras) {
        listarRegras();
        Optional<RegrasModel> regrasOptional =  regrasRepository.findById(codRegras);
        regrasOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma regra encontrado para o ID fornecido."));
        return regrasOptional.map(regrasMapper::toRequestDto).orElse(null);
    }

    public void criarRegras(RegrasDTO regrasDto) {
        //ver como fazer toda a validação toda jwt
        Integer codParticipante = regrasDto.getCodParticipante();

        ParticipanteDTO participanteExistente = participanteService.obterParticipantePorId(codParticipante);

        if(!(participanteExistente.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para adicionar regras.");
        }

        RegrasModel regrasModel = regrasMapper.toEntity(regrasDto);
        regrasModel.setParticipanteModel(participanteMapper.toEntity(participanteExistente));
        regrasRepository.save(regrasModel);
    }

    public void atualizarRegras(Integer codRegras, RegrasDTO regrasDto) {

        RegrasDTO regrasExistente = obterRegrasPorId(codRegras);
        ParticipanteModel participanteExistente = participanteMapper.toEntity(participanteService.obterParticipantePorId(regrasExistente.getCodParticipante()));

        if(!(participanteExistente.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para atualizar regras.");
        }
        else if(!(regrasDto.getCodParticipante().equals(regrasExistente.getCodParticipante()))){
            throw new RuntimeException("Não é possível alterar por quem foi escrita as regras.");
        }
        RegrasModel regrasAtualizada = regrasMapper.toEntity(regrasDto);

        regrasAtualizada.setCodRegras(codRegras);
        regrasAtualizada.setParticipanteModel(participanteExistente);
        regrasRepository.save(regrasAtualizada);
    }

    public void excluirRegras(Integer codRegras) {
        obterRegrasPorId(codRegras);
        regrasRepository.deleteById(codRegras);
    }
}