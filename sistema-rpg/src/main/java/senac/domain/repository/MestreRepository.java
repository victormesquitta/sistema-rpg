package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.model.Mestre;

public interface MestreRepository extends JpaRepository<Mestre, Integer> {
}
