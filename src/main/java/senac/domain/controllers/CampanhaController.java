package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import senac.domain.dtos.responses.*;
import senac.domain.services.*;

import java.util.List;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {
    private final ParticipanteService participanteService;
    private final CampanhaService campanhaService;
    private final PersonagemService personagemService;
    private final ProficienciaService proficienciaService;
    private final OutraProficienciaService outraProficienciaService;
    private final PericiasService periciasService;
    private final AtributosService atributosService;
    private final TalentoTracoService talentoTracoService;
    private final MoedasService moedasService;
    private final AtaquesConjuracaoService ataquesConjuracaoService;
    private final EquipamentoService equipamentoService;
    private final MagiaService magiaService;

    @Autowired
    public CampanhaController(ParticipanteService participanteService, PersonagemService personagemService, CampanhaService campanhaService, ProficienciaService proficienciaService, OutraProficienciaService outraProficienciaService, PericiasService periciasService, AtributosService atributosService, TalentoTracoService talentoTracoService, MoedasService moedasService, AtaquesConjuracaoService ataquesConjuracaoService, EquipamentoService equipamentoService, MagiaService magiaService) {
        this.participanteService = participanteService;
        this.personagemService = personagemService;
        this.campanhaService = campanhaService;
        this.proficienciaService = proficienciaService;
        this.outraProficienciaService = outraProficienciaService;
        this.periciasService = periciasService;
        this.atributosService = atributosService;
        this.talentoTracoService = talentoTracoService;
        this.moedasService = moedasService;
        this.ataquesConjuracaoService = ataquesConjuracaoService;
        this.equipamentoService = equipamentoService;
        this.magiaService = magiaService;

    }
    @GetMapping("/{codCampanha}")
    public String obterCampanha(@PathVariable Integer codCampanha, Model model) {
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-home";
    }

    @GetMapping("/{codCampanha}/participantes")
    public String obterParticipantes(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        List<ParticipanteResponseDTO> listaParticipantes = participanteService.listarParticipantesPorCampanha(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        model.addAttribute("participantes", listaParticipantes);
        return "campanha-participantes";
    }

    @GetMapping("/{codCampanha}/personagens")
    public String obterPersonagens(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        List<PersonagemResponseDTO> listaPersonagens = personagemService.listarPersonagensPorCampanha(codCampanha);
        model.addAttribute("personagens", listaPersonagens);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-personagens";
    }

    @GetMapping("/{codCampanha}/{codPersonagem}/{nome}")
    public String obterPersonagem(@PathVariable Integer codCampanha, @PathVariable String nome, @PathVariable Integer codPersonagem, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        PersonagemResponseDTO personagemResponseDTO = personagemService.obterPersonagemPorIdResponse(codPersonagem);
        ProficienciaResponseDTO proficienciaResponseDTO = proficienciaService.obterProficienciaPorIdResponse(codPersonagem);
        OutraProficienciaResponseDTO outraProficienciaResponseDTO = outraProficienciaService.obterOutraProficienciaPorIdResponse(codPersonagem);
        PericiasResponseDTO periciasResponseDTO = periciasService.obterPericiaPorIdResponse(codPersonagem);
        AtributosResponseDTO atributosResponseDTO = atributosService.obterAtributoPorIdResponse(codPersonagem);
        TalentoTracoResponseDTO talentoTracoResponseDTO = talentoTracoService.obterTalentoTracoPorIdResponse(codPersonagem);
        MoedasResponseDTO moedasResponseDTO = moedasService.obterMoedaPorIdResponse(codPersonagem);
        AtaquesConjuracaoResponseDTO ataquesConjuracaoResponseDTO = ataquesConjuracaoService.obterAtaqueConjuracaoPorIdResponse(codPersonagem);
        EquipamentoResponseDTO equipamentoResponseDTO = equipamentoService.obterEquipamentoPorIdResponse(codPersonagem);
        MagiaResponseDTO magiaResponseDTO = magiaService.obterMagiaPorIdResponse(codPersonagem);
        model.addAttribute("personagem", personagemResponseDTO);
        model.addAttribute("campanha", campanhaResponseDTO);
        model.addAttribute("proficiencias", proficienciaResponseDTO);
        model.addAttribute("outraproficiencias", outraProficienciaResponseDTO);
        model.addAttribute("pericias", periciasResponseDTO);
        model.addAttribute("atributos", atributosResponseDTO);
        model.addAttribute("talentotracos", talentoTracoResponseDTO);
        model.addAttribute("moedas", moedasResponseDTO);
        model.addAttribute("ataquesconjuracoes", ataquesConjuracaoResponseDTO);
        model.addAttribute("equipamentos", equipamentoResponseDTO);
        model.addAttribute("magias", magiaResponseDTO);
        return "campanha-personagem";
    }

    @GetMapping("/{codCampanha}/criar-personagem")
    public String criarPersonagem(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-criar-personagem";
    }

    @GetMapping("/{codCampanha}/mapa")
    public String obterMapa(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-mapa";
    }

    @GetMapping("/{codCampanha}/regras")
    public String obterRegras(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-regras";
    }

    @GetMapping("/{codCampanha}/rolagens")
    public String obterRolagens(@PathVariable Integer codCampanha, Model model){
        CampanhaResponseDTO campanhaResponseDTO = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        model.addAttribute("campanha", campanhaResponseDTO);
        return "campanha-rolagens";
    }

}
