package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.models.MestreModel;

public interface MestreRepository extends JpaRepository<MestreModel, Integer> {
}
