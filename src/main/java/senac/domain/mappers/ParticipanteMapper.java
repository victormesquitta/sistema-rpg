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
    @Autowired
    private UsuarioMapper usuarioMapper;  // Adicione o mapeador de usuário


    public ParticipanteRequestDTO toRequestDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteRequestDTO.class);
        /*if (participanteModel.getUsuarioModel() != null) {
            participanteRequestDto.setCodUsuario(participanteModel.getUsuarioModel().getCodUsuario());
        }        return participanteRequestDto;*/
    }

    public ParticipanteModel toEntity(ParticipanteRequestDTO participanteRequestDto) {
        return modelMapper.map(participanteRequestDto, ParticipanteModel.class);
    }

    public ParticipanteResponseDTO toResponseDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteResponseDTO.class);
    }
}
