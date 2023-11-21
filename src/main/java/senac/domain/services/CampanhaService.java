package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.repositories.CampanhaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampanhaService{

    private final CampanhaRepository campanhaRepository;

    private final CampanhaMapper campanhaMapper;

    @Autowired
    public CampanhaService(CampanhaRepository campanhaRepository, CampanhaMapper campanhaMapper) {
        this.campanhaRepository = campanhaRepository;
        this.campanhaMapper = campanhaMapper;
    }

    public List<CampanhaResponseDTO> listarCampanhasResponse() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();
        if(campanhas.isEmpty()){
           throw new EntityNotFoundException("Nenhuma campanha cadastrada ainda.");
        }
        return campanhas.stream()
                .map(campanhaMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public List<CampanhaRequestDTO> listarCampanhasRequest() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();
        if(campanhas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma campanha cadastrada ainda.");
        }
        return campanhas.stream()
                .map(campanhaMapper::toRequestDto)
                .collect(Collectors.toList());
    }


    public CampanhaResponseDTO obterCampanhaPorIdResponse(Integer id) {
        listarCampanhasResponse();
        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(id);
        campanhaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma campanha encontrada para o ID fornecido."));
        return campanhaOptional.map(campanhaMapper::toResponseDto).orElse(null);
    }

    public CampanhaRequestDTO obterCampanhaPorIdRequest(Integer id) {
        listarCampanhasResponse();
        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(id);
        campanhaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma campanha encontrada para o ID fornecido."));
        return campanhaOptional.map(campanhaMapper::toRequestDto).orElse(null);
    }

    public CampanhaModel criarCampanha(CampanhaRequestDTO campanhaRequestDto) {
        CampanhaModel campanhaModel = campanhaMapper.toEntity(campanhaRequestDto);
        campanhaRepository.save(campanhaModel);
        return campanhaModel;
    }

    public void atualizarCampanha(Integer id, CampanhaRequestDTO campanhaRequestDto) {
        obterCampanhaPorIdResponse(id);
        CampanhaModel campanhaAtualizada = campanhaMapper.toEntity(campanhaRequestDto);
        campanhaAtualizada.setCodCampanha(id);
        campanhaRepository.save(campanhaAtualizada);
    }

    public void excluirCampanha(Integer id) {
        obterCampanhaPorIdResponse(id);
        campanhaRepository.deleteById(id);
    }

    public void entrarNaCampanha() {
        // insere o código de entrada e valida se é o que bate com o da campanha
    }




// retorna qtd players online
// retorna qtd players offline
}
