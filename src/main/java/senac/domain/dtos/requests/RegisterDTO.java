package senac.domain.dtos.requests;

import java.time.LocalDate;

public record RegisterDTO(String usuario, String senha, String email, LocalDate dataCriacao) {
}