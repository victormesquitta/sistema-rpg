package senac.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/criacaoCampanha")
public class CriacaoCampanhaController {

    @GetMapping()
    public String login(Model model) {
        return "criacaoCampanha";
    }
}
