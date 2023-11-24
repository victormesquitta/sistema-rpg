package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.TalentoTracoRequestDTO;
import senac.domain.dtos.responses.TalentoTracoResponseDTO;
import senac.domain.models.TalentoTracoModel;

@Component
public class TalentoTracoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TalentoTracoResponseDTO toResponseDto(TalentoTracoModel talentoTracoModel) {
        TalentoTracoResponseDTO responseDTO = modelMapper.map(talentoTracoModel, TalentoTracoResponseDTO.class);
        responseDTO.setCodPersonagem(talentoTracoModel.getPersonagemModel().getCodPersonagem());
        return responseDTO;
    }

    public TalentoTracoRequestDTO toRequestDto(TalentoTracoModel talentoTracoModel) {
        TalentoTracoRequestDTO requestDTO = modelMapper.map(talentoTracoModel, TalentoTracoRequestDTO.class);
        requestDTO.setCodPersonagem(talentoTracoModel.getPersonagemModel().getCodPersonagem());
        return requestDTO;
    }

    public TalentoTracoModel toEntity(TalentoTracoResponseDTO talentoTracoResponseDTO) {
        return modelMapper.map(talentoTracoResponseDTO, TalentoTracoModel.class);
    }

    public TalentoTracoModel toEntity(TalentoTracoRequestDTO talentoTracoRequestDTO) {
        return modelMapper.map(talentoTracoRequestDTO, TalentoTracoModel.class);
    }
}
