package senac.domain.dtos;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CampanhaDto {

        @NotBlank(message = "O campo 'nome' não pode estar em branco")
        @Size(max = 255, message = "O campo 'nome' deve ter no máximo 255 caracteres")
        String nome;

        @NotNull(message = "O campo 'data' não pode ser nulo")
        //@PastOrPresent(message = "A data de criação da campanha deve ser no passado ou no presente")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dataCriacao;

        @NotNull(message = "O campo 'players' não pode ser nulo")
        @Min(value = 0, message = "O número mínimo de players é 0")
        Integer qtdPlayers;

        @NotBlank(message = "O campo 'senha' não pode estar em branco")
        @Size(max = 30, message = "O campo 'senha' deve ter no máximo 30 caracteres")
        String senha;

        @NotNull(message = "O campo 'qtdPlayersOnline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players online é 0")
        Integer qtdPlayersOnline;

        @NotNull(message = "O campo 'qtdPlayersOffline' não pode ser nulo")
        @Min(value = 0, message = "A quantidade mínima de players offline é 0")
        Integer qtdPlayersOffline;

        public CampanhaDto(String nome, LocalDate dataCriacao, Integer qtdPlayers,
                           String senha, Integer qtdPlayersOnline, Integer qtdPlayersOffline) {
                this.nome = nome;
                this.dataCriacao = dataCriacao;
                this.qtdPlayers = qtdPlayers;
                this.senha = senha;
                this.qtdPlayersOnline = qtdPlayersOnline;
                this.qtdPlayersOffline = qtdPlayersOffline;
        }

        public CampanhaDto() {
        }

        public String getNome() {
                return nome;
        }

        public LocalDate getDataCriacao() {
                return dataCriacao;
        }

        public Integer getQtdPlayers() {
                return qtdPlayers;
        }

        public String getSenha() {
                return senha;
        }

        public Integer getQtdPlayersOnline() {
                return qtdPlayersOnline;
        }

        public Integer getQtdPlayersOffline() {
                return qtdPlayersOffline;
        }

        @Override
        public String toString() {
                return "CampanhaDto{" +
                        "nome='" + nome + '\'' +
                        ", dataCriacao=" + dataCriacao +
                        ", qtdPlayers=" + qtdPlayers +
                        ", senha='" + senha + '\'' +
                        ", qtdPlayersOnline=" + qtdPlayersOnline +
                        ", qtdPlayersOffline=" + qtdPlayersOffline +
                        '}';
        }
}