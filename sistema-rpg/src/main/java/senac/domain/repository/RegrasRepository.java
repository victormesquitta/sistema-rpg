package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.model.Regras;

public interface RegrasRepository extends JpaRepository<Regras, Integer> {
}
