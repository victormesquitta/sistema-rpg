package senac.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senac.domain.models.UsuarioModel;

@Controller
@RequestMapping("/informacoes")
public class InformacoesController {

    @GetMapping()
    public String informacoes() {
        return "informacoes";
    }
}
