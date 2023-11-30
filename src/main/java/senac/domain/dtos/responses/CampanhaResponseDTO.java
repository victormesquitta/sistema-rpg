package senac.domain.dtos.responses;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CampanhaResponseDTO {
    private Integer codCampanha;
    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(message = "O campo 'data' não pode ser nulo")
    //@PastOrPresent(message = "A data de criação da campanha deve ser no passado ou no presente")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    @Size(max = 3000, message = "O campo 'descricao' deve ter no máximo 30 caracteres")
    private String descricao;


}
