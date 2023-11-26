package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.EquipamentoRequestDTO;
import senac.domain.dtos.responses.EquipamentoResponseDTO;
import senac.domain.mappers.EquipamentoMapper;
import senac.domain.models.EquipamentoModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.EquipamentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final EquipamentoMapper equipamentoMapper;
    private final PersonagemService personagemService;

    @Autowired
    public EquipamentoService(EquipamentoRepository equipamentoRepository, EquipamentoMapper equipamentoMapper, PersonagemService personagemService) {
        this.equipamentoRepository = equipamentoRepository;
        this.equipamentoMapper = equipamentoMapper;
        this.personagemService = personagemService;
    }

    public List<EquipamentoResponseDTO> listarEquipamentosResponse() {
        List<EquipamentoModel> equipamentos = equipamentoRepository.findAll();
        if (equipamentos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum equipamento encontrado.");
        }
        return equipamentos.stream()
                .map(equipamentoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoRequestDTO> listarEquipamentosRequest() {
        List<EquipamentoModel> equipamentos = equipamentoRepository.findAll();
        if (equipamentos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum equipamento encontrado.");
        }
        return equipamentos.stream()
                .map(equipamentoMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public EquipamentoResponseDTO obterEquipamentoPorIdResponse(int codEquipamento) {
        listarEquipamentosResponse();
        Optional<EquipamentoModel> equipamentoOptional = equipamentoRepository.findById(codEquipamento);
        equipamentoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum equipamento encontrado para o ID fornecido."));
        return equipamentoOptional.map(equipamentoMapper::toResponseDto).orElse(null);
    }

    public EquipamentoRequestDTO obterEquipamentoPorIdRequest(int codEquipamento) {
        listarEquipamentosResponse();
        Optional<EquipamentoModel> equipamentoOptional = equipamentoRepository.findById(codEquipamento);
        equipamentoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum equipamento encontrado para o ID fornecido."));
        return equipamentoOptional.map(equipamentoMapper::toRequestDto).orElse(null);
    }

    public EquipamentoModel obterEquipamentoModelPorId(Integer codEquipamento){
        listarEquipamentosResponse();
        Optional<EquipamentoModel> equipamentoOptional = equipamentoRepository.findById(codEquipamento);
        equipamentoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum equipamento encontrado para o ID fornecido."));
        EquipamentoRequestDTO equipamentoRequestDTO = equipamentoOptional.map(equipamentoMapper::toRequestDto).orElse(null);
        EquipamentoModel equipamentoModel = equipamentoMapper.toEntity(equipamentoRequestDTO);
        equipamentoModel.setCodEquipamento(codEquipamento);
        equipamentoModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(equipamentoRequestDTO.getCodPersonagem()));
        return equipamentoModel;
    }

    public void criarEquipamento(EquipamentoRequestDTO equipamentoRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(equipamentoRequestDTO.getCodPersonagem());
        EquipamentoModel equipamentoModel = equipamentoMapper.toEntity(equipamentoRequestDTO);
        equipamentoModel.setPersonagemModel(personagemModel);
        equipamentoRepository.save(equipamentoModel);
    }

    public void atualizarEquipamento(Integer codEquipamento, EquipamentoRequestDTO equipamentoRequestDTO) {

        EquipamentoResponseDTO equipamentoExistente = obterEquipamentoPorIdResponse(codEquipamento);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(equipamentoRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(equipamentoExistente.getCodPersonagem()))) {
            throw new RuntimeException("O equipamento não pode alterar de personagem.");
        }

        EquipamentoModel equipamentoAtualizado = equipamentoMapper.toEntity(equipamentoRequestDTO);
        equipamentoAtualizado.setCodEquipamento(codEquipamento);
        equipamentoAtualizado.setPersonagemModel(personagemModel);

        equipamentoRepository.save(equipamentoAtualizado);
    }

    public void excluirEquipamento(int codEquipamento) {
        obterEquipamentoPorIdResponse(codEquipamento);
        equipamentoRepository.deleteById(codEquipamento);
    }
}
