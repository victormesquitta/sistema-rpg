package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.RegrasRequestDTO;
import senac.domain.dtos.responses.RegrasResponseDTO;
import senac.domain.models.RegrasModel;

@Component
public class RegrasMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public RegrasMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RegrasResponseDTO toResponseDto(RegrasModel regrasModel) {
        RegrasResponseDTO regrasResponseDTO = modelMapper.map(regrasModel, RegrasResponseDTO.class);
        regrasResponseDTO.setCodParticipante(regrasModel.getParticipanteModel().getCodParticipante());
        return regrasResponseDTO;
    }

    public RegrasRequestDTO toRequestDto(RegrasModel regrasModel) {
        RegrasRequestDTO regrasRequestDTO = modelMapper.map(regrasModel, RegrasRequestDTO.class);
        regrasRequestDTO.setCodParticipante(regrasModel.getParticipanteModel().getCodParticipante());
        return regrasRequestDTO;
    }

    public RegrasModel toEntity(RegrasResponseDTO regrasResponseDTO) {
        return modelMapper.map(regrasResponseDTO, RegrasModel.class);
    }

    public RegrasModel toEntity(RegrasRequestDTO regrasRequestDTO) {
        return modelMapper.map(regrasRequestDTO, RegrasModel.class);
    }
}
