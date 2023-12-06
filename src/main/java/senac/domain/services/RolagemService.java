package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.RolagemRequestDTO;
import senac.domain.dtos.responses.RolagemResponseDTO;
import senac.domain.funcionalidades.funcionalidade_dado.TipoDado;
import senac.domain.mappers.PersonagemMapper;
import senac.domain.mappers.RolagemMapper;
import senac.domain.models.PersonagemModel;
import senac.domain.models.RolagemModel;
import senac.domain.repositories.RolagensRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        System.out.println(rolagemRequestDTO);
        rolagemModel.setPersonagemModel(personagemModel);

        //set resultado da rolagem e os valore gerados
        calculaRolagem(rolagemModel);
        rolagemModel.setData(LocalDateTime.now());

        rolagensRepository.save(rolagemModel);
    }
    public TipoDado converteTipoDado(String tipoDado){
        // aceita tanto letra minuscula quanto maiuscula - D20/d20
        return switch (tipoDado) {
            case "D4" -> TipoDado.D4;
            case "D6" -> TipoDado.D6;
            case "D8" -> TipoDado.D8;
            case "D12" -> TipoDado.D12;
            case "D20" -> TipoDado.D20;
            case "D100" -> TipoDado.D100;
            case "d4" -> TipoDado.D4;
            case "d6" -> TipoDado.D6;
            case "d8" -> TipoDado.D8;
            case "d12" -> TipoDado.D12;
            case "d20" -> TipoDado.D20;
            case "d100" -> TipoDado.D100;
            default -> throw new RuntimeException("Opção de dado inválida!");
        };
    }

    public RolagemModel calculaRolagem(RolagemModel rolagemModel){
        Integer qtdRolagens = rolagemModel.getQtdRolagens();
        TipoDado tipoDado = converteTipoDado(rolagemModel.getTipoDado());
        ArrayList<Integer> valoresDados = new ArrayList<>();
        int valorTotRolagem = 0;
        for (int i = 0; i < qtdRolagens; i++) {
            int numeroRandomizado = (int) (Math.random() * tipoDado.getLados()) + 1;
            valoresDados.add(numeroRandomizado);
            valorTotRolagem += numeroRandomizado;
        }
        rolagemModel.setResultRolagem(valorTotRolagem);
        rolagemModel.setValoresGerados(valoresDados.toString().replace('[', '(').replace(']', ')'));
        return rolagemModel;
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
