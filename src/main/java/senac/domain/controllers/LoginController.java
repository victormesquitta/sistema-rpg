package senac.domain.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import senac.domain.dtos.requests.AuthenticationDTO;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.dtos.responses.LoginResponseDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;
import senac.domain.security.TokenService;
import senac.domain.services.UsuarioService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public String init (final Model model){
        model.addAttribute("usuario", new UsuarioRequestDTO());
        return "login";
    }

    public ModelAndView redirect(final Model model){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }


//    @GetMapping()
//    public String login(Model model) {
//        model.addAttribute("usuario", new UsuarioModel()); // Substitua 'Usuario' pelo nome da sua classe de modelo
//        return "login";
//    }


    
//
    @PostMapping()
    public ModelAndView logar(@ModelAttribute AuthenticationDTO dto) {
        var senha = new UsernamePasswordAuthenticationToken(dto.usuario(),dto.senha());

        if(!senha.getPrincipal().equals(dto.usuario())){
            throw new BadCredentialsException("Usuario ou senha inválidos");
        }

        var auth = authenticationManager.authenticate(senha);
        var token = tokenService.GerarToken((UsuarioModel) auth.getPrincipal());

        new LoginResponseDTO(token);

        HttpSession session = request.getSession(true);
        session.setAttribute("loggedInUser",dto.usuario());

        UserDetails userDetails = usuarioRepository.findByUsuario(dto.usuario());
        Set<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        if((authorities.contains("ROLE_USER"))){
            return new ModelAndView("redirect:/");
        }
        else{
            return new ModelAndView("redirect:/cadastro");
        }
    }




//    @PostMapping("/{codUsuario}/foto")
//    public ResponseEntity<String> uploadFoto(@PathVariable Integer codUsuario, @RequestPart("imagem") MultipartFile imagem) {
//        UsuarioModel usuario = usuarioService.obterUsuarioModelPorId(codUsuario);
//        try {
//            usuarioService.salvarImagemDoUsuario(codUsuario, imagem);
//            return ResponseEntity.ok("Foto do usuário salva com sucesso");
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Erro ao salvar a foto do usuário");
//        }
//    }

}
