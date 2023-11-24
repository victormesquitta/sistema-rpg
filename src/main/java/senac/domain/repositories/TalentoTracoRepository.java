package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.TalentoTracoModel;

@Repository
public interface TalentoTracoRepository extends JpaRepository<TalentoTracoModel, Integer> {
}
