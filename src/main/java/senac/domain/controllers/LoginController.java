package senac.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import senac.domain.models.UsuarioModel;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new UsuarioModel()); // Substitua 'Usuario' pelo nome da sua classe de modelo
        return "login";
    }

}
