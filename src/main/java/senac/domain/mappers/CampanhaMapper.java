package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.CampanhaDTO;
import senac.domain.dtos.CampanhaRespostaDTO;
import senac.domain.models.CampanhaModel;

@Component
public class CampanhaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CampanhaRespostaDTO toRespostaDto(CampanhaModel campanhaModel) {
        CampanhaRespostaDTO campanharRespostaDto = modelMapper.map(campanhaModel, CampanhaRespostaDTO.class);
        return campanharRespostaDto;
    }

    public CampanhaModel toEntity(CampanhaDTO campanhaDto) {
        return modelMapper.map(campanhaDto, CampanhaModel.class);
    }
    public CampanhaModel toEntity(CampanhaRespostaDTO campanhaRespostaDto) {
        return modelMapper.map(campanhaRespostaDto, CampanhaModel.class);
    }
}

