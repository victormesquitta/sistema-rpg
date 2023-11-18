package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.ParticipanteDTO;
import senac.domain.services.ParticipanteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<Object> criarParticipante(@RequestBody @Valid ParticipanteDTO participanteDto) {
        participanteService.criarParticipante(participanteDto);
        return new ResponseEntity<>("Participante criado com sucesso.",HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteDTO>> listarParticipantes() {
        List<ParticipanteDTO> listarParticipantes = participanteService.listarParticipantes();
        return ResponseEntity.ok(listarParticipantes);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ParticipanteDTO> obterParticipantePorId(@PathVariable Integer id) {
        ParticipanteDTO participanteDto = participanteService.obterParticipantePorId(id);
        return ResponseEntity.ok(participanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarParticipante(@PathVariable Integer id, @RequestBody ParticipanteDTO participanteDTO) {
        participanteService.obterParticipantePorId(id);
        participanteService.atualizarParticipante(id, participanteDTO);
        return new ResponseEntity<>("Participante atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipante(@PathVariable Integer id) {
        participanteService.obterParticipantePorId(id);
        participanteService.excluirParticipante(id);
        return new ResponseEntity<>("Participante excluído com sucesso.", HttpStatus.OK);
    }
}
