package senac.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.OutraProficienciaRequestDTO;
import senac.domain.dtos.responses.OutraProficienciaResponseDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.mappers.OutraProficienciaMapper;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.models.OutraProficienciaModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.OutraProficienciaRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OutraProficienciaService {

    private final OutraProficienciaRepository outraProficienciaRepository;
    private final OutraProficienciaMapper outraProficienciaMapper;
    private final PersonagemService personagemService;
    private final PersonagemMapper personagemMapper;

    @Autowired
    public OutraProficienciaService(OutraProficienciaRepository outraProficienciaRepository, OutraProficienciaMapper outraProficienciaMapper, PersonagemService personagemService, PersonagemMapper personagemMapper) {
        this.outraProficienciaRepository = outraProficienciaRepository;
        this.outraProficienciaMapper = outraProficienciaMapper;
        this.personagemService = personagemService;
        this.personagemMapper = personagemMapper;
    }

    public List<OutraProficienciaResponseDTO> listarOutrasProficienciasResponse() {
        List<OutraProficienciaModel> outrasProficiencias = outraProficienciaRepository.findAll();
        if (outrasProficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma outra proficiência encontrada.");
        }
        return outrasProficiencias.stream()
                .map(outraProficienciaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<OutraProficienciaRequestDTO> listarOutrasProficienciasRequest() {
        List<OutraProficienciaModel> outrasProficiencias = outraProficienciaRepository.findAll();
        if (outrasProficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma outra proficiência encontrada.");
        }
        return outrasProficiencias.stream()
                .map(outraProficienciaMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public OutraProficienciaResponseDTO obterOutraProficienciaPorIdResponse(int codOutraProficiencia) {
        listarOutrasProficienciasResponse();
        Optional<OutraProficienciaModel> outraProficienciaOptional = outraProficienciaRepository.findById(codOutraProficiencia);
        outraProficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma outra proficiência encontrada para o ID fornecido."));
        return outraProficienciaOptional.map(outraProficienciaMapper::toResponseDto).orElse(null);
    }

    public OutraProficienciaRequestDTO obterOutraProficienciaPorIdRequest(int codOutraProficiencia) {
        listarOutrasProficienciasResponse();
        Optional<OutraProficienciaModel> outraProficienciaOptional = outraProficienciaRepository.findById(codOutraProficiencia);
        outraProficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma outra proficiência encontrada para o ID fornecido."));
        return outraProficienciaOptional.map(outraProficienciaMapper::toRequestDto).orElse(null);
    }

    public void criarOutraProficiencia(OutraProficienciaRequestDTO outraProficienciaRequestDTO) {
        OutraProficienciaModel outraProficienciaModel = outraProficienciaMapper.toEntity(outraProficienciaRequestDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorIdRequest(outraProficienciaModel.getPersonagemModel().getCodPersonagem()));
        outraProficienciaModel.setPersonagemModel(personagemModel);
        outraProficienciaRepository.save(outraProficienciaModel);
    }

    public void atualizarOutraProficiencia(int codOutraProficiencia, OutraProficienciaRequestDTO outraProficienciaRequestDTO) {
        OutraProficienciaResponseDTO outraProficienciaExistente = obterOutraProficienciaPorIdResponse(codOutraProficiencia);
        PersonagemResponseDTO personagemResponseDTO = personagemService.obterPersonagemPorIdResponse(outraProficienciaExistente.getCodPersonagem());

        if(!(personagemResponseDTO.getCodPersonagem().equals(outraProficienciaExistente.getCodPersonagem()))){
            throw new RuntimeException("A Outra Proficiência não pode alterar de personagem.");
        }

        OutraProficienciaModel outraProficienciaAtualizada = outraProficienciaMapper.toEntity(outraProficienciaRequestDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemResponseDTO);
        outraProficienciaAtualizada.setCodOutraProficiencia(codOutraProficiencia);
        outraProficienciaAtualizada.setPersonagemModel(personagemModel);
        outraProficienciaRepository.save(outraProficienciaAtualizada);
    }

    public void excluirOutraProficiencia(int codOutraProficiencia) {
        obterOutraProficienciaPorIdResponse(codOutraProficiencia);
        outraProficienciaRepository.deleteById(codOutraProficiencia);
    }
}
