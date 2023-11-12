package senac.domain.services;

import senac.domain.dtos.CampanhaRecordDto;
import senac.domain.models.CampanhaModel;

import java.util.List;

public interface CampanhaServiceInterface {
        List<CampanhaRecordDto> listarCampanhas();
        CampanhaRecordDto obterCampanhaPorId(Integer id);
        void criarCampanha(CampanhaRecordDto campanha);
        void atualizarCampanha(Integer id, CampanhaRecordDto campanha);
        void excluirCampanha(Integer id);
        void entrarNaCampanha();
}
