package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Rolagens;

@Repository
public interface RolagensRepository extends JpaRepository<Rolagens, Integer> {
}
