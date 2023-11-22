package senac.domain.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.requests.PersonagemRequestDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.PersonagemModel;
@Component
public class PersonagemMapper {
    @Autowired
    private ModelMapper modelMapper;

//    @PostConstruct
//    public void configureMapper() {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        TypeMap<ParticipanteModel, ParticipanteRequestDTO> typeMapRequest = modelMapper.createTypeMap(ParticipanteModel.class, ParticipanteRequestDTO.class);
//        typeMapRequest.addMapping(src -> src.getUsuarioModel().getCodUsuario(), ParticipanteRequestDTO::setCodUsuario);
//        typeMapRequest.addMapping(src -> src.getCampanhaModel().getCodCampanha(), ParticipanteRequestDTO::setCodCampanha);
//
//        TypeMap<ParticipanteModel, ParticipanteResponseDTO> typeMapResponse = modelMapper.createTypeMap(ParticipanteModel.class, ParticipanteResponseDTO.class);
//        typeMapResponse.addMapping(src -> src.getUsuarioModel().getCodUsuario(), ParticipanteResponseDTO::setCodUsuario);
//        typeMapResponse.addMapping(src -> src.getCampanhaModel().getCodCampanha(), ParticipanteResponseDTO::setCodCampanha);
//    }


    public PersonagemResponseDTO toResponseDto(PersonagemModel personagemModel) {
        PersonagemResponseDTO personagemResponseDTO = modelMapper.map(personagemModel, PersonagemResponseDTO.class);
        personagemResponseDTO.setCodParticipante(personagemModel.getParticipanteModel().getCodParticipante());
        return personagemResponseDTO;
    }

    public PersonagemRequestDTO toRequestDto(PersonagemModel personagemModel) {
        PersonagemRequestDTO personagemRequestDTO = modelMapper.map(personagemModel, PersonagemRequestDTO.class);
        personagemRequestDTO.setCodParticipante(personagemModel.getParticipanteModel().getCodParticipante());
        return personagemRequestDTO;
    }

    public PersonagemModel toEntity(PersonagemResponseDTO personagemResponseDTO) {
        return modelMapper.map(personagemResponseDTO, PersonagemModel.class);
    }

    public PersonagemModel toEntity(PersonagemRequestDTO personagemRequestDTO) {
        return modelMapper.map(personagemRequestDTO, PersonagemModel.class);
    }
}
