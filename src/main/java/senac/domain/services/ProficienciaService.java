package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.ProficienciaRequestDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.dtos.responses.ProficienciaResponseDTO;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.mappers.ProficienciaMapper;
import senac.domain.models.PersonagemModel;
import senac.domain.models.ProficienciaModel;
import senac.domain.repositories.ProficienciaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProficienciaService {

    private final ProficienciaRepository proficienciaRepository;
    private final ProficienciaMapper proficienciaMapper;
    private final PersonagemService personagemService;
    private final PersonagemMapper personagemMapper;

    @Autowired
    public ProficienciaService(ProficienciaRepository proficienciaRepository, ProficienciaMapper proficienciaMapper, PersonagemService personagemService, PersonagemMapper personagemMapper) {
        this.proficienciaRepository = proficienciaRepository;
        this.proficienciaMapper = proficienciaMapper;
        this.personagemService = personagemService;
        this.personagemMapper = personagemMapper;
    }

    public List<ProficienciaResponseDTO> listarProficienciasResponse() {
        List<ProficienciaModel> proficiencias = proficienciaRepository.findAll();
        if (proficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma proficiência encontrada.");
        }
        return proficiencias.stream()
                .map(proficienciaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ProficienciaRequestDTO> listarProficienciasRequest() {
        List<ProficienciaModel> proficiencias = proficienciaRepository.findAll();
        if (proficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma proficiência encontrada.");
        }
        return proficiencias.stream()
                .map(proficienciaMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public ProficienciaResponseDTO obterProficienciaPorIdResponse(int codProficiencia) {
        listarProficienciasResponse();
        Optional<ProficienciaModel> proficienciaOptional = proficienciaRepository.findById(codProficiencia);
        proficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma proficiência encontrada para o ID fornecido."));
        return proficienciaOptional.map(proficienciaMapper::toResponseDto).orElse(null);
    }

    public ProficienciaRequestDTO obterProficienciaPorIdRequest(int codProficiencia) {
        listarProficienciasResponse();
        Optional<ProficienciaModel> proficienciaOptional = proficienciaRepository.findById(codProficiencia);
        proficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma proficiência encontrada para o ID fornecido."));
        return proficienciaOptional.map(proficienciaMapper::toRequestDto).orElse(null);
    }

    public void criarProficiencia(ProficienciaRequestDTO proficienciaRequestDTO) {
        ProficienciaModel proficienciaModel = proficienciaMapper.toEntity(proficienciaRequestDTO);
        System.out.println(proficienciaModel);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorIdRequest(proficienciaModel.getPersonagemModel().getCodPersonagem()));
        System.out.println("PersonagemModel: " + personagemModel);
        proficienciaModel.setPersonagemModel(personagemModel);
        proficienciaRepository.save(proficienciaModel);
    }

    public void atualizarProficiencia(int codProficiencia, ProficienciaRequestDTO proficienciaRequestDTO) {

        ProficienciaResponseDTO proficienciaExistente = obterProficienciaPorIdResponse(codProficiencia);
        PersonagemResponseDTO personagemDTO = personagemService.obterPersonagemPorIdResponse(proficienciaExistente.getCodProficiencia());

        if (!(personagemDTO.getCodPersonagem().equals(proficienciaExistente.getCodPersonagem()))) {
            throw new RuntimeException("A proficiência não pode alterar de personagem.");
        }

        ProficienciaModel proficienciaAtualizada = proficienciaMapper.toEntity(proficienciaRequestDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemDTO);

        proficienciaAtualizada.setPersonagemModel(personagemModel);
        proficienciaAtualizada.setCodProficiencia(codProficiencia);
        proficienciaRepository.save(proficienciaAtualizada);
    }

    public void excluirProficiencia(int codProficiencia) {
        obterProficienciaPorIdResponse(codProficiencia);
        proficienciaRepository.deleteById(codProficiencia);
    }
}
