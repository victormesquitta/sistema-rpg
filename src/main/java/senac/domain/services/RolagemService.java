package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.RolagemRequestDTO;
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

    public RolagemModel obterRolagemModelPorId(Integer codRolagem){
        listarRolagensResponse();
        Optional<RolagemModel> rolagemOptional = rolagensRepository.findById(codRolagem);
        rolagemOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma rolagem encontrada para o ID fornecido."));
        RolagemRequestDTO rolagemRequestDTO = rolagemOptional.map(rolagemMapper::toRequestDto).orElse(null);
        RolagemModel rolagemModel = rolagemMapper.toEntity(rolagemRequestDTO);
        rolagemModel.setCodRolagem(codRolagem);
        rolagemModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(rolagemRequestDTO.getCodPersonagem()));
        return rolagemModel;
    }

    public void criarRolagem(RolagemRequestDTO rolagemRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(rolagemRequestDTO.getCodPersonagem());
        RolagemModel rolagemModel = rolagemMapper.toEntity(rolagemRequestDTO);
        rolagemModel.setPersonagemModel(personagemModel);
        rolagensRepository.save(rolagemModel);
    }

    public void atualizarRolagem(Integer codRolagem, RolagemRequestDTO rolagemRequestDTO) {

        RolagemResponseDTO rolagemExistente = obterRolagemPorIdResponse(codRolagem);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(rolagemRequestDTO.getCodPersonagem());

        if(!(personagemModel.getCodPersonagem().equals(rolagemExistente.getCodPersonagem()))){
            throw new RuntimeException("Rolagem não pode alterar de personagem.");
        }

        RolagemModel rolagemAtualizada = rolagemMapper.toEntity(rolagemRequestDTO);
        rolagemAtualizada.setCodRolagem(codRolagem);
        rolagemAtualizada.setPersonagemModel(personagemModel);

        rolagensRepository.save(rolagemAtualizada);
    }

    public void excluirRolagem(Integer codPersonagem) {
        obterRolagemPorIdResponse(codPersonagem);
        rolagensRepository.deleteById(codPersonagem);
    }
}
