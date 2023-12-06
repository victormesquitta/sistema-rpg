package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;

@Controller
@RequestMapping("/criacao-campanha")
public class CriacaoCampanhaController {

    private final ParticipanteService participanteService;
    private final CampanhaService campanhaService;
    @Autowired
    public CriacaoCampanhaController(ParticipanteService participanteService, CampanhaService campanhaService) {
        this.participanteService = participanteService;
        this.campanhaService = campanhaService;
    }

    @GetMapping
    public String init (Model model) {
        model.addAttribute("campanha", new CampanhaRequestDTO());
        return "criacao-campanha";
    }

    @PostMapping()
    public String criarCampanha(CampanhaRequestDTO campanhaRequestDto){
        participanteService.criarPrimeiroParticipante(campanhaService.criarCampanha(campanhaRequestDto));
        return "lista-de-campanhas";
    }
}
