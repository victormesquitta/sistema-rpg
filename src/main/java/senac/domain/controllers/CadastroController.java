package senac.domain.controllers;



import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.services.UsuarioService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public String init(final Model model){
        model.addAttribute("usuario",new UsuarioModel());
        return "cadastro";

    }
    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView result(@ModelAttribute UsuarioRequestDTO usuarioRequestDto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRequestDto.getSenha());
        usuarioRequestDto.setSenha(encryptedPassword);
        usuarioService.criarUsuario(usuarioRequestDto);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Criando usuário com email: {0}", usuarioRequestDto.getEmail());
        //return new ResponseEntity<>("Usuário criado com sucesso.", HttpStatus.CREATED);
        ModelAndView successModelAndView = new ModelAndView("redirect:/login");
        return successModelAndView;
    }

}
