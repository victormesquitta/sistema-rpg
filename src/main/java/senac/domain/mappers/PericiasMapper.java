package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.PericiasRequestDTO;
import senac.domain.dtos.responses.PericiasResponseDTO;
import senac.domain.models.PericiasModel;

@Component
public class PericiasMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PericiasResponseDTO toResponseDto(PericiasModel periciasModel) {
        PericiasResponseDTO periciasResponseDTO = modelMapper.map(periciasModel, PericiasResponseDTO.class);
        periciasResponseDTO.setCodPersonagem(periciasModel.getPersonagemModel().getCodPersonagem());
        return periciasResponseDTO;
    }

    public PericiasRequestDTO toRequestDto(PericiasModel periciasModel) {
        PericiasRequestDTO periciasRequestDTO = modelMapper.map(periciasModel, PericiasRequestDTO.class);
        periciasRequestDTO.setCodPersonagem(periciasModel.getPersonagemModel().getCodPersonagem());
        return periciasRequestDTO;
    }

    public PericiasModel toEntity(PericiasResponseDTO periciasResponseDTO) {
        return modelMapper.map(periciasResponseDTO, PericiasModel.class);
    }

    public PericiasModel toEntity(PericiasRequestDTO periciasRequestDTO) {
        return modelMapper.map(periciasRequestDTO, PericiasModel.class);
    }
}
