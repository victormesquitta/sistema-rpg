package senac.domain.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

// responsável por fazer a validação das informações antes de serem salvas no banco de dados via model/controller
@Data
public class UsuarioRequestDTO {


        @NotBlank(message = "O campo 'usuario' não pode estar em branco")
        @Size(max = 255, message = "O campo 'usuario' deve ter no máximo 255 caracteres")
        private String usuario;

        @NotBlank(message = "O campo 'email' não pode estar em branco")
        @Email(message = "O campo 'email' deve ser um endereço de e-mail válido")
        @Size(max = 255, message = "O campo 'email' deve ter no máximo 255 caracteres")
        private String email;

        @NotBlank(message = "O campo 'senha' não pode estar em branco")
        @Size(max = 30, message = "O campo 'senha' deve ter no máximo 30 caracteres")
        String senha;

        @NotNull(message = "O campo 'datacriacao' não pode ser nulo")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dataCriacao;


        private byte[] imagem;

        @NotNull(message = "O campo 'horasjogadas' não pode ser nulo")
        private LocalTime horasJogadas;
}


