package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;

import java.util.List;

@Controller
@RequestMapping("/api/campanhas")
public class CampanhaRestController {
    private final ParticipanteService participanteService;
    private final CampanhaService campanhaService;

    @Autowired
    public CampanhaRestController(ParticipanteService participanteService, CampanhaService campanhaService) {
        this.participanteService = participanteService;
        this.campanhaService = campanhaService;
    }

    @PostMapping
    public ResponseEntity<Object> criarCampanha(@RequestBody @Valid CampanhaRequestDTO campanhaRequestDto) {
        // salvando a campanha
        participanteService.criarPrimeiroParticipante(campanhaService.criarCampanha(campanhaRequestDto));
        return new ResponseEntity<>("Campanha criada com sucesso.", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CampanhaResponseDTO>> listarCampanhas() {
        List<CampanhaResponseDTO> listaCampanhas = campanhaService.listarCampanhasResponse();
        return ResponseEntity.ok(listaCampanhas);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CampanhaResponseDTO> obterCampanhaPorId(@PathVariable int id) {
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(id);
        return ResponseEntity.ok(campanhaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaRequestDTO campanhaRequestDTO) {
        campanhaService.atualizarCampanha(id, campanhaRequestDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> excluirCampanha(@RequestParam Integer codCampanha) {
        campanhaService.excluirCampanha(codCampanha);
        return new ResponseEntity<>("Campanha excluída com sucesso.", HttpStatus.OK);
    }
}
