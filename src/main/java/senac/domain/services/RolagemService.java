package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.PersonagemRequestDTO;
import senac.domain.dtos.requests.RolagemRequestDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.dtos.responses.RolagemResponseDTO;
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

    public List<RolagemResponseDTO> listarRolagensResponse(){
        List<RolagemModel> rolagens = rolagensRepository.findAll();
        if(rolagens.isEmpty()){
            throw new EntityNotFoundException("Nenhuma rolagem feita.");
        }
        return rolagens.stream()
                .map(rolagemMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<RolagemRequestDTO> listarRolagensRequest(){
        List<RolagemModel> rolagens = rolagensRepository.findAll();
        if(rolagens.isEmpty()){
            throw new EntityNotFoundException("Nenhuma rolagem feita.");
        }
        return rolagens.stream()
                .map(rolagemMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public RolagemResponseDTO obterRolagemPorIdResponse(Integer codRolagem){
        listarRolagensResponse();
        Optional<RolagemModel> rolagemOptional = rolagensRepository.findById(codRolagem);
        rolagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma rolagem encontrada para o ID fornecido."));
        return rolagemOptional.map(rolagemMapper::toResponseDto).orElse(null);
    }

    public RolagemRequestDTO obterRolagemPorIdRequest(Integer codRolagem){
        listarRolagensResponse();
        Optional<RolagemModel> rolagemOptional = rolagensRepository.findById(codRolagem);
        rolagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma rolagem encontrada para o ID fornecido."));
        return rolagemOptional.map(rolagemMapper::toRequestDto).orElse(null);
    }

    public void criarRolagem(RolagemRequestDTO rolagemRequestDTO) {
        RolagemModel rolagemModel = rolagemMapper.toEntity(rolagemRequestDTO);
        PersonagemModel personagemModel = personagemMapper.toEntity(personagemService.obterPersonagemPorIdRequest(rolagemRequestDTO.getCodPersonagem()));
        rolagemModel.setPersonagemModel(personagemModel);
        rolagensRepository.save(rolagemModel);
    }

    public void atualizarRolagem(Integer codRolagem, RolagemRequestDTO rolagemDTO) {

        RolagemRequestDTO rolagemExistente = obterRolagemPorIdRequest(codRolagem);
        PersonagemResponseDTO personagemDTO = personagemService.obterPersonagemPorIdResponse(rolagemExistente.getCodPersonagem());

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
        obterRolagemPorIdResponse(codPersonagem);
        rolagensRepository.deleteById(codPersonagem);
    }
}
