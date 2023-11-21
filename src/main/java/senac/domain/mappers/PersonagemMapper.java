package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.requests.PersonagemRequestDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.models.PersonagemModel;
@Component
public class PersonagemMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioMapper personagemMapper;


    public PersonagemResponseDTO toResponseDto(PersonagemModel personagemModel) {
        return modelMapper.map(personagemModel, PersonagemResponseDTO.class);
    }

    public PersonagemRequestDTO toRequestDto(PersonagemModel personagemModel) {
        return modelMapper.map(personagemModel, PersonagemRequestDTO.class);
    }

    public PersonagemModel toEntity(PersonagemResponseDTO personagemResponseDTO) {
        return modelMapper.map(personagemResponseDTO, PersonagemModel.class);
    }

    public PersonagemModel toEntity(PersonagemRequestDTO personagemRequestDTO) {
        return modelMapper.map(personagemRequestDTO, PersonagemModel.class);
    }
}
