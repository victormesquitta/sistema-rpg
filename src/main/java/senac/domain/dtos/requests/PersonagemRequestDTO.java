package senac.domain.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonagemRequestDTO {

    @NotNull(message = "O participante não pode ser nulo")
    private Integer codParticipante;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

//    private byte[] foto;

    @Size(max = 20, message = "A classe deve ter no máximo 20 caracteres")
    private String classe;

    @Size(max = 20, message = "A raça deve ter no máximo 20 caracteres")
    private String raca;

    @Size(max = 20, message = "O antecedente deve ter no máximo 20 caracteres")
    private String antecedente;

    @NotNull(message = "O nível não pode ser nulo")
    private Integer nivel;

    @NotNull(message = "A experiência (xp) não pode ser nula")
    private Integer xp;

    @NotNull(message = "O bônus de proficiência não pode ser nulo")
    private Integer bonusProficiencia;

    @Size(max = 45, message = "A inspiração deve ter no máximo 45 caracteres")
    private String inspiracao;

    @Size(max = 45, message = "A sabedoria passiva deve ter no máximo 45 caracteres")
    private String sabedoriaPassiva;

    @NotNull(message = "O CA (Classe de Armadura) não pode ser nulo")
    private Integer ca;

    @NotNull(message = "A iniciativa não pode ser nula")
    private Integer iniciativa;

    @NotNull(message = "O deslocamento não pode ser nulo")
    private Float deslocamento;

    @NotNull(message = "Os pontos de vida (HP) não podem ser nulos")
    private Integer hp;

    @NotNull(message = "Os pontos de vida temporários (HP Temp) não podem ser nulos")
    private Integer hpTemp;

    @Size(max = 200, message = "Os traços de personalidade devem ter no máximo 200 caracteres")
    private String tracosPersonalidade;

    @Size(max = 200, message = "Os ideais devem ter no máximo 200 caracteres")
    private String ideais;

    @Size(max = 200, message = "Os vínculos devem ter no máximo 200 caracteres")
    private String vinculos;

    @Size(max = 200, message = "As fraquezas devem ter no máximo 200 caracteres")
    private String fraquezas;

    @Size(max = 20, message = "A cor dos olhos deve ter no máximo 20 caracteres")
    private String corOlhos;

    @Size(max = 20, message = "A altura deve ter no máximo 20 caracteres")
    private String altura;

    @Size(max = 20, message = "O peso deve ter no máximo 20 caracteres")
    private String peso;

    @Size(max = 20, message = "A cor da pele deve ter no máximo 20 caracteres")
    private String pele;

    @Size(max = 30, message = "A cor do cabelo deve ter no máximo 30 caracteres")
    private String cabelos;

    @Size(max = 200, message = "A aparência deve ter no máximo 200 caracteres")
    private String aparencia;

    @Size(max = 2000, message = "Os aliados e organizações devem ter no máximo 2000 caracteres")
    private String aliadosOrg;

    @Size(max = 1000, message = "Outras características devem ter no máximo 1000 caracteres")
    private String outrasCaracteristicas;

    @Size(max = 10000, message = "A história deve ter no máximo 10000 caracteres")
    private String historia;

    @Size(max = 1000, message = "O tesouro deve ter no máximo 1000 caracteres")
    private String tesouro;

    @Size(max = 15, message = "A habilidade de conjuração deve ter no máximo 15 caracteres")
    private String habilidadeConjuracao;

    @Max(value = 20, message = "O CD de resistência à magia deve ser no máximo 20")
    private Integer cdResistMagia;

    @Max(value = 20, message = "O bônus de ataque à magia deve ser no máximo 20")
    private Integer bonusAtaqueMagia;
}
