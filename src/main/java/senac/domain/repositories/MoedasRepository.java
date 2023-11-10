package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.MoedasModel;

@Repository
public interface MoedasRepository extends JpaRepository<MoedasModel, Integer> {
}
