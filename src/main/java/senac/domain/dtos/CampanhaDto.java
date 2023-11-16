package senac.domain.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class CampanhaDTO {
        private Integer codCampanha;
        @NotBlank(message = "O campo 'nome' não pode estar em branco")
        @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
        private String nome;

        @NotNull(message = "O campo 'data' não pode ser nulo")
        //@PastOrPresent(message = "A data de criação da campanha deve ser no passado ou no presente")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dataCriacao;

        @NotNull(message = "O campo 'players' não pode ser nulo")
        @Min(value = 0, message = "O número mínimo de players é 0")
        private Integer qtdPlayers;

        @NotBlank(message = "O campo 'senha' não pode estar em branco")
        @Size(max = 30, message = "O campo 'senha' deve ter no máximo 30 caracteres")
        private String senha;

        @NotNull(message = "O campo 'qtdPlayersOnline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players online é 0")
        private Integer qtdPlayersOnline;

        @NotNull(message = "O campo 'qtdPlayersOffline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players offline é 0")
        private Integer qtdPlayersOffline;
}