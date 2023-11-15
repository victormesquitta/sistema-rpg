package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ParticipanteDto;
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
    public ResponseEntity<Object> criarParticipante(@RequestBody @Valid ParticipanteDto participanteDto) {
        Integer codUsuario = participanteDto.getCodUsuario();
        if(usuarioRepository.findById(codUsuario).isPresent()){
            participanteService.criarParticipante(participanteDto, codUsuario);
            return new ResponseEntity<>("Participante criado com sucesso.",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Nenhum usuário possui o ID fornecido.",HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<ParticipanteDto> listarParticipantes() {
        return participanteService.listarParticipantes();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ParticipanteDto> obterParticipantePorId(@PathVariable Integer id) {
        ParticipanteDto participanteDto = participanteService.obterParticipantePorId(id);
        if (participanteDto != null) {
            return ResponseEntity.ok(participanteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarParticipante(@PathVariable Integer id, @RequestBody ParticipanteDto participanteDTO) {
        participanteService.atualizarParticipante(id, participanteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipante(@PathVariable Integer id) {
        ParticipanteDto participante = participanteService.obterParticipantePorId(id);
        if (participante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participante não encontrado.");
        }
        participanteService.excluirParticipante(id);
        return new ResponseEntity<>("Participante excluído com sucesso.", HttpStatus.OK);
    }
}
