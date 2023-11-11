package senac.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView logar() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}
