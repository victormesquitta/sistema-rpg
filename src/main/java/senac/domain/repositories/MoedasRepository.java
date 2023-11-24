package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.MoedasModel;
import senac.domain.models.PericiasModel;
import senac.domain.models.PersonagemModel;

import java.util.Optional;

@Repository
public interface MoedasRepository extends JpaRepository<MoedasModel, Integer> {
    Optional<MoedasModel> findByPersonagemModel(PersonagemModel personagemModel);

}
