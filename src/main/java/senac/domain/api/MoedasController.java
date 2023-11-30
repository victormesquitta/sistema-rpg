package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.MoedasRequestDTO;
import senac.domain.dtos.responses.MoedasResponseDTO;
import senac.domain.services.MoedasService;

import java.util.List;

@RestController
@RequestMapping("/api/moedas")
public class MoedasController {

    @Autowired
    private MoedasService moedasService;

    @PostMapping
    public ResponseEntity<Object> criarMoeda(@RequestBody @Valid MoedasRequestDTO moedasRequestDTO) {
        moedasService.criarMoeda(moedasRequestDTO);
        return new ResponseEntity<>("Moeda criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MoedasResponseDTO>> listarMoedas() {
        List<MoedasResponseDTO> listarMoedas = moedasService.listarMoedasResponse();
        return ResponseEntity.ok(listarMoedas);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MoedasResponseDTO> obterMoedaPorId(@PathVariable int id) {
        MoedasResponseDTO moedasDTO = moedasService.obterMoedaPorIdResponse(id);
        return ResponseEntity.ok(moedasDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarMoeda(@PathVariable int id, @RequestBody MoedasRequestDTO moedasRequestDTO) {
        moedasService.atualizarMoeda(id, moedasRequestDTO);
        return new ResponseEntity<>("Moeda atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirMoeda(@PathVariable int id) {
        moedasService.excluirMoeda(id);
        return new ResponseEntity<>("Moeda excluída com sucesso.", HttpStatus.OK);
    }
}
