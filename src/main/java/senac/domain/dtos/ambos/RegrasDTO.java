package senac.domain.dtos.ambos;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class RegrasDTO {
    private Integer codRegras;
    @NotNull(message = "A data não pode ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate data;
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres")
    String nome;
    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    String descricao;
    @NotNull(message = "O código da campanha não pode ser nulo")
    Integer codCampanha;
    @NotNull(message = "O código do mestre não pode ser nulo")
    Integer codParticipante;
}
