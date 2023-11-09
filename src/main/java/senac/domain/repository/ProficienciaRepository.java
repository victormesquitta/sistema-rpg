package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Proficiencia;
@Repository
public interface ProficienciaRepository extends JpaRepository<Proficiencia, Integer> {
}
