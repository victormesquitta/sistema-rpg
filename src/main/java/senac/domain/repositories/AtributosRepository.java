package senac.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.domain.models.AtributosModel;
import senac.domain.models.PericiasModel;
import senac.domain.models.PersonagemModel;

import java.util.Optional;

@Repository
public interface AtributosRepository extends JpaRepository<AtributosModel, Integer> {

    Optional<AtributosModel> findByPersonagemModel(PersonagemModel personagemModel);
}
