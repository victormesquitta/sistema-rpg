package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.MagiaModel;

@Repository
public interface MagiaRepository extends JpaRepository<MagiaModel, Integer> {
}