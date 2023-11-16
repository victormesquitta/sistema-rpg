package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.CampanhaDTO;
import senac.domain.dtos.CampanhaRespostaDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.repositories.CampanhaRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampanhaService{
    private final UsuarioService usuarioService;

    private final CampanhaRepository campanhaRepository;

    private final CampanhaMapper campanhaMapper;

    @Autowired
    public CampanhaService(UsuarioService usuarioService, CampanhaRepository campanhaRepository, CampanhaMapper campanhaMapper) {
        this.usuarioService = usuarioService;
        this.campanhaRepository = campanhaRepository;
        this.campanhaMapper = campanhaMapper;
    }

    public List<CampanhaRespostaDTO> listarCampanhas() {
        List<CampanhaModel> campanhas = campanhaRepository.findAll();
        if(campanhas.isEmpty()){
           throw new EntityNotFoundException("Nenhuma campanha cadastrada ainda.");
        }
        return campanhas.stream()
                .map(campanhaMapper::toRespostaDto)
                .collect(Collectors.toList());
    }


    public CampanhaRespostaDTO obterCampanhaPorId(Integer id) {
        listarCampanhas();
        Optional<CampanhaModel> campanhaOptional = campanhaRepository.findById(id);
        campanhaOptional.orElseThrow(() -> new EntityNotFoundException("Nenhuma campanha encontrada para o ID fornecido."));
        return campanhaOptional.map(campanhaMapper::toRespostaDto).orElse(null);
    }

    public void criarCampanha(CampanhaDTO campanhaDto) {
        CampanhaModel campanhaModel = campanhaMapper.toEntity(campanhaDto);

        campanhaRepository.save(campanhaModel);
    }

    public void atualizarCampanha(Integer id, CampanhaDTO campanhaDto) {
        obterCampanhaPorId(id);
        CampanhaModel campanhaAtualizada = campanhaMapper.toEntity(campanhaDto);
        campanhaAtualizada.setCodCampanha(id);
        campanhaRepository.save(campanhaAtualizada);
    }

    public void excluirCampanha(Integer id) {
        obterCampanhaPorId(id);
        campanhaRepository.deleteById(id);
    }

    public void entrarNaCampanha() {
        // insere o código de entrada e valida se é o que bate com o da campanha
    }




// retorna qtd players online
// retorna qtd players offline
}
