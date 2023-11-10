package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.OutraProficienciaModel;

@Repository
public interface OutraProficienciaRepository extends JpaRepository<OutraProficienciaModel, Integer> {
}
