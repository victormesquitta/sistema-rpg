package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.EquipamentoRequestDTO;
import senac.domain.dtos.responses.EquipamentoResponseDTO;
import senac.domain.models.EquipamentoModel;

@Component
public class EquipamentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EquipamentoResponseDTO toResponseDto(EquipamentoModel equipamentoModel) {
        EquipamentoResponseDTO equipamentoResponseDTO = modelMapper.map(equipamentoModel, EquipamentoResponseDTO.class);
        equipamentoResponseDTO.setCodPersonagem(equipamentoModel.getPersonagemModel().getCodPersonagem());
        return equipamentoResponseDTO;
    }

    public EquipamentoRequestDTO toRequestDto(EquipamentoModel equipamentoModel) {
        EquipamentoRequestDTO equipamentoRequestDTO = modelMapper.map(equipamentoModel, EquipamentoRequestDTO.class);
        equipamentoRequestDTO.setCodPersonagem(equipamentoModel.getPersonagemModel().getCodPersonagem());
        return equipamentoRequestDTO;
    }

    public EquipamentoModel toEntity(EquipamentoResponseDTO equipamentoResponseDTO) {
        return modelMapper.map(equipamentoResponseDTO, EquipamentoModel.class);
    }

    public EquipamentoModel toEntity(EquipamentoRequestDTO equipamentoRequestDTO) {
        return modelMapper.map(equipamentoRequestDTO, EquipamentoModel.class);
    }
}
