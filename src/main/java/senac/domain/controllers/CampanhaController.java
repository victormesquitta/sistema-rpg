package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.CampanhaRecordDto;
import senac.domain.services.CampanhaService;

import java.util.List;

@Controller
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public List<CampanhaRecordDto> listarCampanhas() {
        return campanhaService.listarCampanhas();
    }

    @GetMapping("/{id}")
    public CampanhaRecordDto obterCampanhaPorId(@PathVariable Integer id) {
        return campanhaService.obterCampanhaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Void> criarCampanha(@RequestBody CampanhaRecordDto campanhaRecordDto) {
        campanhaService.criarCampanha(campanhaRecordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCampanha(@PathVariable Integer id, @RequestBody CampanhaRecordDto campanhaDTO) {
        campanhaService.atualizarCampanha(id, campanhaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCampanha(@PathVariable Integer id) {
        campanhaService.excluirCampanha(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
