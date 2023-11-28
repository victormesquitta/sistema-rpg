package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.services.UsuarioService;

@Controller
@RequestMapping("/login-cadastro")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("usuario", new UsuarioModel()); // Substitua 'Usuario' pelo nome da sua classe de modelo
        return "login";
    }


    @PostMapping("/cadastro")
    public ModelAndView criarUsuario(@ModelAttribute UsuarioRequestDTO usuarioRequestDto) {
        System.out.println(usuarioRequestDto);
        usuarioService.criarUsuario(usuarioRequestDto);
//        return new ResponseEntity<>("Usuário criado com sucesso.", HttpStatus.CREATED);
        return new ModelAndView("redirect:/");
    }
//
    @PostMapping("/login")
    public ModelAndView logar(@ModelAttribute UsuarioRequestDTO usuarioRequestDto) {
        for (UsuarioRequestDTO usuario:usuarioService.listarUsuariosRequest()) {
            if(usuario.getEmail().equals(usuarioRequestDto.getEmail()) && usuario.getSenha().equals(usuarioRequestDto.getSenha())){
                return new ModelAndView("redirect:/profile");
            }
        }
        return new ModelAndView("redirect:/login-cadastro");
    }




//    @PostMapping("/{codUsuario}/foto")
//    public ResponseEntity<String> uploadFoto(@PathVariable Integer codUsuario, @RequestPart("imagem") MultipartFile imagem) {
//        UsuarioModel usuario = usuarioService.obterUsuarioModelPorId(codUsuario);
//        try {
//            usuarioService.salvarImagemDoUsuario(codUsuario, imagem);
//            return ResponseEntity.ok("Foto do usuário salva com sucesso");
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Erro ao salvar a foto do usuário");
//        }
//    }

}
