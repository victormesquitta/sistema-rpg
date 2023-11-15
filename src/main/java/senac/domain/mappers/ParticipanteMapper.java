package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ParticipanteDto;
import senac.domain.models.ParticipanteModel;

@Component
public class ParticipanteMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;  // Adicione o mapeador de usuário


    public ParticipanteDto toDto(ParticipanteModel participanteModel) {
        ParticipanteDto participanteDto = modelMapper.map(participanteModel, ParticipanteDto.class);
        if (participanteModel.getUsuarioModel() != null) {
            participanteDto.setCodUsuario(participanteModel.getUsuarioModel().getCodUsuario());
        }        return participanteDto;
    }

    public ParticipanteModel toEntity(ParticipanteDto participanteDto) {
        return modelMapper.map(participanteDto, ParticipanteModel.class);
    }
}
