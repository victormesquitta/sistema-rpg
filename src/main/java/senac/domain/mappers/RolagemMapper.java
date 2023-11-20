package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.RolagemDTO;
import senac.domain.models.RolagemModel;
@Component
public class RolagemMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RolagemDTO toDto(RolagemModel rolagemModel) {
        return modelMapper.map(rolagemModel, RolagemDTO.class);
    }

    public RolagemModel toEntity(RolagemDTO rolagemDTO) {
        return modelMapper.map(rolagemDTO, RolagemModel.class);
    }

}
