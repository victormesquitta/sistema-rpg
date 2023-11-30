package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.RolagemRequestDTO;
import senac.domain.dtos.responses.RolagemResponseDTO;
import senac.domain.services.RolagemService;

import java.util.List;

@RestController
@RequestMapping("/api/rolagens")
public class RolagemController {

    @Autowired
    private RolagemService rolagemService;

    @PostMapping
    public ResponseEntity<Object> criarRolagem(@RequestBody @Valid RolagemRequestDTO rolagemRequestDTO) {
        rolagemService.criarRolagem(rolagemRequestDTO);
        return new ResponseEntity<>("Rolagem criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RolagemResponseDTO>> listarRolagens() {
        List<RolagemResponseDTO> listarRolagens = rolagemService.listarRolagensResponse();
        return ResponseEntity.ok(listarRolagens);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RolagemResponseDTO> obterRolagemPorId(@PathVariable Integer id) {
        RolagemResponseDTO rolagemDTO = rolagemService.obterRolagemPorIdResponse(id);
        return ResponseEntity.ok(rolagemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarRolagem(@PathVariable Integer id, @RequestBody RolagemRequestDTO rolagemRequestDTO) {
        rolagemService.atualizarRolagem(id, rolagemRequestDTO);
        return new ResponseEntity<>("Rolagem atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipante(@PathVariable Integer id) {
        rolagemService.excluirRolagem(id);
        return new ResponseEntity<>("Rolagem excluída com sucesso.", HttpStatus.OK);
    }
}
