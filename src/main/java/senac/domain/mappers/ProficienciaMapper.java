package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.ProficienciaDTO;
import senac.domain.models.ProficienciaModel;
@Component
public class ProficienciaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProficienciaDTO toDto(ProficienciaModel proficienciaModel) {
        return modelMapper.map(proficienciaModel, ProficienciaDTO.class);
    }

    public ProficienciaModel toEntity(ProficienciaDTO proficienciaDTO) {
        return modelMapper.map(proficienciaDTO, ProficienciaModel.class);
    }

}
