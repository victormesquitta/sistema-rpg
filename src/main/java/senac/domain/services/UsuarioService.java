package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import senac.domain.dtos.UsuarioDto;
import senac.domain.mappers.UsuarioMapper;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;
import senac.domain.services.interfaces.UsuarioServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("Nenhum usuário cadastrado ainda.");
        }
        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDto obterUsuarioPorId(Integer id) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado para o ID fornecido."));
        return usuarioOptional.map(usuarioMapper::toDto).orElse(null);
    }

    public void criarUsuario(UsuarioDto usuarioDto) {
        validarDadosDuplicados(usuarioDto);
        UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuarioModel);
    }

    public void atualizarUsuario(Integer id, UsuarioDto usuarioDto) {
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

    private void validarDadosDuplicados(UsuarioDto usuarioDto, Integer id) {
        for(UsuarioDto usuario : listarUsuarios()){
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
    private void validarDadosDuplicados(UsuarioDto usuarioDto) {
        for(UsuarioDto usuario : listarUsuarios()){
            if(usuario.getUsuario().equals(usuarioDto.getUsuario())){
                throw new DataIntegrityViolationException("Nome de usuário já em uso.");
            }
            else if(usuario.getEmail().equals(usuarioDto.getEmail())){
                throw new DataIntegrityViolationException("Endereço de e-mail já em uso.");
            }
        }
    }
}
