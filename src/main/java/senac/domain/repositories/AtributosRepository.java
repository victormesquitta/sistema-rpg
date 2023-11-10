package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.AtributosModel;

@Repository
public interface AtributosRepository extends JpaRepository<AtributosModel, Integer> {
}
