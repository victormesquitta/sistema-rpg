package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.AtaquesConjuracaoRequestDTO;
import senac.domain.dtos.responses.AtaquesConjuracaoResponseDTO;
import senac.domain.models.AtaquesConjuracaoModel;

@Component
public class AtaquesConjuracaoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AtaquesConjuracaoResponseDTO toResponseDto(AtaquesConjuracaoModel ataquesConjuracaoModel) {
        AtaquesConjuracaoResponseDTO responseDTO = modelMapper.map(ataquesConjuracaoModel, AtaquesConjuracaoResponseDTO.class);
        responseDTO.setCodPersonagem(ataquesConjuracaoModel.getPersonagemModel().getCodPersonagem());
        return responseDTO;
    }

    public AtaquesConjuracaoRequestDTO toRequestDto(AtaquesConjuracaoModel ataquesConjuracaoModel) {
        AtaquesConjuracaoRequestDTO requestDTO = modelMapper.map(ataquesConjuracaoModel, AtaquesConjuracaoRequestDTO.class);
        requestDTO.setCodPersonagem(ataquesConjuracaoModel.getPersonagemModel().getCodPersonagem());
        return requestDTO;
    }

    public AtaquesConjuracaoModel toEntity(AtaquesConjuracaoResponseDTO responseDTO) {
        return modelMapper.map(responseDTO, AtaquesConjuracaoModel.class);
    }

    public AtaquesConjuracaoModel toEntity(AtaquesConjuracaoRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, AtaquesConjuracaoModel.class);
    }
}
