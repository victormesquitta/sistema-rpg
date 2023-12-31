package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.models.CampanhaModel;
import senac.domain.repositories.CampanhaRepository;

@Component
public class CampanhaMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CampanhaRepository campanhaRepository;

    public CampanhaResponseDTO toResponseDto(CampanhaModel campanhaModel) {
        return modelMapper.map(campanhaModel, CampanhaResponseDTO.class);
    }


    public CampanhaRequestDTO toRequestDto(CampanhaModel campanhaModel) {
        return modelMapper.map(campanhaModel, CampanhaRequestDTO.class);
    }

    public CampanhaModel toEntity(CampanhaRequestDTO campanhaRequestDto) {
        return modelMapper.map(campanhaRequestDto, CampanhaModel.class);
    }
    public CampanhaModel toEntity(CampanhaResponseDTO campanhaResponseDto) {
        return modelMapper.map(campanhaResponseDto, CampanhaModel.class);
    }

}

