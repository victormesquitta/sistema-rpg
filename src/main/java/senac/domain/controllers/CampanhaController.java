package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.CampanhaDto;
import senac.domain.dtos.UsuarioDto;
import senac.domain.services.CampanhaService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<Object> criarCampanha(@RequestBody @Valid CampanhaDto campanhaDto) {
        campanhaService.criarCampanha(campanhaDto);
        return new ResponseEntity<>("Campanha criada com sucesso.", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Object> listarCampanhas() {
        List<CampanhaDto> listaCampanhas = campanhaService.listarCampanhas();
        if (listaCampanhas != null) {
            return ResponseEntity.ok(listaCampanhas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma campanha cadastrada na base de dados.");
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterCampanhaPorId(@PathVariable Integer id) {
        CampanhaDto campanhaDto = campanhaService.obterCampanhaPorId(id);
        if (campanhaDto != null) {
            return ResponseEntity.ok(campanhaDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma campanha encontrada para o ID fornecido.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaDto campanhaDTO) {
        campanhaService.atualizarCampanha(id, campanhaDTO);
        return new ResponseEntity<>("Campanha atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirCampanha(@PathVariable Integer id) {
        CampanhaDto campanha = campanhaService.obterCampanhaPorId(id);
        if (campanha == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada.");
        }
        campanhaService.excluirCampanha(id);
        return new ResponseEntity<>("Campanha excluída com sucesso.", HttpStatus.OK);
    }
}
