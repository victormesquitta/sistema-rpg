package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.AtaquesConjuracaoRequestDTO;
import senac.domain.dtos.responses.AtaquesConjuracaoResponseDTO;
import senac.domain.mappers.AtaquesConjuracaoMapper;
import senac.domain.models.AtaquesConjuracaoModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.AtaquesConjuracaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtaquesConjuracaoService {

    private final AtaquesConjuracaoRepository ataquesConjuracaoRepository;
    private final AtaquesConjuracaoMapper ataquesConjuracaoMapper;
    private final PersonagemService personagemService;

    @Autowired
    public AtaquesConjuracaoService(AtaquesConjuracaoRepository ataquesConjuracaoRepository, AtaquesConjuracaoMapper ataquesConjuracaoMapper, PersonagemService personagemService) {
        this.ataquesConjuracaoRepository = ataquesConjuracaoRepository;
        this.ataquesConjuracaoMapper = ataquesConjuracaoMapper;
        this.personagemService = personagemService;
    }

    public List<AtaquesConjuracaoResponseDTO> listarAtaquesConjuracaoResponse() {
        List<AtaquesConjuracaoModel> ataquesConjuracaoList = ataquesConjuracaoRepository.findAll();
        if (ataquesConjuracaoList.isEmpty()) {
            throw new EntityNotFoundException("Nenhum ataque ou conjuração encontrada.");
        }
        return ataquesConjuracaoList.stream()
                .map(ataquesConjuracaoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AtaquesConjuracaoRequestDTO> listarAtaquesConjuracaoRequest() {
        List<AtaquesConjuracaoModel> ataquesConjuracaoList = ataquesConjuracaoRepository.findAll();
        if (ataquesConjuracaoList.isEmpty()) {
            throw new EntityNotFoundException("Nenhum ataque ou conjuração encontrada.");
        }
        return ataquesConjuracaoList.stream()
                .map(ataquesConjuracaoMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public AtaquesConjuracaoResponseDTO obterAtaqueConjuracaoPorIdResponse(int codAtaqueConjuracao) {
        listarAtaquesConjuracaoResponse();
        Optional<AtaquesConjuracaoModel> ataqueConjuracaoOptional = ataquesConjuracaoRepository.findById(codAtaqueConjuracao);
        ataqueConjuracaoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum ataque ou conjuração encontrada para o ID fornecido."));
        return ataqueConjuracaoOptional.map(ataquesConjuracaoMapper::toResponseDto).orElse(null);
    }

    public AtaquesConjuracaoRequestDTO obterAtaqueConjuracaoPorIdRequest(int codAtaqueConjuracao) {
        listarAtaquesConjuracaoResponse();
        Optional<AtaquesConjuracaoModel> ataqueConjuracaoOptional = ataquesConjuracaoRepository.findById(codAtaqueConjuracao);
        ataqueConjuracaoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum ataque ou conjuração encontrada para o ID fornecido."));
        return ataqueConjuracaoOptional.map(ataquesConjuracaoMapper::toRequestDto).orElse(null);
    }

    public AtaquesConjuracaoModel obterAtaqueConjuracaoModelPorId(Integer codAtaqueConjuracao){
        listarAtaquesConjuracaoResponse();
        Optional<AtaquesConjuracaoModel> ataqueConjuracaoOptional = ataquesConjuracaoRepository.findById(codAtaqueConjuracao);
        ataqueConjuracaoOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum ataque ou conjuração encontrada para o ID fornecido."));
        AtaquesConjuracaoRequestDTO ataqueConjuracaoRequestDTO = ataqueConjuracaoOptional.map(ataquesConjuracaoMapper::toRequestDto).orElse(null);
        AtaquesConjuracaoModel ataqueConjuracaoModel = ataquesConjuracaoMapper.toEntity(ataqueConjuracaoRequestDTO);
        ataqueConjuracaoModel.setCodAtaquesConjuracao(codAtaqueConjuracao);
        ataqueConjuracaoModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(ataqueConjuracaoRequestDTO.getCodPersonagem()));
        return ataqueConjuracaoModel;
    }

    public void criarAtaqueConjuracao(AtaquesConjuracaoRequestDTO ataqueConjuracaoRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(ataqueConjuracaoRequestDTO.getCodPersonagem());
        AtaquesConjuracaoModel ataqueConjuracaoModel = ataquesConjuracaoMapper.toEntity(ataqueConjuracaoRequestDTO);
        ataqueConjuracaoModel.setPersonagemModel(personagemModel);
        ataquesConjuracaoRepository.save(ataqueConjuracaoModel);
    }

    public void atualizarAtaqueConjuracao(Integer codAtaqueConjuracao, AtaquesConjuracaoRequestDTO ataqueConjuracaoRequestDTO) {

        AtaquesConjuracaoResponseDTO ataqueConjuracaoExistente = obterAtaqueConjuracaoPorIdResponse(codAtaqueConjuracao);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(ataqueConjuracaoRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(ataqueConjuracaoExistente.getCodPersonagem()))) {
            throw new RuntimeException("O ataque ou conjuração não pode alterar de personagem.");
        }

        AtaquesConjuracaoModel ataqueConjuracaoAtualizado = ataquesConjuracaoMapper.toEntity(ataqueConjuracaoRequestDTO);
        ataqueConjuracaoAtualizado.setCodAtaquesConjuracao(codAtaqueConjuracao);
        ataqueConjuracaoAtualizado.setPersonagemModel(personagemModel);

        ataquesConjuracaoRepository.save(ataqueConjuracaoAtualizado);
    }

    public void excluirAtaqueConjuracao(int codAtaqueConjuracao) {
        obterAtaqueConjuracaoPorIdResponse(codAtaqueConjuracao);
        ataquesConjuracaoRepository.deleteById(codAtaqueConjuracao);
    }
}
