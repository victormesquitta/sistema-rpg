package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer>{
}