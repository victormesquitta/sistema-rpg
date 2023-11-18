package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.ParticipanteDTO;
import senac.domain.dtos.ambos.PersonagemDTO;
import senac.domain.mappers.ParticipanteMapper;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.PersonagemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final ParticipanteService participanteService;
    private final ParticipanteMapper participanteMapper;
    private final PersonagemMapper personagemMapper;

    @Autowired
    public PersonagemService(PersonagemRepository personagemRepository, ParticipanteService participanteService, ParticipanteMapper participanteMapper, PersonagemMapper personagemMapper) {
        this.personagemRepository = personagemRepository;
        this.participanteService = participanteService;
        this.participanteMapper = participanteMapper;
        this.personagemMapper = personagemMapper;
    }



    public List<PersonagemDTO> listarPersonagens() {
        List<PersonagemModel> personagens = personagemRepository.findAll();
        if(personagens.isEmpty()){
            throw new EntityNotFoundException("Nenhum personagem cadastrado.");
        }
        return personagens.stream()
                .map(personagemMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonagemDTO obterPersonagemPorId(Integer codPersonagem) {
        listarPersonagens();
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(codPersonagem);
        personagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum personagem encontrado para o ID fornecido."));
        return personagemOptional.map(personagemMapper::toDto).orElse(null);
    }

    public void criarPersonagem(PersonagemDTO personagemDTO) {

        ParticipanteModel participanteExistente = participanteMapper.toEntity(participanteService.obterParticipantePorId(personagemDTO.getCodParticipante()));

        PersonagemModel personagemModel = personagemMapper.toEntity(personagemDTO);
        personagemModel.setParticipanteModel(participanteExistente);

        personagemRepository.save(personagemModel);
    }

    public void atualizarPersonagem(Integer codPersonagem, PersonagemDTO personagemDTO) {

        PersonagemDTO personagemExistente = obterPersonagemPorId(codPersonagem);
        ParticipanteDTO participanteDto = participanteService.obterParticipantePorId(personagemExistente.getCodParticipante());

        if(!(participanteDto.getCodParticipante().equals(personagemDTO.getCodParticipante()))){
            throw new RuntimeException("Personagem não pode alterar de participante.");
        }
        PersonagemModel personagemAtualizado = personagemMapper.toEntity(personagemDTO);
        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDto);
        personagemAtualizado.setParticipanteModel(participanteModel);
        personagemRepository.save(personagemAtualizado);
    }
    public void excluirPersonagem(Integer codPersonagem) {
        obterPersonagemPorId(codPersonagem);
        personagemRepository.deleteById(codPersonagem);
    }
}
