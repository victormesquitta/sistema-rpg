package senac.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {
    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        return modelAndView;
    }
}
