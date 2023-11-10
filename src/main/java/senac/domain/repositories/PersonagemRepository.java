package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.PersonagemModel;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemModel, Integer> {
}
