package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.TalentoTracoRequestDTO;
import senac.domain.dtos.responses.TalentoTracoResponseDTO;
import senac.domain.mappers.TalentoTracoMapper;
import senac.domain.models.PersonagemModel;
import senac.domain.models.TalentoTracoModel;
import senac.domain.repositories.TalentoTracoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TalentoTracoService {

    private final TalentoTracoRepository talentoTracoRepository;
    private final TalentoTracoMapper talentoTracoMapper;
    private final PersonagemService personagemService;

    @Autowired
    public TalentoTracoService(TalentoTracoRepository talentoTracoRepository, TalentoTracoMapper talentoTracoMapper, PersonagemService personagemService) {
        this.talentoTracoRepository = talentoTracoRepository;
        this.talentoTracoMapper = talentoTracoMapper;
        this.personagemService = personagemService;
    }

    public List<TalentoTracoResponseDTO> listarTalentosTracosResponse() {
        List<TalentoTracoModel> talentosTracos = talentoTracoRepository.findAll();
        if (talentosTracos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum talento ou traço encontrado.");
        }
        return talentosTracos.stream()
                .map(talentoTracoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<TalentoTracoRequestDTO> listarTalentosTracosRequest() {
        List<TalentoTracoModel> talentosTracos = talentoTracoRepository.findAll();
        if (talentosTracos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum talento ou traço encontrado.");
        }
        return talentosTracos.stream()
                .map(talentoTracoMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public TalentoTracoResponseDTO obterTalentoTracoPorIdResponse(int codTalentoTraco) {
        listarTalentosTracosResponse();
        Optional<TalentoTracoModel> talentoTracoOptional = talentoTracoRepository.findById(codTalentoTraco);
        talentoTracoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum talento ou traço encontrado para o ID fornecido."));
        return talentoTracoOptional.map(talentoTracoMapper::toResponseDto).orElse(null);
    }

    public TalentoTracoRequestDTO obterTalentoTracoPorIdRequest(int codTalentoTraco) {
        listarTalentosTracosResponse();
        Optional<TalentoTracoModel> talentoTracoOptional = talentoTracoRepository.findById(codTalentoTraco);
        talentoTracoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum talento ou traço encontrado para o ID fornecido."));
        return talentoTracoOptional.map(talentoTracoMapper::toRequestDto).orElse(null);
    }

    public TalentoTracoModel obterTalentoTracoModelPorId(Integer codTalentoTraco) {
        listarTalentosTracosResponse();
        Optional<TalentoTracoModel> talentoTracoOptional = talentoTracoRepository.findById(codTalentoTraco);
        talentoTracoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum talento ou traço encontrado para o ID fornecido."));
        TalentoTracoRequestDTO talentoTracoRequestDTO = talentoTracoOptional.map(talentoTracoMapper::toRequestDto).orElse(null);
        TalentoTracoModel talentoTracoModel = talentoTracoMapper.toEntity(talentoTracoRequestDTO);
        talentoTracoModel.setCodTalentoTraco(codTalentoTraco);
        talentoTracoModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(talentoTracoRequestDTO.getCodPersonagem()));
        return talentoTracoModel;
    }

    public void criarTalentoTraco(TalentoTracoRequestDTO talentoTracoRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(talentoTracoRequestDTO.getCodPersonagem());
        TalentoTracoModel talentoTracoModel = talentoTracoMapper.toEntity(talentoTracoRequestDTO);
        talentoTracoModel.setPersonagemModel(personagemModel);
        talentoTracoRepository.save(talentoTracoModel);
    }

    public void atualizarTalentoTraco(Integer codTalentoTraco, TalentoTracoRequestDTO talentoTracoRequestDTO) {
        TalentoTracoResponseDTO talentoTracoExistente = obterTalentoTracoPorIdResponse(codTalentoTraco);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(talentoTracoRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(talentoTracoExistente.getCodPersonagem()))) {
            throw new RuntimeException("O talento ou traço não pode alterar de personagem.");
        }

        TalentoTracoModel talentoTracoAtualizado = talentoTracoMapper.toEntity(talentoTracoRequestDTO);
        talentoTracoAtualizado.setCodTalentoTraco(codTalentoTraco);
        talentoTracoAtualizado.setPersonagemModel(personagemModel);

        talentoTracoRepository.save(talentoTracoAtualizado);
    }

    public void excluirTalentoTraco(int codTalentoTraco) {
        obterTalentoTracoPorIdResponse(codTalentoTraco);
        talentoTracoRepository.deleteById(codTalentoTraco);
    }
}
