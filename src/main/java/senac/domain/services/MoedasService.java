package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.MoedasRequestDTO;
import senac.domain.dtos.responses.MoedasResponseDTO;
import senac.domain.mappers.MoedasMapper;
import senac.domain.models.MoedasModel;
import senac.domain.models.PersonagemModel;
import senac.domain.repositories.MoedasRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoedasService {

    private final MoedasRepository moedasRepository;
    private final MoedasMapper moedasMapper;
    private final PersonagemService personagemService;

    @Autowired
    public MoedasService(MoedasRepository moedasRepository, MoedasMapper moedasMapper, PersonagemService personagemService) {
        this.moedasRepository = moedasRepository;
        this.moedasMapper = moedasMapper;
        this.personagemService = personagemService;
    }

    public List<MoedasResponseDTO> listarMoedasResponse() {
        List<MoedasModel> moedas = moedasRepository.findAll();
        if (moedas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma moeda encontrada.");
        }
        return moedas.stream()
                .map(moedasMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MoedasRequestDTO> listarMoedasRequest() {
        List<MoedasModel> moedas = moedasRepository.findAll();
        if (moedas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma moeda encontrada.");
        }
        return moedas.stream()
                .map(moedasMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public MoedasResponseDTO obterMoedaPorIdResponse(int codMoeda) {
        listarMoedasResponse();
        Optional<MoedasModel> moedaOptional = moedasRepository.findById(codMoeda);
        moedaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma moeda encontrada para o ID fornecido."));
        return moedaOptional.map(moedasMapper::toResponseDto).orElse(null);
    }

    public MoedasRequestDTO obterMoedaPorIdRequest(int codMoeda) {
        listarMoedasResponse();
        Optional<MoedasModel> moedaOptional = moedasRepository.findById(codMoeda);
        moedaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma moeda encontrada para o ID fornecido."));
        return moedaOptional.map(moedasMapper::toRequestDto).orElse(null);
    }

    public MoedasModel obterMoedasModelPorId(Integer codMoeda){
        listarMoedasResponse();
        Optional<MoedasModel> moedaOptional = moedasRepository.findById(codMoeda);
        moedaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma moeda encontrada para o ID fornecido."));
        MoedasRequestDTO moedasRequestDTO = moedaOptional.map(moedasMapper::toRequestDto).orElse(null);
        MoedasModel moedasModel = moedasMapper.toEntity(moedasRequestDTO);
        moedasModel.setCodMoeda(codMoeda);
        moedasModel.setPersonagemModel(personagemService.obterPersonagemModelPorId(moedasRequestDTO.getCodPersonagem()));
        return moedasModel;
    }

    public void criarMoeda(MoedasRequestDTO moedasRequestDTO){
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(moedasRequestDTO.getCodPersonagem());
        MoedasModel moedasModel = moedasMapper.toEntity(moedasRequestDTO);
        // Verifica se o personagem já possui um registro de moedas
        Optional<MoedasModel> moedasExistenteOptional = moedasRepository.findByPersonagemModel(personagemModel);

        if (moedasExistenteOptional.isPresent()) {
            // Já existe um registro de moedas para o personagem
            throw new RuntimeException("O personagem já possui um registro de moedas.");
        }

        moedasModel.setPersonagemModel(personagemModel);
        moedasRepository.save(moedasModel);
    }

    public void criarMoedaComPersonagem(PersonagemModel personagemModel) {
        MoedasResponseDTO moedasResponseDTO = new MoedasResponseDTO();
        MoedasModel moedasModel = moedasMapper.toEntity(moedasResponseDTO);

        moedasModel.setPo(0);
        moedasModel.setPp(0);
        moedasModel.setPc(0);
        moedasModel.setPl(0);
        moedasModel.setDa(0);

        moedasModel.setPersonagemModel(personagemModel);

        moedasRepository.save(moedasModel);
    }

    public void atualizarMoeda(int codMoeda, MoedasRequestDTO moedaRequestDTO) {
        MoedasResponseDTO moedasExistentes = obterMoedaPorIdResponse(codMoeda);
        PersonagemModel personagemModel = personagemService.obterPersonagemModelPorId(moedaRequestDTO.getCodPersonagem());

        if (!(personagemModel.getCodPersonagem().equals(moedasExistentes.getCodPersonagem()))) {
            throw new RuntimeException("A moeda não pode alterar de personagem.");
        }

        MoedasModel moedasAtualizadas = moedasMapper.toEntity(moedaRequestDTO);
        moedasAtualizadas.setCodMoeda(codMoeda);
        moedasAtualizadas.setPersonagemModel(personagemModel);

        moedasRepository.save(moedasAtualizadas);
    }

    public void excluirMoeda(int codMoeda) {
        obterMoedaPorIdResponse(codMoeda);
        moedasRepository.deleteById(codMoeda);
    }
}
