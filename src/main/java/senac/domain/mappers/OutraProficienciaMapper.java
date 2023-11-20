package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.OutraProficienciaDTO;
import senac.domain.models.OutraProficienciaModel;

@Component
public class OutraProficienciaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OutraProficienciaDTO toDto(OutraProficienciaModel outraProficienciaModel) {
        return modelMapper.map(outraProficienciaModel, OutraProficienciaDTO.class);
    }

    public OutraProficienciaModel toEntity(OutraProficienciaDTO outraProficienciaDTO) {
        return modelMapper.map(outraProficienciaDTO, OutraProficienciaModel.class);
    }
}
