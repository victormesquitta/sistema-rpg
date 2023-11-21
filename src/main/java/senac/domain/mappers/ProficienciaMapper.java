package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.ProficienciaRequestDTO;
import senac.domain.dtos.responses.ProficienciaResponseDTO;
import senac.domain.models.ProficienciaModel;
@Component
public class ProficienciaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProficienciaResponseDTO toResponseDto(ProficienciaModel proficienciaModel) {
        return modelMapper.map(proficienciaModel, ProficienciaResponseDTO.class);
    }

    public ProficienciaRequestDTO toRequestDto(ProficienciaModel proficienciaModel) {
        return modelMapper.map(proficienciaModel, ProficienciaRequestDTO.class);
    }

    public ProficienciaModel toEntity(ProficienciaResponseDTO proficienciaResponseDTO) {
        return modelMapper.map(proficienciaResponseDTO, ProficienciaModel.class);
    }

    public ProficienciaModel toEntity(ProficienciaRequestDTO proficienciaRequestDTO) {
        return modelMapper.map(proficienciaRequestDTO, ProficienciaModel.class);
    }

}
