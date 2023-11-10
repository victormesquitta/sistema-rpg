package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.models.RegrasModel;

public interface RegrasRepository extends JpaRepository<RegrasModel, Integer> {
}
