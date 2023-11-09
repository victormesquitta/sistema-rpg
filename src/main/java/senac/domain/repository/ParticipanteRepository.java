package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
}
