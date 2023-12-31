package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.dtos.responses.UsuarioResponseDTO;
import senac.domain.mappers.UsuarioMapper;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

import java.io.IOException;
import java.time.LocalDate;
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

    public List<UsuarioResponseDTO> listarUsuariosResponse() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("Nenhum usuário cadastrado ainda.");
        }
            return usuarios.stream()
                    .map(usuarioMapper::toResponseDto)
                    .collect(Collectors.toList());
    }

    public List<UsuarioRequestDTO> listarUsuariosRequest() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("Nenhum usuário cadastrado ainda.");
        }
            return usuarios.stream()
                    .map(usuarioMapper::toRequestDto)
                    .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obterUsuarioPorIdResponse(Integer id) {
        listarUsuariosResponse();
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado para o ID fornecido."));
            return usuarioOptional.map(usuarioMapper::toResponseDto).orElse(null);
    }

//    public void salvarImagemDoUsuario(Integer codUsuario, MultipartFile imagem) throws IOException {
//        UsuarioModel usuario = usuarioRepository.findById(codUsuario)
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//
//        usuario.setImagem(imagem.getBytes());
//        usuarioRepository.save(usuario);
//    }

    public UsuarioRequestDTO obterUsuarioPorIdRequest(Integer id) {
        listarUsuariosResponse();
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado para o ID fornecido."));
            return usuarioOptional.map(usuarioMapper::toRequestDto).orElse(null);
    }

    public UsuarioModel obterUsuarioModelPorId(Integer codUsuario){
        listarUsuariosResponse();
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(codUsuario);
        usuarioOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado para o ID fornecido."));
        UsuarioRequestDTO usuarioRequestDTO = usuarioOptional.map(usuarioMapper::toRequestDto).orElse(null);
        UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioRequestDTO);
        usuarioModel.setCodUsuario(obterUsuarioPorIdResponse(codUsuario).getCodUsuario());
        return usuarioModel;
    }

    public void criarUsuario(UsuarioRequestDTO usuarioRequestDto) {
        validarDadosDuplicados(usuarioRequestDto);
        UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioRequestDto);
        usuarioModel.setDataCriacao(LocalDate.now());
        usuarioRepository.save(usuarioModel);
    }

    public void atualizarUsuario(Integer id, UsuarioRequestDTO usuarioRequestDto) {
        obterUsuarioPorIdRequest(id);
        validarDadosDuplicados(usuarioRequestDto, id);
        UsuarioModel usuarioAtualizado = usuarioMapper.toEntity(usuarioRequestDto);
        usuarioAtualizado.setCodUsuario(id);
        usuarioRepository.save(usuarioAtualizado);
    }

    public void excluirUsuario(Integer id) {
        obterUsuarioPorIdRequest(id);
        usuarioRepository.deleteById(id);
    }

    private void validarDadosDuplicados(UsuarioRequestDTO usuarioRequestDto, Integer id) {
        for(UsuarioModel usuario : usuarioRepository.findAll()){
            if(!(id.equals(usuario.getCodUsuario()))){
                if(usuario.getUsuario().equals(usuarioRequestDto.getUsuario())){
                    throw new DataIntegrityViolationException("Nome de usuário já em uso.");
                }
                else if(usuario.getEmail().equals(usuarioRequestDto.getEmail())){
                    throw new DataIntegrityViolationException("Endereço de e-mail já em uso.");
                }
            }
        }
    }
    private void validarDadosDuplicados(UsuarioRequestDTO usuarioRequestDto) {
        for(UsuarioModel usuario : usuarioRepository.findAll()){
            if(usuario.getUsuario().equals(usuarioRequestDto.getUsuario())){
                throw new DataIntegrityViolationException("Nome de usuário já em uso.");
            }
            else if(usuario.getEmail().equals(usuarioRequestDto.getEmail())){
                throw new DataIntegrityViolationException("Endereço de e-mail já em uso.");
            }
        }
    }
}
