package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.PersonagemDTO;
import senac.domain.dtos.ambos.ProficienciaDTO;
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

    public List<ProficienciaDTO> listarProficiencias() {
        List<ProficienciaModel> proficiencias = proficienciaRepository.findAll();
        if (proficiencias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma proficiência encontrada.");
        }
        return proficiencias.stream()
                .map(proficienciaMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProficienciaDTO obterProficienciaPorId(int codProficiencia) {
        listarProficiencias();
        Optional<ProficienciaModel> proficienciaOptional = proficienciaRepository.findById(codProficiencia);
        proficienciaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma proficiência encontrada para o ID fornecido."));
        return proficienciaOptional.map(proficienciaMapper::toDto).orElse(null);
    }

    public void criarProficiencia(ProficienciaDTO proficienciaDTO) {
        ProficienciaModel proficienciaModel = proficienciaMapper.toEntity(proficienciaDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorId(proficienciaModel.getCodProficiencia()));
        proficienciaModel.setPersonagemModel(personagemModel);
        proficienciaRepository.save(proficienciaModel);
    }

    public void atualizarProficiencia(int codProficiencia, ProficienciaDTO proficienciaDTO) {

        ProficienciaDTO proficienciaExistente = obterProficienciaPorId(codProficiencia);
        PersonagemDTO personagemDTO = personagemService.obterPersonagemPorId(proficienciaExistente.getCodProficiencia());

        if (!(personagemDTO.getCodPersonagem().equals(proficienciaDTO.getCodPersonagem()))) {
            throw new RuntimeException("A proficiência não pode alterar de personagem.");
        }

        ProficienciaModel proficienciaAtualizada = proficienciaMapper.toEntity(proficienciaDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemDTO);

        proficienciaAtualizada.setPersonagemModel(personagemModel);
        proficienciaAtualizada.setCodProficiencia(codProficiencia);
        proficienciaRepository.save(proficienciaAtualizada);
    }

    public void excluirProficiencia(int codProficiencia) {
        obterProficienciaPorId(codProficiencia);
        proficienciaRepository.deleteById(codProficiencia);
    }
}
