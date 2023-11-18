package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.ParticipanteDTO;
import senac.domain.dtos.ambos.RegrasDTO;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.mappers.ParticipanteMapper;
import senac.domain.mappers.RegrasMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.RegrasModel;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.repositories.RegrasRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegrasService {
    private final RegrasRepository regrasRepository;
    private final CampanhaRepository campanhaRepository;
    private final RegrasMapper regrasMapper;
    private final ParticipanteMapper participanteMapper;
    private final CampanhaMapper campanhaMapper;
    private final ParticipanteService participanteService;
    private final CampanhaService campanhaService;
    @Autowired
    public RegrasService(RegrasRepository regrasRepository, CampanhaRepository campanhaRepository, RegrasMapper regrasMapper, ParticipanteMapper participanteMapper, CampanhaMapper campanhaMapper, ParticipanteService participanteService, CampanhaService campanhaService) {
        this.regrasRepository = regrasRepository;
        this.campanhaRepository = campanhaRepository;
        this.regrasMapper = regrasMapper;
        this.participanteMapper = participanteMapper;
        this.campanhaMapper = campanhaMapper;
        this.participanteService = participanteService;
        this.campanhaService = campanhaService;
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
        Integer codCampanha = regrasDto.getCodCampanha();
        Integer codParticipante = regrasDto.getCodParticipante();

        CampanhaRequestDTO campanhaExistente = campanhaService.obterCampanhaPorIdRequest(codCampanha);
        ParticipanteDTO participanteExistente = participanteService.obterParticipantePorIdRequest(codParticipante);

        if(!(participanteExistente.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para adicionar regras.");
        }

        RegrasModel regrasModel = regrasMapper.toEntity(regrasDto);
        regrasModel.setCampanhaModel(campanhaMapper.toEntity(campanhaExistente));
        regrasModel.setParticipanteModel(participanteMapper.toEntity(participanteExistente));
        regrasRepository.save(regrasModel);
    }

    public void atualizarRegras(Integer codRegras, RegrasDTO regrasDto) {

        RegrasDTO regrasExistente = obterRegrasPorId(codRegras);
        ParticipanteModel participanteExistente = participanteMapper.toEntity(participanteService.obterParticipantePorIdRequest(regrasExistente.getCodParticipante()));
        CampanhaModel campanhaExistente = campanhaMapper.toEntity(campanhaService.obterCampanhaPorIdRequest(regrasExistente.getCodCampanha()));

        if(!(participanteExistente.getCargo().equals("Mestre"))){
            throw new RuntimeException("Participante não possui o cargo MESTRE para atualizar regras.");
        }
        else if(!(regrasDto.getCodCampanha().equals(regrasExistente.getCodCampanha()))){
            throw new RuntimeException("Não é possível alterar as regras para uma outra campanha.");
        }
        else if(!(regrasDto.getCodParticipante().equals(regrasExistente.getCodParticipante()))){
            throw new RuntimeException("Não é possível alterar por quem foi escrita as regras.");
        }
        RegrasModel regrasAtualizada = regrasMapper.toEntity(regrasDto);

        regrasAtualizada.setCodRegras(codRegras);
        regrasAtualizada.setParticipanteModel(participanteExistente);
        regrasAtualizada.setCampanhaModel(campanhaExistente);
        regrasRepository.save(regrasAtualizada);
    }

    // Excluir Regras
    public void excluirRegras(Integer codRegras) {
        obterRegrasPorId(codRegras);
        regrasRepository.deleteById(codRegras);
    }
}