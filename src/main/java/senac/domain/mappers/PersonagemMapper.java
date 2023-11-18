package senac.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.domain.dtos.ambos.PersonagemDTO;
import senac.domain.models.PersonagemModel;
@Component
public class PersonagemMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioMapper personagemMapper;


    public PersonagemDTO toDto(PersonagemModel personagemModel) {
        return modelMapper.map(personagemModel, PersonagemDTO.class);
    }

    public PersonagemModel toEntity(PersonagemDTO personagemDTO) {
        return modelMapper.map(personagemDTO, PersonagemModel.class);
    }
}
