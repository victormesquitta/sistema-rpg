package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.models.EquipamentoModel;

public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Integer> {
}
