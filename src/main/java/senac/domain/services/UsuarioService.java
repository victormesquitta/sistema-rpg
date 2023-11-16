package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import senac.domain.dtos.UsuarioDTO;
import senac.domain.dtos.UsuarioRespostaDTO;
import senac.domain.mappers.UsuarioMapper;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioRespostaDTO> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("Nenhum usuário cadastrado ainda.");
        }
        return usuarios.stream()
                .map(usuarioMapper::toRespostaDto)
                .collect(Collectors.toList());
    }

    public UsuarioRespostaDTO obterUsuarioPorId(Integer id) {
        listarUsuarios();
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado para o ID fornecido."));
        return usuarioOptional.map(usuarioMapper::toRespostaDto).orElse(null);
    }

    public void criarUsuario(UsuarioDTO usuarioDto) {
        validarDadosDuplicados(usuarioDto);
        UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuarioModel);
    }

    public void atualizarUsuario(Integer id, UsuarioDTO usuarioDto) {
        obterUsuarioPorId(id);
        validarDadosDuplicados(usuarioDto, id);
        UsuarioModel usuarioAtualizado = usuarioMapper.toEntity(usuarioDto);
        usuarioAtualizado.setCodUsuario(id);
        usuarioRepository.save(usuarioAtualizado);
    }

    public void excluirUsuario(Integer id) {
        obterUsuarioPorId(id);
        usuarioRepository.deleteById(id);
    }

    private void validarDadosDuplicados(UsuarioDTO usuarioDto, Integer id) {
        for(UsuarioModel usuario : usuarioRepository.findAll()){
            if(!(id.equals(usuario.getCodUsuario()))){
                if(usuario.getUsuario().equals(usuarioDto.getUsuario())){
                    throw new DataIntegrityViolationException("Nome de usuário já em uso.");
                }
                else if(usuario.getEmail().equals(usuarioDto.getEmail())){
                    throw new DataIntegrityViolationException("Endereço de e-mail já em uso.");
                }
            }
        }
    }
    private void validarDadosDuplicados(UsuarioDTO usuarioDto) {
        for(UsuarioModel usuario : usuarioRepository.findAll()){
            if(usuario.getUsuario().equals(usuarioDto.getUsuario())){
                throw new DataIntegrityViolationException("Nome de usuário já em uso.");
            }
            else if(usuario.getEmail().equals(usuarioDto.getEmail())){
                throw new DataIntegrityViolationException("Endereço de e-mail já em uso.");
            }
        }
    }
}
