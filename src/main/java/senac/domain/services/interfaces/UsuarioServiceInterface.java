package senac.domain.services.interfaces;

import senac.domain.dtos.UsuarioDto;

import java.util.List;

public interface UsuarioServiceInterface {
    List<UsuarioDto> listarUsuarios();
    UsuarioDto obterUsuarioPorId(Integer id);
    void criarUsuario(UsuarioDto usuario);
    void atualizarUsuario(Integer id, UsuarioDto usuario);
    void excluirUsuario(Integer id);
}
