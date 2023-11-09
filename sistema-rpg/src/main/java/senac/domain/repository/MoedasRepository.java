package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Moedas;

@Repository
public interface MoedasRepository extends JpaRepository<Moedas, Integer> {
}
