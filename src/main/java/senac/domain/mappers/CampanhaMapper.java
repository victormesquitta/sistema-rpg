package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.CampanhaDto;
import senac.domain.models.CampanhaModel;

@Component
public class CampanhaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CampanhaDto toDto(CampanhaModel campanhaModel) {
        CampanhaDto campanhaDto = modelMapper.map(campanhaModel, CampanhaDto.class);
        campanhaDto.setCodCampanha(campanhaModel.getCodCampanha());
        return campanhaDto;
    }

    public CampanhaModel toEntity(CampanhaDto campanhaDto) {
        return modelMapper.map(campanhaDto, CampanhaModel.class);
    }
}

