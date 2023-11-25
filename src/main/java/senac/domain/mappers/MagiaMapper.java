package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.MagiaRequestDTO;
import senac.domain.dtos.responses.MagiaResponseDTO;
import senac.domain.models.MagiaModel;

@Component
public class MagiaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MagiaResponseDTO toResponseDto(MagiaModel magiaModel) {
        MagiaResponseDTO magiaResponseDTO = modelMapper.map(magiaModel, MagiaResponseDTO.class);
        magiaResponseDTO.setCodPersonagem(magiaModel.getPersonagemModel().getCodPersonagem());
        return magiaResponseDTO;
    }

    public MagiaRequestDTO toRequestDto(MagiaModel magiaModel) {
        MagiaRequestDTO magiaRequestDTO = modelMapper.map(magiaModel, MagiaRequestDTO.class);
        magiaRequestDTO.setCodPersonagem(magiaModel.getPersonagemModel().getCodPersonagem());
        return magiaRequestDTO;
    }

    public MagiaModel toEntity(MagiaResponseDTO magiaResponseDTO) {
        return modelMapper.map(magiaResponseDTO, MagiaModel.class);
    }

    public MagiaModel toEntity(MagiaRequestDTO magiaRequestDTO) {
        return modelMapper.map(magiaRequestDTO, MagiaModel.class);
    }
}
