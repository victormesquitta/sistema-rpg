package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.CampanhaRespostaDTO;
import senac.domain.dtos.CampanhaDTO;
import senac.domain.services.CampanhaService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<Object> criarCampanha(@RequestBody @Valid CampanhaDTO campanhaDto) {
        campanhaService.criarCampanha(campanhaDto);
        return new ResponseEntity<>("Campanha criada com sucesso.", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Object> listarCampanhas() {
        List<CampanhaRespostaDTO> listaCampanhas = campanhaService.listarCampanhas();
            return ResponseEntity.ok(listaCampanhas);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterCampanhaPorId(@PathVariable Integer id) {
        CampanhaRespostaDTO campanhaDto = campanhaService.obterCampanhaPorId(id);
        return ResponseEntity.ok(campanhaDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaDTO campanhaDTO) {
        campanhaService.atualizarCampanha(id, campanhaDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirCampanha(@PathVariable Integer id) {
        CampanhaRespostaDTO campanha = campanhaService.obterCampanhaPorId(id);
        campanhaService.excluirCampanha(id);
        return new ResponseEntity<>("Campanha excluída com sucesso.", HttpStatus.OK);
    }
}
