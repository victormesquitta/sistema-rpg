package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Pericias;

@Repository
public interface PericiasRepository extends JpaRepository<Pericias, Integer> {
}
