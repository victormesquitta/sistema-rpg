package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;

import java.util.List;

@Controller
@RequestMapping("/lista-de-campanhas")
public class ListaCampanhasController {
    private final ParticipanteService participanteService;
    private final CampanhaService campanhaService;
    @Autowired
    public ListaCampanhasController(ParticipanteService participanteService, CampanhaService campanhaService) {
        this.participanteService = participanteService;
        this.campanhaService = campanhaService;
    }
    @GetMapping()
    public String listarCampanhas(Model model) {
        List<CampanhaResponseDTO> listaCampanhas = campanhaService.listarCampanhasResponse();
        model.addAttribute("listaCampanhas", listaCampanhas);
        return "lista-campanhas";
    }

    @GetMapping("/{codCampanha}")
    public String detalhesCampanha(@PathVariable Integer codCampanha, Model model) {
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        List<ParticipanteResponseDTO> participantes = participanteService.listarParticipantesPorCampanha(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        model.addAttribute("participantes", participantes);
        return "detalhes-campanha";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaRequestDTO campanhaRequestDTO) {
        campanhaService.atualizarCampanha(id, campanhaRequestDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/deletarCampanha")
    public String excluirCampanha(@RequestParam Integer codCampanha) {
        campanhaService.excluirCampanha(codCampanha);
        return "redirect:/lista-de-campanhas";
    }
//    @GetMapping("criacao-campanha")
//    public String criacaoCampanha(@RequestBody @Valid CampanhaRequestDTO campanhaRequestDto) {
//        return "criacao-campanha";
//    }
//    @PostMapping("entrar-campanha")
//    public String entrarCampanha(Model model) {
//        return "campanha-home";
//    }
}
