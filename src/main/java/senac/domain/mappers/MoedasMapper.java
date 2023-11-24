package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.MoedasRequestDTO;
import senac.domain.dtos.responses.MoedasResponseDTO;
import senac.domain.models.MoedasModel;

@Component
public class MoedasMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MoedasResponseDTO toResponseDto(MoedasModel moedasModel) {
        MoedasResponseDTO moedasResponseDTO = modelMapper.map(moedasModel, MoedasResponseDTO.class);
        moedasResponseDTO.setCodPersonagem(moedasModel.getPersonagemModel().getCodPersonagem());
        return moedasResponseDTO;
    }

    public MoedasRequestDTO toRequestDto(MoedasModel moedasModel) {
        MoedasRequestDTO moedasRequestDTO = modelMapper.map(moedasModel, MoedasRequestDTO.class);
        moedasRequestDTO.setCodPersonagem(moedasModel.getPersonagemModel().getCodPersonagem());
        return moedasRequestDTO;
    }

    public MoedasModel toEntity(MoedasResponseDTO moedasResponseDTO) {
        return modelMapper.map(moedasResponseDTO, MoedasModel.class);
    }

    public MoedasModel toEntity(MoedasRequestDTO moedasRequestDTO) {
        return modelMapper.map(moedasRequestDTO, MoedasModel.class);
    }
}
