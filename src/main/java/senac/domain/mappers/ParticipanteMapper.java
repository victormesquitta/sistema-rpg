package senac.domain.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.models.ParticipanteModel;

@Component
public class ParticipanteMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<ParticipanteModel, ParticipanteRequestDTO> typeMapRequest = modelMapper.createTypeMap(ParticipanteModel.class, ParticipanteRequestDTO.class);
        typeMapRequest.addMapping(src -> src.getUsuarioModel().getCodUsuario(), ParticipanteRequestDTO::setCodUsuario);
        typeMapRequest.addMapping(src -> src.getCampanhaModel().getCodCampanha(), ParticipanteRequestDTO::setCodCampanha);

        TypeMap<ParticipanteModel, ParticipanteResponseDTO> typeMapResponse = modelMapper.createTypeMap(ParticipanteModel.class, ParticipanteResponseDTO.class);
        typeMapResponse.addMapping(src -> src.getUsuarioModel().getCodUsuario(), ParticipanteResponseDTO::setCodUsuario);
        typeMapResponse.addMapping(src -> src.getCampanhaModel().getCodCampanha(), ParticipanteResponseDTO::setCodCampanha);
    }



    public ParticipanteRequestDTO toRequestDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteRequestDTO.class);
    }

    public ParticipanteResponseDTO toResponseDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteResponseDTO.class);
    }

//    public ParticipanteResponseDTO toResponseDto(ParticipanteRequestDTO participanteRequestDTO) {
//        return modelMapper.map(participanteRequestDTO, ParticipanteResponseDTO.class);
//    }

    public ParticipanteModel toEntity(ParticipanteResponseDTO participanteResponseDTO) {
        return modelMapper.map(participanteResponseDTO, ParticipanteModel.class);
    }

    public ParticipanteModel toEntity(ParticipanteRequestDTO participanteRequestDTO) {
        return modelMapper.map(participanteRequestDTO, ParticipanteModel.class);
    }
}
