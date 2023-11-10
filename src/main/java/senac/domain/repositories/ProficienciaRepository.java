package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.ProficienciaModel;
@Repository
public interface ProficienciaRepository extends JpaRepository<ProficienciaModel, Integer> {
}
