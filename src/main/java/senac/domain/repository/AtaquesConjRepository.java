package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.AtaquesConj;
@Repository
public interface AtaquesConjRepository extends JpaRepository<AtaquesConj, Integer>{
}
