package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.UsuarioDTO;
import senac.domain.dtos.UsuarioRespostaDTO;
import senac.domain.models.UsuarioModel;
@Component
public class UsuarioMapper {
    @Autowired
    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioModel toEntity(UsuarioDTO usuarioDto) {
        return modelMapper.map(usuarioDto, UsuarioModel.class);
    }
    public UsuarioModel toEntity(UsuarioRespostaDTO usuarioRespostaDto) {
        return modelMapper.map(usuarioRespostaDto, UsuarioModel.class);
    }

    public UsuarioRespostaDTO toRespostaDto(UsuarioModel usuarioModel) {
        return modelMapper.map(usuarioModel, UsuarioRespostaDTO.class);
    }


}
