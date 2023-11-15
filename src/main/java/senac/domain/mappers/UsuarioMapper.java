package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.UsuarioDto;
import senac.domain.models.UsuarioModel;
@Component
public class UsuarioMapper {
    @Autowired
    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioDto toDto(UsuarioModel usuarioModel) {
        UsuarioDto usuarioDto = modelMapper.map(usuarioModel, UsuarioDto.class);
        usuarioDto.setCodUsuario(usuarioModel.getCodUsuario());
        return usuarioDto;
    }

    public UsuarioModel toEntity(UsuarioDto usuarioDto) {
        return modelMapper.map(usuarioDto, UsuarioModel.class);
    }

}
