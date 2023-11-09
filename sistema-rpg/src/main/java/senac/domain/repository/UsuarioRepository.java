package senac.domain.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import senac.domain.model.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @GetMapping
    default List<Usuario> find(Usuario filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return findAll(example);
    }
}
