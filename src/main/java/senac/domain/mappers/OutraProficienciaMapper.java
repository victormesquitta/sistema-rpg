package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.OutraProficienciaRequestDTO;
import senac.domain.dtos.responses.OutraProficienciaResponseDTO;
import senac.domain.models.OutraProficienciaModel;

@Component
public class OutraProficienciaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OutraProficienciaResponseDTO toResponseDto(OutraProficienciaModel outraProficienciaModel) {
        return modelMapper.map(outraProficienciaModel, OutraProficienciaResponseDTO.class);
    }

    public OutraProficienciaRequestDTO toRequestDto(OutraProficienciaModel outraProficienciaModel) {
        return modelMapper.map(outraProficienciaModel, OutraProficienciaRequestDTO.class);
    }

    public OutraProficienciaModel toEntity(OutraProficienciaResponseDTO outraProficienciaResponseDTO) {
        return modelMapper.map(outraProficienciaResponseDTO, OutraProficienciaModel.class);
    }

    public OutraProficienciaModel toEntity(OutraProficienciaRequestDTO outraProficienciaRequestDTO) {
        return modelMapper.map(outraProficienciaRequestDTO, OutraProficienciaModel.class);
    }
}
