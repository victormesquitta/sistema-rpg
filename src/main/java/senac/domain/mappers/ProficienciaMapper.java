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
        ProficienciaResponseDTO proficienciaResponseDTO = modelMapper.map(proficienciaModel, ProficienciaResponseDTO.class);
        proficienciaResponseDTO.setCodPersonagem(proficienciaModel.getPersonagemModel().getCodPersonagem());
        return proficienciaResponseDTO;
    }

    public ProficienciaRequestDTO toRequestDto(ProficienciaModel proficienciaModel) {
        ProficienciaRequestDTO proficienciaRequestDTO = modelMapper.map(proficienciaModel, ProficienciaRequestDTO.class);
        proficienciaRequestDTO.setCodPersonagem(proficienciaModel.getPersonagemModel().getCodPersonagem());
        return proficienciaRequestDTO;
    }

    public ProficienciaModel toEntity(ProficienciaResponseDTO proficienciaResponseDTO) {
        return modelMapper.map(proficienciaResponseDTO, ProficienciaModel.class);
    }

    public ProficienciaModel toEntity(ProficienciaRequestDTO proficienciaRequestDTO) {
        return modelMapper.map(proficienciaRequestDTO, ProficienciaModel.class);
    }

}
