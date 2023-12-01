package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.mappers.CampanhaMapper;
import senac.domain.mappers.ParticipanteMapper;
import senac.domain.mappers.UsuarioMapper;
import senac.domain.models.CampanhaModel;
import senac.domain.models.ParticipanteModel;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.ParticipanteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;
    private final UsuarioService usuarioService;
    private final CampanhaService campanhaService;
    private final ParticipanteMapper participanteMapper;
    private final CampanhaMapper campanhaMapper;
    private final UsuarioMapper usuarioMapper;
    @Autowired
    public ParticipanteService(ParticipanteRepository participanteRepository, UsuarioMapper usuarioMapper, UsuarioService usuarioService, ParticipanteMapper participanteMapper, CampanhaService campanhaService, CampanhaMapper campanhaMapper) {
        this.participanteRepository = participanteRepository;
        this.usuarioService = usuarioService;
        this.campanhaService = campanhaService;
        this.usuarioMapper = usuarioMapper;
        this.participanteMapper = participanteMapper;
        this.campanhaMapper = campanhaMapper;
    }

    public List<ParticipanteResponseDTO> listarParticipantesPorCampanha(Integer codCampanha) {
        List<ParticipanteModel> participantes = participanteRepository.findByCampanhaModel_CodCampanha(codCampanha);
//        if(participantes.isEmpty()){
//            throw new EntityNotFoundException("Nenhum participante cadastrado.");
//        }
        return participantes.stream()
                .map(participanteMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public List<ParticipanteResponseDTO> listarParticipantesResponse() {
        List<ParticipanteModel> participantes = participanteRepository.findAll();
        if(participantes.isEmpty()){
            throw new EntityNotFoundException("Nenhum participante cadastrado.");
        }
        return participantes.stream()
                .map(participanteMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ParticipanteRequestDTO> listarParticipantesRequest() {
        List<ParticipanteModel> participantes = participanteRepository.findAll();
        if(participantes.isEmpty()){
            throw new EntityNotFoundException("Nenhum participante cadastrado.");
        }
        return participantes.stream()
                .map(participanteMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    public ParticipanteResponseDTO obterParticipantePorIdResponse(Integer id) {
        listarParticipantesResponse();
        Optional<ParticipanteModel> participanteOptional = participanteRepository.findById(id);
        System.out.println(participanteOptional);
        participanteOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum participante encontrado para o ID fornecido."));
        System.out.println(participanteOptional.map(participanteMapper::toResponseDto));
        return participanteOptional.map(participanteMapper::toResponseDto).orElse(null);
    }

    public ParticipanteRequestDTO obterParticipantePorIdRequest(Integer codParticipante) {
        listarParticipantesRequest();
        Optional<ParticipanteModel> participanteOptional = participanteRepository.findById(codParticipante);
        participanteOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum participante encontrado para o ID fornecido."));
        return participanteOptional.map(participanteMapper::toRequestDto).orElse(null);
    }

    public ParticipanteModel obterParticipanteModelPorId(Integer codParticipante){
        listarParticipantesResponse();
        Optional<ParticipanteModel> participanteOptional = participanteRepository.findById(codParticipante);
        participanteOptional.orElseThrow(() -> new EntityNotFoundException("Nenhum participante encontrado para o ID fornecido."));
        ParticipanteRequestDTO participanteRequestDTO = participanteOptional.map(participanteMapper::toRequestDto).orElse(null);
        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteRequestDTO);
        participanteModel.setCodParticipante(obterParticipantePorIdResponse(codParticipante).getCodParticipante());
        participanteModel.setCampanhaModel(campanhaService.obterCampanhaModelPorId(participanteRequestDTO.getCodCampanha()));
        participanteModel.setUsuarioModel(usuarioService.obterUsuarioModelPorId(participanteRequestDTO.getCodUsuario()));
        return participanteModel;

    }

    public void criarParticipante(ParticipanteRequestDTO participanteDto) {

        CampanhaModel campanhaModel = campanhaService.obterCampanhaModelPorId(participanteDto.getCodCampanha());
        UsuarioModel usuarioModel = usuarioService.obterUsuarioModelPorId(participanteDto.getCodUsuario());

        Integer codUsuario = participanteDto.getCodUsuario();
        Integer codCampanha = participanteDto.getCodCampanha();

        participanteDto.setCargo("Expectador");
        participanteDto.setAdmMaster(false);
        participanteDto.setAdm(false);

        System.out.println("Existe na campanha: " + participanteRepository.existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(codUsuario, codCampanha));
        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDto);
        participanteModel.setCampanhaModel(campanhaModel);
        participanteModel.setUsuarioModel(usuarioModel);
        usuarioTemParticipanteNaCampanha(codUsuario, codCampanha);
        participanteRepository.save(participanteModel);
    }

    public void criarPrimeiroParticipante(CampanhaModel campanha) {
        ParticipanteResponseDTO participanteDonoDto = new ParticipanteResponseDTO();
//
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer codUsuario = null;

        if (authentication != null && authentication.getPrincipal() instanceof UsuarioModel) {
            UsuarioModel usuarioModel = (UsuarioModel) authentication.getPrincipal();
            codUsuario = usuarioModel.getCodUsuario();
        }

        if (codUsuario != null) {
            ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDonoDto);

            participanteModel.setNome("Participante 1");

            UsuarioModel usuarioModel = usuarioMapper.toEntity(usuarioService.obterUsuarioPorIdResponse(codUsuario));
            usuarioModel.setSenha((usuarioService.obterUsuarioPorIdRequest(codUsuario).getSenha()));
            participanteModel.setUsuarioModel(usuarioModel);
            participanteModel.setCampanhaModel(campanha);
            participanteModel.setAdm(true);
            participanteModel.setAdmMaster(true);
            participanteModel.setCargo("Mestre");
            participanteRepository.save(participanteModel);
        } else {
            // Trate o caso em que o código do usuário não é um número inteiro válido
            throw new IllegalStateException("Código de usuário não é um número inteiro válido.");
        }
    }

    public void atualizarParticipante(Integer codParticipante, ParticipanteRequestDTO participanteDto) {
//        if(!(id.equals(participanteDto.getCodUsuario()))){
//            throw new RuntimeException("Não é possível alterar o codigo de usuário.");


//        }
        ParticipanteRequestDTO participanteExistente = obterParticipantePorIdRequest(codParticipante);

        CampanhaModel campanhaModel = campanhaService.obterCampanhaModelPorId(participanteDto.getCodCampanha());
        UsuarioModel usuarioModel = usuarioService.obterUsuarioModelPorId(participanteDto.getCodUsuario());

        if(!(usuarioModel.getCodUsuario().equals(participanteDto.getCodUsuario()))){
            throw new RuntimeException("Não é possível desvincular um participante de um usuário.");
        }
        else if(!(campanhaModel.getCodCampanha().equals(participanteDto.getCodCampanha()))){
            throw new RuntimeException("Não é possível desvincular um participante de uma campanha.");

        }


        if(!(participanteExistente.isAdm() || participanteExistente.isAdmMaster())){
            participanteDto.setAdm(false);
            participanteDto.setAdmMaster(false);
            participanteDto.setCargo(participanteExistente.getCargo());
        }


        //System.out.println(participanteRepository.existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(codUsuario, codCampanha));
        ParticipanteModel participanteAtualizado = participanteMapper.toEntity(participanteDto);
        participanteAtualizado.setCodParticipante(codParticipante);
        participanteAtualizado.setUsuarioModel(usuarioModel);
        participanteAtualizado.setCampanhaModel(campanhaModel);
        // Atualizar o participante no banco de dados
        participanteRepository.save(participanteAtualizado);
    }


    public void excluirParticipante(Integer id) {
        obterParticipantePorIdRequest(id);
        participanteRepository.deleteById(id);
    }
    
    private void usuarioTemParticipanteNaCampanha(Integer codUsuario, Integer codCampanha){
        boolean exists = participanteRepository.existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(codUsuario, codCampanha);
        if (exists) {
            throw new RuntimeException("Usuário já possui um participante nessa campanha.");
        }
//        for(ParticipanteModel participante : participanteRepository.findAll()){
//            if(participante.getUsuarioModel().getCodUsuario().equals(codUsuario) && participante.getCampanhaModel().getCodCampanha().equals(codCampanha)){
//                throw new RuntimeException("Usuário já possui um participante nessa campanha - " + participante.getUsuarioModel().getUsuario());
//            }
//        }
    }




}
