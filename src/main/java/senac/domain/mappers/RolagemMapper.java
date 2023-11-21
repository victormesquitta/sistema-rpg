package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.RolagemRequestDTO;
import senac.domain.dtos.responses.RolagemResponseDTO;
import senac.domain.models.RolagemModel;
@Component
public class RolagemMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RolagemResponseDTO toResponseDto(RolagemModel rolagemModel) {
        return modelMapper.map(rolagemModel, RolagemResponseDTO.class);
    }

    public RolagemRequestDTO toRequestDto(RolagemModel rolagemModel) {
        return modelMapper.map(rolagemModel, RolagemRequestDTO.class);
    }

    public RolagemModel toEntity(RolagemResponseDTO rolagemResponseDTO) {
        return modelMapper.map(rolagemResponseDTO, RolagemModel.class);
    }

    public RolagemModel toEntity(RolagemRequestDTO rolagemRequestDTO) {
        return modelMapper.map(rolagemRequestDTO, RolagemModel.class);
    }

}
