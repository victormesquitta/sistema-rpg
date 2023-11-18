package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.dtos.requests.CampanhaRequestDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.repositories.CampanhaRepository;
import senac.domain.services.CampanhaService;
import senac.domain.services.ParticipanteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/campanhas")
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
        Integer codCampanha = campanhaService.criarCampanha(campanhaRequestDto);
        participanteService.criarPrimeiroParticipante(codCampanha);
        return new ResponseEntity<>("Campanha criada com sucesso.", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Object> listarCampanhas() {
        List<CampanhaResponseDTO> listaCampanhas = campanhaService.listarCampanhasResponse();
            return ResponseEntity.ok(listaCampanhas);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterCampanhaPorId(@PathVariable Integer id) {
        CampanhaResponseDTO campanhaResponseDTODto = campanhaService.obterCampanhaPorIdResponse(id);
        return ResponseEntity.ok(campanhaResponseDTODto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaRequestDTO campanhaRequestDTO) {
        campanhaService.atualizarCampanha(id, campanhaRequestDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirCampanha(@PathVariable Integer id) {
        campanhaService.obterCampanhaPorIdResponse(id);
        campanhaService.excluirCampanha(id);
        return new ResponseEntity<>("Campanha excluída com sucesso.", HttpStatus.OK);
    }
}
