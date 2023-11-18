package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.repositories.UsuarioRepository;
import senac.domain.services.ParticipanteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Object> criarParticipante(@RequestBody @Valid ParticipanteRequestDTO participanteRequestDto) {
        participanteService.criarParticipante(participanteRequestDto);
        return new ResponseEntity<>("Participante criado com sucesso.",HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> listarParticipantes() {
        List<ParticipanteResponseDTO> listarParticipantes = participanteService.listarParticipantesResponse();
        return ResponseEntity.ok(listarParticipantes);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ParticipanteResponseDTO> obterParticipantePorId(@PathVariable Integer id) {
        ParticipanteResponseDTO participanteDto = participanteService.obterParticipantePorIdResponse(id);
        return ResponseEntity.ok(participanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarParticipante(@PathVariable Integer id, @RequestBody ParticipanteRequestDTO participanteRequestDTO) {
        participanteService.obterParticipantePorIdResponse(id);
        participanteService.atualizarParticipante(id, participanteRequestDTO);
        return new ResponseEntity<>("Participante atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipante(@PathVariable Integer id) {
        participanteService.obterParticipantePorIdResponse(id);
        participanteService.excluirParticipante(id);
        return new ResponseEntity<>("Participante excluído com sucesso.", HttpStatus.OK);
    }
}
