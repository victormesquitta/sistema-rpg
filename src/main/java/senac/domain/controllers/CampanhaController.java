package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;
import senac.domain.services.PersonagemService;

import java.util.List;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {
    private final ParticipanteService participanteService;
    private final PersonagemService personagemService;
    private final CampanhaService campanhaService;
    @Autowired
    public CampanhaController(ParticipanteService participanteService, PersonagemService personagemService, CampanhaService campanhaService) {
        this.participanteService = participanteService;
        this.personagemService = personagemService;
        this.campanhaService = campanhaService;
    }
    @GetMapping("/{codCampanha}")
    public String obterCampanha(@PathVariable Integer codCampanha, Model model) {
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-home";
    }

    @GetMapping("/{codCampanha}/participantes")
    public String obterParticipantes(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        List<ParticipanteResponseDTO> listaParticipantes = participanteService.listarParticipantesPorCampanha(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        model.addAttribute("participantes", listaParticipantes);
        return "campanha-participantes";
    }

    @GetMapping("/{codCampanha}/personagens")
    public String obterPersonagens(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        List<PersonagemResponseDTO> listaPersonagens = personagemService.listarPersonagensPorCampanha(codCampanha);
        model.addAttribute("personagens", listaPersonagens);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-personagens";
    }

    @GetMapping("/{codCampanha}/mapa")
    public String obterMapa(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-mapa";
    }

    @GetMapping("/{codCampanha}/regras")
    public String obterRegras(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-regras";
    }

    @GetMapping("/{codCampanha}/criar-personagem")
    public String criarPersonagem(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-criar-personagem";
    }
}
