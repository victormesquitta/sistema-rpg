package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.OutraProficiencia;

@Repository
public interface OutraProficienciaRepository extends JpaRepository<OutraProficiencia, Integer> {
}
