package senac.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ModelMapper modelMapper;

    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDto obterUsuarioPorId(Integer id) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(usuario -> usuarioMapper.toDto(usuario)).orElse(null);
    }

    public void criarUsuario(UsuarioDto usuarioDto) {
        UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioDto);
        usuarioRepository.save(usuarioModel);
    }

    public void atualizarUsuario(Integer id, UsuarioDto usuarioDto) {
        UsuarioModel usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            modelMapper.map(usuarioDto, usuarioExistente);
            usuarioRepository.save(usuarioExistente);
        }
    }

    public void excluirUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
