package senac.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.AtributosRequestDTO;
import senac.domain.dtos.responses.AtributosResponseDTO;
import senac.domain.mappers.AtributosMapper;
import senac.domain.models.AtributosModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.AtributosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtributosService {

    private final AtributosRepository atributosRepository;
    private final AtributosMapper atributosMapper;
    private final PersonagemService personagemService;

    @Autowired
    public AtributosService(AtributosRepository atributosRepository, AtributosMapper atributosMapper, PersonagemService personagemService) {
        this.atributosRepository = atributosRepository;
        this.atributosMapper = atributosMapper;
        this.personagemService = personagemService;
    }

    public List<AtributosResponseDTO> listarAtributosResponse() {
        List<AtributosModel> atributos = atributosRepository.findAll();
        if (atributos.isEmpty()) {
            throw new RuntimeException("Nenhum atributo encontrado.");
        }
        return atributos.stream()
                .map(atributosMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AtributosRequestDTO> listarAtributosRequest() {
        List<AtributosModel> atributos = atributosRepository.findAll();
        if (atributos.isEmpty()) {
            throw new RuntimeException("Nenhum atributo encontrado.");
        }
        return atributos.stream()
                .map(atributosMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public AtributosResponseDTO obterAtributoPorIdResponse(int codAtributos) {
        listarAtributosResponse();
        Optional<AtributosModel> atributoOptional = atributosRepository.findById(codAtributos);
        atributoOptional.orElseThrow(() -> new RuntimeException("Nenhum atributo encontrado para o ID fornecido."));
        return atributoOptional.map(atributosMapper::toResponseDto).orElse(null);
    }

    public AtributosRequestDTO obterAtributoPorIdRequest(int codAtributos) {
        listarAtributosResponse();
        Optional<AtributosModel> atributoOptional = atributosRepository.findById(codAtributos);
        atributoOptional.orElseThrow(() -> new RuntimeException("Nenhum atributo encontrado para o ID fornecido."));
        return atributoOptional.map(atributosMapper::toRequestDto).orElse(null);
    }

    public AtributosModel obterAtributosModelPorId(Integer codAtributos) {
        listarAtributosResponse();
        Optional<AtributosModel> atributoOptional = atributosRepository.findById(codAtributos);
        atributoOptional.orElseThrow(() -> new RuntimeException("Nenhum atributo encontrado para o ID fornecido."));
        AtributosRequestDTO atributosRequestDTO = atributoOptional.map(atributosMapper::toRequestDto).orElse(null);
        AtributosModel atributosModel = atributosMapper.toEntity(atributosRequestDTO);
        atributosModel.setCodAtributos(codAtributos);
        atributosModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(atributosRequestDTO.getCodPersonagem()));
        return atributosModel;
    }

    public void criarAtributos(AtributosRequestDTO atributosRequestDTO) {
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(atributosRequestDTO.getCodPersonagem());
        AtributosModel atributosModel = atributosMapper.toEntity(atributosRequestDTO);
        // Verifica se o personagem já possui um registro de atributos
        Optional<AtributosModel> atributosExistenteOptional = atributosRepository.findByPersonagemModel(personagemModel);

        if (atributosExistenteOptional.isPresent()) {
            // Já existe um registro de atributos para o personagem
            throw new RuntimeException("O personagem já possui um registro de atributos.");
        }

        atributosModel.setPersonagemModel(personagemModel);
        atributosRepository.save(atributosModel);
    }

    public AtributosModel criarAtributosComPersonagem(PersonagemModel personagemModel) {
        AtributosResponseDTO atributosResponseDTO = new AtributosResponseDTO();
        AtributosModel atributosModel = atributosMapper.toEntity(atributosResponseDTO);

        atributosModel.setForca(0);
        atributosModel.setInteligencia(0);
        atributosModel.setDestreza(0);
        atributosModel.setConstituicao(0);
        atributosModel.setCarisma(0);
        atributosModel.setSabedoria(0);

        atributosModel.setPersonagemModel(personagemModel);

        atributosRepository.save(atributosModel);
        return atributosModel;
    }

    public void atualizarAtributo(int codAtributos, AtributosRequestDTO atributosRequestDTO) {
        AtributosResponseDTO atributosExistentes = obterAtributoPorIdResponse(codAtributos);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(atributosRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(atributosExistentes.getCodPersonagem()))) {
            throw new RuntimeException("O atributo não pode alterar de personagem.");
        }

        AtributosModel atributosAtualizados = atributosMapper.toEntity(atributosRequestDTO);
        atributosAtualizados.setCodAtributos(codAtributos);
        atributosAtualizados.setPersonagemModel(personagemModel);

        atributosRepository.save(atributosAtualizados);
    }

    public void excluirAtributo(int codAtributos) {
        obterAtributoPorIdResponse(codAtributos);
        atributosRepository.deleteById(codAtributos);
    }
}
