package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.AtributosRequestDTO;
import senac.domain.dtos.responses.AtributosResponseDTO;
import senac.domain.models.AtributosModel;

@Component
public class AtributosMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AtributosResponseDTO toResponseDto(AtributosModel atributosModel) {
        AtributosResponseDTO atributosResponseDTO = modelMapper.map(atributosModel, AtributosResponseDTO.class);
        atributosResponseDTO.setCodPersonagem(atributosModel.getPersonagemModel().getCodPersonagem());
        return atributosResponseDTO;
    }

    public AtributosRequestDTO toRequestDto(AtributosModel atributosModel) {
        AtributosRequestDTO atributosRequestDTO = modelMapper.map(atributosModel, AtributosRequestDTO.class);
        atributosRequestDTO.setCodPersonagem(atributosModel.getPersonagemModel().getCodPersonagem());
        return atributosRequestDTO;
    }

    public AtributosModel toEntity(AtributosResponseDTO atributosResponseDTO) {
        return modelMapper.map(atributosResponseDTO, AtributosModel.class);
    }

    public AtributosModel toEntity(AtributosRequestDTO atributosRequestDTO) {
        return modelMapper.map(atributosRequestDTO, AtributosModel.class);
    }
}
