package senac.domain.services;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final CampanhaMapper campanhaMapper;

    @Autowired
    public CampanhaService(CampanhaMapper campanhaMapper) {
        this.campanhaMapper = campanhaMapper;
    }

    @Override
    public List<CampanhaDto> listarCampanhas() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();
        return campanhas.stream()
                .map(campanhaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CampanhaDto obterCampanhaPorId(Integer id) {
        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(id);
        if (campanhaOptional.isPresent()) {
            CampanhaModel campanha = campanhaOptional.get();
            return modelMapper.map(campanha, CampanhaDto.class);
        } else {
            return null; // Ou lançar uma exceção, dependendo do seu requisito.
        }
    }

    @Override
    public void criarCampanha(CampanhaDto campanhaDto) {
        var campanhaModel = new CampanhaModel();
        BeanUtils.copyProperties(campanhaDto, campanhaModel);
        campanhaRepository.save(campanhaModel);
    }

    @Override
    public void atualizarCampanha(Integer id, CampanhaDto campanhaDto) {
        CampanhaModel campanhaExistente = campanhaRepository.findById(id).orElse(null);
        if (campanhaExistente != null) {
            modelMapper.map(campanhaDto, campanhaExistente);
            campanhaRepository.save(campanhaExistente);
        }
    }

    @Override
    public void excluirCampanha(Integer id) {
        campanhaRepository.deleteById(id);
    }

    @Override
    public void entrarNaCampanha() {
        // insere o código de entrada e valida se é o que bate com o da campanha
    }


// retorna qtd players online
// retorna qtd players offline
}
