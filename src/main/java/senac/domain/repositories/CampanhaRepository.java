package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.CampanhaModel;

@Repository
public interface CampanhaRepository extends JpaRepository<CampanhaModel, Integer>{


}