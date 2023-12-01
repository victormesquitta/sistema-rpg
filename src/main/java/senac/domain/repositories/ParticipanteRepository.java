package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.ParticipanteModel;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteModel, Integer> {
    boolean existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(Integer codUsuario, Integer codCampanha);
    List<ParticipanteModel> findByCampanhaModel_CodCampanha(Integer codCampanha);

}
