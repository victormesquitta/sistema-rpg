package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;

import java.util.List;

@Controller
@RequestMapping("/campanhas")
public class CampanhaController {
    private final ParticipanteService participanteService;
    private final CampanhaMapper campanhaMapper;
    private final CampanhaService campanhaService;
    private final CampanhaRepository campanhaRepository;
    @Autowired
    public CampanhaController(ParticipanteService participanteService, CampanhaMapper campanhaMapper, CampanhaService campanhaService, CampanhaRepository campanhaRepository) {
        this.participanteService = participanteService;
        this.campanhaMapper = campanhaMapper;
        this.campanhaService = campanhaService;
        this.campanhaRepository = campanhaRepository;
    }

    @PostMapping
    public ResponseEntity<Object> criarCampanha(@RequestBody @Valid CampanhaRequestDTO campanhaRequestDto) {
        // salvando a campanha
        participanteService.criarPrimeiroParticipante(campanhaService.criarCampanha(campanhaRequestDto));
        return new ResponseEntity<>("Campanha criada com sucesso.", HttpStatus.CREATED);
    }
    @GetMapping()
    public String listarCampanhas(Model model) {
        List<CampanhaResponseDTO> listaCampanhas = campanhaService.listarCampanhasResponse();
        model.addAttribute("listaCampanhas", listaCampanhas);
        return "campanha";
    }

    @PostMapping("/detalhesCampanha")
    public String detalhesCampanha(@RequestParam Integer codCampanha, Model model) {
        CampanhaRequestDTO campanhaRequestDTO = campanhaService.obterCampanhaPorIdRequest(codCampanha);
        model.addAttribute("campanha", campanhaRequestDTO);
        return "detalhesCampanha";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaRequestDTO campanhaRequestDTO) {
        campanhaService.atualizarCampanha(id, campanhaRequestDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/deletarCampanha")
    public String excluirCampanha(@RequestParam Integer codCampanha) {
        campanhaService.excluirCampanha(codCampanha);
        return "redirect:/campanhas";
    }
}
