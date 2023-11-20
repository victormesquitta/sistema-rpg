package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.RolagemDTO;
import senac.domain.services.RolagemService;

import java.util.List;

@RestController
@RequestMapping("/api/rolagens")
public class RolagemController {

    @Autowired
    private RolagemService rolagemService;

    @PostMapping
    public ResponseEntity<Object> criarRolagem(@RequestBody @Valid RolagemDTO rolagemDTO) {
        rolagemService.criarRolagem(rolagemDTO);
        return new ResponseEntity<>("Rolagem criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RolagemDTO>> listarRolagens() {
        List<RolagemDTO> listarRolagens = rolagemService.listarRolagens();
        return ResponseEntity.ok(listarRolagens);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RolagemDTO> obterRolagemPorId(@PathVariable Integer id) {
        RolagemDTO rolagemDTO = rolagemService.obterRolagemPorId(id);
        return ResponseEntity.ok(rolagemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarRolagem(@PathVariable Integer id, @RequestBody RolagemDTO rolagemDTO) {
        rolagemService.atualizarRolagem(id, rolagemDTO);
        return new ResponseEntity<>("Rolagem atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipante(@PathVariable Integer id) {
        rolagemService.excluirRolagem(id);
        return new ResponseEntity<>("Rolagem excluída com sucesso.", HttpStatus.OK);
    }
}
