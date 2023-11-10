package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.PericiasModel;

@Repository
public interface PericiasRepository extends JpaRepository<PericiasModel, Integer> {
}
