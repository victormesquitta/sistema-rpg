package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.AtaquesConjuracaoModel;
@Repository
public interface AtaquesConjuracaoRepository extends JpaRepository<AtaquesConjuracaoModel, Integer>{
}
