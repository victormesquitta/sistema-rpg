package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.RegrasDTO;
import senac.domain.models.RegrasModel;

@Component
public class RegrasMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public RegrasMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RegrasDTO toRequestDto(RegrasModel regrasModel) {
        return modelMapper.map(regrasModel, RegrasDTO.class);
    }

    public RegrasModel toEntity(RegrasDTO regrasDto) {
        return modelMapper.map(regrasDto, RegrasModel.class);
    }
}
