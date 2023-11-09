package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.domain.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}
