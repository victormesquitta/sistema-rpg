package senac.domain.dtos.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class UsuarioResponseDTO {
    private Integer codUsuario;
    @NotBlank(message = "O campo 'usuario' não pode estar em branco")
    @Size(max = 255, message = "O campo 'usuario' deve ter no máximo 255 caracteres")
    private String usuario;

    @NotBlank(message = "O campo 'email' não pode estar em branco")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido")
    @Size(max = 255, message = "O campo 'email' deve ter no máximo 255 caracteres")
    private String email;

    @NotNull(message = "O campo 'datacriacao' não pode ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    private byte[] imagem;

    @NotNull(message = "O campo 'horasjogadas' não pode ser nulo")
    private LocalTime horasJogadas;
}
