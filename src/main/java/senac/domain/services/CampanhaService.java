package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.CampanhaDto;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.services.interfaces.CampanhaServiceInterface;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampanhaService implements CampanhaServiceInterface {

    private final CampanhaRepository campanhaRepository;

    private final CampanhaMapper campanhaMapper;

    @Autowired
    public CampanhaService(CampanhaRepository campanhaRepository, CampanhaMapper campanhaMapper) {
        this.campanhaRepository = campanhaRepository;
        this.campanhaMapper = campanhaMapper;
    }

    @Override
    public List<CampanhaDto> listarCampanhas() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();
        if(campanhas.isEmpty()){
           throw new EntityNotFoundException("Nenhuma campanha cadastrada ainda.");
        }
        return campanhas.stream()
                .map(campanhaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CampanhaDto obterCampanhaPorId(Integer id) {
        listarCampanhas();
        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(id);
        campanhaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma campanha encontrada para o ID fornecido."));
        return campanhaOptional.map(campanhaMapper::toDto).orElse(null);
    }

    @Override
    public void criarCampanha(CampanhaDto campanhaDto) {
        CampanhaModel campanhaModel = campanhaMapper.toEntity(campanhaDto);
        campanhaRepository.save(campanhaModel);
    }

    @Override
    public void atualizarCampanha(Integer id, CampanhaDto campanhaDto) {
        obterCampanhaPorId(id);
        CampanhaModel campanhaAtualizada = campanhaMapper.toEntity(campanhaDto);
        campanhaAtualizada.setCodCampanha(id);
        campanhaRepository.save(campanhaAtualizada);
    }

    @Override
    public void excluirCampanha(Integer id) {
        obterCampanhaPorId(id);
        campanhaRepository.deleteById(id);
    }

    @Override
    public void entrarNaCampanha() {
        // insere o código de entrada e valida se é o que bate com o da campanha
    }




// retorna qtd players online
// retorna qtd players offline
}
