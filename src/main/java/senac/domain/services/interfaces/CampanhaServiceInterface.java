package senac.domain.services.interfaces;

import senac.domain.dtos.CampanhaDto;

import java.util.List;

public interface CampanhaServiceInterface {
        List<CampanhaDto> listarCampanhas();
        CampanhaDto obterCampanhaPorId(Integer id);
        void criarCampanha(CampanhaDto campanha);
        void atualizarCampanha(Integer id, CampanhaDto campanha);
        void excluirCampanha(Integer id);
        void entrarNaCampanha();
}
