package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.RolagemModel;

@Repository
public interface RolagensRepository extends JpaRepository<RolagemModel, Integer> {
}
