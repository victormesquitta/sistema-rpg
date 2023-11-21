package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.models.ParticipanteModel;

@Component
public class ParticipanteMapper {

    @Autowired
    private ModelMapper modelMapper;


    public ParticipanteRequestDTO toRequestDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteRequestDTO.class);
    }

    public ParticipanteResponseDTO toResponseDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteResponseDTO.class);
    }

    public ParticipanteModel toEntity(ParticipanteResponseDTO participanteResponseDTO) {
        return modelMapper.map(participanteResponseDTO, ParticipanteModel.class);
    }

    public ParticipanteModel toEntity(ParticipanteRequestDTO participanteRequestDTO) {
        return modelMapper.map(participanteRequestDTO, ParticipanteModel.class);
    }
}
