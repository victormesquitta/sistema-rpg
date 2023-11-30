package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.AtaquesConjuracaoRequestDTO;
import senac.domain.dtos.responses.AtaquesConjuracaoResponseDTO;
import senac.domain.services.AtaquesConjuracaoService;

import java.util.List;

@RestController
@RequestMapping("/api/ataquesconjuracao")
public class AtaquesConjuracaoController {

    @Autowired
    private AtaquesConjuracaoService ataquesConjuracaoService;

    @PostMapping
    public ResponseEntity<Object> criarAtaqueConjuracao(@RequestBody @Valid AtaquesConjuracaoRequestDTO ataqueConjuracaoRequestDTO) {
        ataquesConjuracaoService.criarAtaqueConjuracao(ataqueConjuracaoRequestDTO);
        return new ResponseEntity<>("Ataque ou conjuração criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AtaquesConjuracaoResponseDTO>> listarAtaquesConjuracao() {
        List<AtaquesConjuracaoResponseDTO> listarAtaquesConjuracao = ataquesConjuracaoService.listarAtaquesConjuracaoResponse();
        return ResponseEntity.ok(listarAtaquesConjuracao);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AtaquesConjuracaoResponseDTO> obterAtaqueConjuracaoPorId(@PathVariable int id) {
        AtaquesConjuracaoResponseDTO ataqueConjuracaoDTO = ataquesConjuracaoService.obterAtaqueConjuracaoPorIdResponse(id);
        return ResponseEntity.ok(ataqueConjuracaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAtaqueConjuracao(@PathVariable int id, @RequestBody AtaquesConjuracaoRequestDTO ataqueConjuracaoRequestDTO) {
        ataquesConjuracaoService.atualizarAtaqueConjuracao(id, ataqueConjuracaoRequestDTO);
        return new ResponseEntity<>("Ataque ou conjuração atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirAtaqueConjuracao(@PathVariable int id) {
        ataquesConjuracaoService.excluirAtaqueConjuracao(id);
        return new ResponseEntity<>("Ataque ou conjuração excluída com sucesso.", HttpStatus.OK);
    }
}
