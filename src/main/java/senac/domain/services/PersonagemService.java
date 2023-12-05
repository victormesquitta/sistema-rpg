package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.PersonagemRequestDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.mappers.ParticipanteMapper;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.repositories.PersonagemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final CampanhaRepository campanhaRepository;
    private final ParticipanteService participanteService;
    private final ParticipanteMapper participanteMapper;
    private final PersonagemMapper personagemMapper;

    @Autowired
    public PersonagemService(PersonagemRepository personagemRepository, CampanhaRepository campanhaRepository, ParticipanteService participanteService, ParticipanteMapper participanteMapper, PersonagemMapper personagemMapper) {
        this.personagemRepository = personagemRepository;
        this.campanhaRepository = campanhaRepository;
        this.participanteService = participanteService;
        this.participanteMapper = participanteMapper;
        this.personagemMapper = personagemMapper;
    }

    public List<PersonagemResponseDTO> listarPersonagensPorCampanha(Integer codCampanha) {
        List<PersonagemResponseDTO> personagens = new ArrayList<>();

        for(PersonagemResponseDTO personagem : listarPersonagensResponse()){
            ParticipanteResponseDTO participante = participanteService.obterParticipantePorIdResponse(personagem.getCodParticipante());
            if(participante.getCodCampanha() == codCampanha){
                personagens.add(personagem);
            }
        }

        return personagens;
    }

//    public List<PersonagemRequestDTO> obterPersonagensDaCampanha(Integer codCampanha) {
//        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(codCampanha);
//
//        if (campanhaOptional.isPresent()) {
//            CampanhaModel campanha = campanhaOptional.get();
//            List<ParticipanteModel> participantes = campanha.getParticipantes();
//            List<PersonagemRequestDTO> personagens = new ArrayList<>();
//
//            for (ParticipanteModel participante : participantes) {
//                personagens.addAll(participante.getPersonagens().stream()
//                        .map(PersonagemRequestDTO::new) // Converter PersonagemModel para PersonagemRequestDTO
//                        .toList());
//            }
//
//            return personagens;
//        } else {
//            // Lógica para tratamento de erro (campanha não encontrada)
//            return Collections.emptyList();
//        }
//    }


    public List<PersonagemResponseDTO> listarPersonagensResponse() {
        List<PersonagemModel> personagens = personagemRepository.findAll();
        if(personagens.isEmpty()){
            throw new EntityNotFoundException("Nenhum personagem cadastrado.");
        }
        return personagens.stream()
                .map(personagem -> {
                    PersonagemResponseDTO personagemResponseDTO = personagemMapper.toResponseDto(personagem);
                    personagemResponseDTO.setCodParticipante(personagem.getParticipanteModel().getCodParticipante());
                    return personagemResponseDTO;
                })
                .collect(Collectors.toList());
    }

    public List<PersonagemRequestDTO> listarPersonagensRequest() {
        List<PersonagemModel> personagens = personagemRepository.findAll();
        if(personagens.isEmpty()){
            throw new EntityNotFoundException("Nenhum personagem cadastrado.");
        }
        return personagens.stream()
                .map(personagemMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public PersonagemResponseDTO obterPersonagemPorIdResponse(Integer codPersonagem) {
        listarPersonagensResponse();
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(codPersonagem);
        System.out.println(personagemOptional);
        personagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum personagem encontrado para o ID fornecido."));


        return personagemOptional.map(personagemMapper::toResponseDto).orElse(null);
    }

    public PersonagemRequestDTO obterPersonagemPorIdRequest(Integer codPersonagem) {
        listarPersonagensResponse();
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(codPersonagem);
        personagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum personagem encontrado para o ID fornecido."));
        return personagemOptional.map(personagemMapper::toRequestDto).orElse(null);
    }


    public PersonagemModel obterPersonagemModelPorId(Integer codPersonagem){
        listarPersonagensResponse();
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(codPersonagem);
        personagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum personagem encontrado para o ID fornecido."));
        PersonagemRequestDTO personagemRequestDTO = personagemOptional.map(personagemMapper::toRequestDto).orElse(null);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemRequestDTO);
        personagemModel.setCodPersonagem(obterPersonagemPorIdResponse(codPersonagem).getCodPersonagem());
        personagemModel.setParticipanteModel(participanteService.obterParticipanteModelPorId(personagemRequestDTO.getCodParticipante()));
        return personagemModel;
    }


    public PersonagemModel criarPersonagem(PersonagemRequestDTO personagemRequestDTO) {

        ParticipanteModel participanteModel = participanteService.obterParticipanteModelPorId(personagemRequestDTO.getCodParticipante());
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemRequestDTO);
        personagemModel.setParticipanteModel(participanteModel);

        personagemRepository.save(personagemModel);
        return personagemModel;
    }

    public void atualizarPersonagem(Integer codPersonagem, PersonagemRequestDTO personagemRequestDTO) {

        ParticipanteModel participanteModel = participanteService.obterParticipanteModelPorId(personagemRequestDTO.getCodParticipante());

        PersonagemResponseDTO personagemResponseDTO = obterPersonagemPorIdResponse(codPersonagem);

        if(!(personagemResponseDTO.getCodParticipante().equals(personagemRequestDTO.getCodParticipante()))){
            throw new RuntimeException("Personagem não pode alterar de participante.");
        }

        PersonagemModel personagemModel = personagemMapper.toEntity(personagemRequestDTO);
        personagemModel.setParticipanteModel(participanteModel);
        personagemModel.setCodPersonagem(codPersonagem);

        personagemRepository.save(personagemModel);
    }
    public void excluirPersonagem(Integer codPersonagem) {
        obterPersonagemPorIdResponse(codPersonagem);
        personagemRepository.deleteById(codPersonagem);
    }
}
