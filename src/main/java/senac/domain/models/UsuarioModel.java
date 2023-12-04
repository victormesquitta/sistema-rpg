package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
public class    UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario")
    private Integer codUsuario;

    @Column(name="usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name="email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name="senha", nullable = false, length = 255)
    private String senha;

    @Column(name="datacriacao", nullable = false)
    private LocalDate dataCriacao;

//    @Lob
//    @Column(name="imagem", nullable = false, columnDefinition = "LONGBLOB")
//    private byte [] imagem;

//    @Column(name="horasjogadas", nullable = false)
//    private LocalTime horasJogadas;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuarioModel", cascade = CascadeType.ALL)
    private List<ParticipanteModel> participantes = new ArrayList<>();

    public UsuarioModel (String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
