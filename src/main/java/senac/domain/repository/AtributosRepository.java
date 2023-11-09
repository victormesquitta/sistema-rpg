package senac.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.model.Atributos;

@Repository
public interface AtributosRepository extends JpaRepository<Atributos, Integer> {
}
