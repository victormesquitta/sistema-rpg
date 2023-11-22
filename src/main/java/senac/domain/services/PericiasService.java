package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.PericiasRequestDTO;
import senac.domain.dtos.responses.PericiasResponseDTO;
import senac.domain.mappers.PericiasMapper;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.models.PersonagemModel;
import senac.domain.models.PericiasModel;
import senac.domain.repositories.PericiasRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PericiasService {

    private final PericiasRepository periciasRepository;
    private final PericiasMapper periciasMapper;
    private final PersonagemService personagemService;
    private final PersonagemMapper personagemMapper;

    @Autowired
    public PericiasService(PericiasRepository periciasRepository, PericiasMapper periciasMapper, PersonagemService personagemService, PersonagemMapper personagemMapper) {
        this.periciasRepository = periciasRepository;
        this.periciasMapper = periciasMapper;
        this.personagemService = personagemService;
        this.personagemMapper = personagemMapper;
    }

    public List<PericiasResponseDTO> listarPericiasResponse() {
        List<PericiasModel> pericias = periciasRepository.findAll();
        if (pericias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma perícia encontrada.");
        }
        return pericias.stream()
                .map(periciasMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<PericiasRequestDTO> listarPericiasRequest() {
        List<PericiasModel> pericias = periciasRepository.findAll();
        if (pericias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma perícia encontrada.");
        }
        return pericias.stream()
                .map(periciasMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public PericiasResponseDTO obterPericiaPorIdResponse(int codPericia) {
        listarPericiasResponse();
        Optional<PericiasModel> periciaOptional = periciasRepository.findById(codPericia);
        periciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma perícia encontrada para o ID fornecido."));
        return periciaOptional.map(periciasMapper::toResponseDto).orElse(null);
    }

    public PericiasRequestDTO obterPericiaPorIdRequest(int codPericia) {
        listarPericiasResponse();
        Optional<PericiasModel> periciaOptional = periciasRepository.findById(codPericia);
        periciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma perícia encontrada para o ID fornecido."));
        return periciaOptional.map(periciasMapper::toRequestDto).orElse(null);
    }

    public void criarPericia(PericiasRequestDTO periciasRequestDTO){
        periciasRepository.findById(periciasRequestDTO.)
        if(periciasRequestDTO.getCodPersonagem().equals()){

        }

        PericiasModel periciasModel = periciasMapper.toEntity(periciasRequestDTO);
        periciasRepository.save();
    }

    public void criarPericiaComParticipante(PersonagemModel personagemModel) {
        PericiasResponseDTO periciasResponseDTO = new PericiasResponseDTO();
        PericiasModel periciasModel = periciasMapper.toEntity(periciasResponseDTO);

        periciasModel.setAtletismo(0);
        periciasModel.setAcrobacia(0);
        periciasModel.setFurtividade(0);
        periciasModel.setPrestidigitacao(0);
        periciasModel.setArcanismo(0);
        periciasModel.setHistoria(0);
        periciasModel.setInvestigacao(0);
        periciasModel.setNatureza(0);
        periciasModel.setReligiao(0);
        periciasModel.setIntuicao(0);
        periciasModel.setLidarComAnimais(0);
        periciasModel.setMedicina(0);
        periciasModel.setPercepcao(0);
        periciasModel.setSobrevivencia(0);
        periciasModel.setAtuacao(0);
        periciasModel.setEnganacao(0);
        periciasModel.setIntimidacao(0);
        periciasModel.setPersuasao(0);

        periciasModel.setPersonagemModel(personagemModel);

        periciasRepository.save(periciasModel);
    }

    public void atualizarPericia(int codPericia, PericiasRequestDTO periciaRequestDTO) {
        PericiasResponseDTO periciasExistentes = obterPericiaPorIdResponse(codPericia);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorIdResponse(periciasExistentes.getCodPersonagem()));

        if (!(personagemModel.getCodPersonagem().equals(periciasExistentes.getCodPersonagem()))) {
            throw new RuntimeException("A perícia não pode alterar de personagem.");
        }

        PericiasModel periciaAtualizada = periciasMapper.toEntity(periciaRequestDTO);
        periciaAtualizada.setPersonagemModel(personagemModel);
        periciaAtualizada.setCodPericia(codPericia);
        periciasRepository.save(periciaAtualizada);
    }

    public void excluirPericia(int codPericia) {
        obterPericiaPorIdResponse(codPericia);
        periciasRepository.deleteById(codPericia);
    }
}
