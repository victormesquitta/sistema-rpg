package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.dtos.responses.UsuarioResponseDTO;
import senac.domain.models.UsuarioModel;
@Component
public class UsuarioMapper {
    @Autowired
    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioModel toEntity(UsuarioRequestDTO usuarioRequestDto) {
        return modelMapper.map(usuarioRequestDto, UsuarioModel.class);
    }

    public UsuarioRequestDTO toRequestDto(UsuarioModel usuarioModel) {
        return modelMapper.map(usuarioModel, UsuarioRequestDTO.class);
    }

    public UsuarioResponseDTO toResponseDto(UsuarioModel usuarioModel) {
        return modelMapper.map(usuarioModel, UsuarioResponseDTO.class);
    }


}
