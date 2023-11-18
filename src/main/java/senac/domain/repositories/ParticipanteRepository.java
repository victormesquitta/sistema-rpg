package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.ParticipanteModel;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteModel, Integer> {
    boolean existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(Integer codUsuario, Integer codCampanha);

}
