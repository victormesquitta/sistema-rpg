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
    private UsuarioMapper usuarioMapper;


    public ParticipanteDTO toDto(ParticipanteModel participanteModel) {
        return modelMapper.map(participanteModel, ParticipanteDTO.class);
    }

    public ParticipanteModel toEntity(ParticipanteDTO participanteDto) {
        return modelMapper.map(participanteDto, ParticipanteModel.class);
    }
}
