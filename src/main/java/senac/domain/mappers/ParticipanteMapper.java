package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.ParticipanteDTO;
import senac.domain.models.ParticipanteModel;

@Component
public class ParticipanteMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;  // Adicione o mapeador de usuário


    public ParticipanteDTO toDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteDTO.class);
        /*if (participanteModel.getUsuarioModel() != null) {
            participanteRequestDto.setCodUsuario(participanteModel.getUsuarioModel().getCodUsuario());
        }        return participanteRequestDto;*/
    }

    public ParticipanteModel toEntity(ParticipanteDTO participanteDto) {
        return modelMapper.map(participanteDto, ParticipanteModel.class);
    }
}
