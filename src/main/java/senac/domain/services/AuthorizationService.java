package senac.domain.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

@Service

public class AuthorizationService implements UserDetailsService {

//    @Autowired
//    UsuarioModel usuario;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = usuarioRepository.findByUsuario(username);


        return usuarioRepository.findByUsuario(username);    // metodo para fazer a consulta dos usuarios para o spring SecurityConfiguration
    }
}
