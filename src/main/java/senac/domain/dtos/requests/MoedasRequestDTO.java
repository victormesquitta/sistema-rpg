package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoedasRequestDTO {

    @NotNull(message = "O campo po não pode ser nulo")
    private Integer po;

    @NotNull(message = "O campo pp não pode ser nulo")
    private Integer pp;

    @NotNull(message = "O campo pc não pode ser nulo")
    private Integer pc;

    @NotNull(message = "O campo pl não pode ser nulo")
    private Integer pl;

    @NotNull(message = "O campo da não pode ser nulo")
    private Integer da;

    @NotNull(message = "O campo codpersonagem não pode ser nulo")
    private Integer codPersonagem;
}
