package senac.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.CampanhaRecordDto;
import senac.domain.models.CampanhaModel;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.services.interfaces.CampanhaServiceInterface;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampanhaService implements CampanhaServiceInterface {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CampanhaRecordDto> listarCampanhas() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();

        // Converte a lista de modelos de campanha para uma lista de DTOs usando o ModelMapper
        return campanhas.stream()
                .map(campanha -> modelMapper.map(campanha, CampanhaRecordDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CampanhaRecordDto obterCampanhaPorId(Integer id) {
        CampanhaModel campanha = campanhaRepository.findById(id).orElse(null);
        return (campanha != null) ? modelMapper.map(campanha, CampanhaRecordDto.class) : null;
    }

    @Override
    public void criarCampanha(CampanhaRecordDto campanhaRecordDto) {
        CampanhaModel campanha = modelMapper.map(campanhaRecordDto, CampanhaModel.class);
        campanhaRepository.save(campanha);
    }

    @Override
    public void atualizarCampanha(Integer id, CampanhaRecordDto campanhaRecordDto) {
        CampanhaModel campanhaExistente = campanhaRepository.findById(id).orElse(null);
        if (campanhaExistente != null) {
            modelMapper.map(campanhaRecordDto, campanhaExistente);
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
