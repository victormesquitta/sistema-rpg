package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.MagiaRequestDTO;
import senac.domain.dtos.responses.MagiaResponseDTO;
import senac.domain.mappers.MagiaMapper;
import senac.domain.models.MagiaModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.MagiaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MagiaService {

    private final MagiaRepository magiaRepository;
    private final MagiaMapper magiaMapper;
    private final PersonagemService personagemService;

    @Autowired
    public MagiaService(MagiaRepository magiaRepository, MagiaMapper magiaMapper, PersonagemService personagemService) {
        this.magiaRepository = magiaRepository;
        this.magiaMapper = magiaMapper;
        this.personagemService = personagemService;
    }

    public List<MagiaResponseDTO> listarMagiasResponse() {
        List<MagiaModel> magias = magiaRepository.findAll();
        if (magias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma magia encontrada.");
        }
        return magias.stream()
                .map(magiaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MagiaRequestDTO> listarMagiasRequest() {
        List<MagiaModel> magias = magiaRepository.findAll();
        if (magias.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma magia encontrada.");
        }
        return magias.stream()
                .map(magiaMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public MagiaResponseDTO obterMagiaPorIdResponse(int codMagia) {
        listarMagiasResponse();
        Optional<MagiaModel> magiaOptional = magiaRepository.findById(codMagia);
        magiaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma magia encontrada para o ID fornecido."));
        return magiaOptional.map(magiaMapper::toResponseDto).orElse(null);
    }

    public MagiaRequestDTO obterMagiaPorIdRequest(int codMagia) {
        listarMagiasResponse();
        Optional<MagiaModel> magiaOptional = magiaRepository.findById(codMagia);
        magiaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma magia encontrada para o ID fornecido."));
        return magiaOptional.map(magiaMapper::toRequestDto).orElse(null);
    }

    public MagiaModel obterMagiaModelPorId(Integer codMagia){
        listarMagiasResponse();
        Optional<MagiaModel> magiaOptional = magiaRepository.findById(codMagia);
        magiaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma magia encontrada para o ID fornecido."));
        MagiaRequestDTO magiaRequestDTO = magiaOptional.map(magiaMapper::toRequestDto).orElse(null);
        MagiaModel magiaModel = magiaMapper.toEntity(magiaRequestDTO);
        magiaModel.setCodMagia(codMagia);
        magiaModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(magiaRequestDTO.getCodPersonagem()));
        return magiaModel;
    }

    public void criarMagia(MagiaRequestDTO magiaRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(magiaRequestDTO.getCodPersonagem());
        MagiaModel magiaModel = magiaMapper.toEntity(magiaRequestDTO);
        magiaModel.setPersonagemModel(personagemModel);
        magiaRepository.save(magiaModel);
    }

    public void atualizarMagia(Integer codMagia, MagiaRequestDTO magiaRequestDTO) {

        MagiaResponseDTO magiaExistente = obterMagiaPorIdResponse(codMagia);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(magiaRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(magiaExistente.getCodPersonagem()))) {
            throw new RuntimeException("A magia não pode alterar de personagem.");
        }

        MagiaModel magiaAtualizada = magiaMapper.toEntity(magiaRequestDTO);
        magiaAtualizada.setCodMagia(codMagia);
        magiaAtualizada.setPersonagemModel(personagemModel);

        magiaRepository.save(magiaAtualizada);
    }

    public void excluirMagia(int codMagia) {
        obterMagiaPorIdResponse(codMagia);
        magiaRepository.deleteById(codMagia);
    }
}
