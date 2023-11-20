package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.ambos.PersonagemDTO;
import senac.domain.dtos.ambos.RolagemDTO;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.mappers.RolagemMapper;
import senac.domain.models.PersonagemModel;
import senac.domain.models.RolagemModel;
import senac.domain.repositories.RolagensRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolagemService {

    private final RolagensRepository rolagensRepository;
    private final RolagemMapper rolagemMapper;
    private final PersonagemService personagemService;
    private final PersonagemMapper personagemMapper;
    @Autowired
    public RolagemService(RolagensRepository rolagensRepository, RolagemMapper rolagemMapper, PersonagemService personagemService, PersonagemMapper personagemMapper) {
        this.rolagensRepository = rolagensRepository;
        this.rolagemMapper = rolagemMapper;
        this.personagemService = personagemService;
        this.personagemMapper = personagemMapper;
    }

    public List<RolagemDTO> listarRolagens(){
        List<RolagemModel> rolagens = rolagensRepository.findAll();
        if(rolagens.isEmpty()){
            throw new EntityNotFoundException("Nenhuma rolagem feita.");
        }
        return rolagens.stream()
                .map(rolagemMapper::toDto)
                .collect(Collectors.toList());
    }

    public RolagemDTO obterRolagemPorId(Integer codRolagem){
        listarRolagens();
        Optional<RolagemModel> rolagemOptional = rolagensRepository.findById(codRolagem);
        rolagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma rolagem encontrada para o ID fornecido."));
        return rolagemOptional.map(rolagemMapper::toDto).orElse(null);
    }

    public void criarRolagem(RolagemDTO rolagemDTO) {
        RolagemModel rolagemModel = rolagemMapper.toEntity(rolagemDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorId(rolagemDTO.getCodPersonagem()));
        rolagemModel.setPersonagemModel(personagemModel);
        rolagensRepository.save(rolagemModel);
    }

    public void atualizarRolagem(Integer codRolagem, RolagemDTO rolagemDTO) {

        RolagemDTO rolagemExistente = obterRolagemPorId(codRolagem);
        PersonagemDTO personagemDTO = personagemService.obterPersonagemPorId(rolagemExistente.getCodPersonagem());

        if(!(personagemDTO.getCodPersonagem().equals(rolagemDTO.getCodPersonagem()))){
            throw new RuntimeException("Rolagem não pode alterar de personagem.");
        }
        RolagemModel rolagensAtualizado = rolagemMapper.toEntity(rolagemDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemDTO);
        rolagensAtualizado.setPersonagemModel(personagemModel);
        rolagensAtualizado.setCodRolagem(codRolagem);
        rolagensRepository.save(rolagensAtualizado);
    }

    public void excluirRolagem(Integer codPersonagem) {
        obterRolagemPorId(codPersonagem);
        rolagensRepository.deleteById(codPersonagem);
    }
}
