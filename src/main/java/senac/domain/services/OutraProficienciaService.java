package senac.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.OutraProficienciaDTO;
import senac.domain.dtos.ambos.PersonagemDTO;
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

    public List<OutraProficienciaDTO> listarOutrasProficiencias() {
        List<OutraProficienciaModel> outrasProficiencias = outraProficienciaRepository.findAll();
        if (outrasProficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma outra proficiência encontrada.");
        }
        return outrasProficiencias.stream()
                .map(outraProficienciaMapper::toDto)
                .collect(Collectors.toList());
    }

    public OutraProficienciaDTO obterOutraProficienciaPorId(int codOutraProficiencia) {
        listarOutrasProficiencias();
        Optional<OutraProficienciaModel> outraProficienciaOptional = outraProficienciaRepository.findById(codOutraProficiencia);
        outraProficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma outra proficiência encontrada para o ID fornecido."));
        return outraProficienciaOptional.map(outraProficienciaMapper::toDto).orElse(null);
    }

    public void criarOutraProficiencia(OutraProficienciaDTO outraProficienciaDTO) {
        OutraProficienciaModel outraProficienciaModel = outraProficienciaMapper.toEntity(outraProficienciaDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorId(outraProficienciaDTO.getCodPersonagem()));
        outraProficienciaModel.setPersonagemModel(personagemModel);
        outraProficienciaRepository.save(outraProficienciaModel);
    }

    public void atualizarOutraProficiencia(int codOutraProficiencia, OutraProficienciaDTO outraProficienciaDTO) {
        OutraProficienciaDTO outraProficienciaExistente = obterOutraProficienciaPorId(codOutraProficiencia);
        PersonagemDTO personagemDTO = personagemService.obterPersonagemPorId(outraProficienciaExistente.getCodPersonagem());

        if(!(personagemDTO.getCodPersonagem().equals(outraProficienciaDTO.getCodPersonagem()))){
            throw new RuntimeException("A Outra Proficiência não pode alterar de personagem.");
        }

        OutraProficienciaModel outraProficienciaAtualizada = outraProficienciaMapper.toEntity(outraProficienciaDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemDTO);
        outraProficienciaAtualizada.setCodOutraProficiencia(codOutraProficiencia);
        outraProficienciaAtualizada.setPersonagemModel(personagemModel);
        outraProficienciaRepository.save(outraProficienciaAtualizada);
    }

    public void excluirOutraProficiencia(int codOutraProficiencia) {
        obterOutraProficienciaPorId(codOutraProficiencia);
        outraProficienciaRepository.deleteById(codOutraProficiencia);
    }
}
