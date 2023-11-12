package senac.domain.dtos;

import jakarta.validation.constraints.*;

import java.util.Date;

public record CampanhaRecordDto(

        @NotBlank(message = "O campo 'nome' não pode estar em branco")
        @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
        String nome,

        @NotNull(message = "O campo 'data' não pode ser nulo")
        @PastOrPresent(message = "A data de criação da campanha deve ser no passado ou no presente")
        Date data,

        @NotNull(message = "O campo 'players' não pode ser nulo")
        @Min(value = 1, message = "O número mínimo de players é 1")
        Integer players,

        @NotBlank(message = "O campo 'senha' não pode estar em branco")
        @Size(max = 30, message = "O campo 'senha' deve ter no máximo 30 caracteres")
        String senha,

        @NotNull(message = "O campo 'qtdPlayersOnline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players online é 0")
        Integer qtdPlayersOnline,

        @NotNull(message = "O campo 'qtdPlayersOffline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players offline é 0")
        Integer qtdPlayersOffline

) {}