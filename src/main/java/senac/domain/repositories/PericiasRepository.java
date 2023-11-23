package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.PericiasModel;
import senac.domain.models.PersonagemModel;

import java.util.Optional;

@Repository
public interface PericiasRepository extends JpaRepository<PericiasModel, Integer> {
    Optional<PericiasModel> findByPersonagemModel(PersonagemModel personagemModel);
}
