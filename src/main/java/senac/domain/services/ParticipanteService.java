package senac.domain.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.domain.dtos.requests.ParticipanteRequestDTO;
import senac.domain.dtos.responses.CampanhaResponseDTO;
import senac.domain.dtos.responses.ParticipanteResponseDTO;
import senac.domain.dtos.responses.UsuarioResponseDTO;
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

    public void criarParticipante(ParticipanteRequestDTO participanteDto) {
        Integer codUsuario = participanteDto.getCodUsuario();
        Integer codCampanha = participanteDto.getCodCampanha();

        participanteDto.setCargo("Expectador");
        participanteDto.setAdmMaster(false);
        participanteDto.setAdm(false);

        CampanhaResponseDTO campanhaExistente = campanhaService.obterCampanhaPorIdResponse(codCampanha);
        UsuarioResponseDTO usuarioExistente = usuarioService.obterUsuarioPorIdResponse(codUsuario);

        System.out.println("Existe na campanha: " + participanteRepository.existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(codUsuario, codCampanha));
        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDto);
        participanteModel.setCampanhaModel(campanhaMapper.toEntity(campanhaExistente));
        participanteModel.setUsuarioModel(usuarioMapper.toEntity(usuarioExistente));
        usuarioTemParticipanteNaCampanha(codUsuario, codCampanha);
        participanteRepository.save(participanteModel);
    }

    public void criarPrimeiroParticipante(CampanhaModel campanha) {
        ParticipanteResponseDTO participanteDonoDto = new ParticipanteResponseDTO();

        // pegar via autenticação
        Integer codUsuario = 1; //participanteDonoDto.getCodUsuario();

        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDonoDto);

        participanteModel.setNome("Participante 1");
        participanteModel.setUsuarioModel(usuarioMapper.toEntity(usuarioService.obterUsuarioPorIdRequest(codUsuario))); // puxar a autenticação do usuário para pegar seu id
        participanteModel.setCampanhaModel(campanha);
        participanteModel.setAdm(true);
        participanteModel.setAdmMaster(true);
        participanteModel.setCargo("Mestre");
        participanteRepository.save(participanteModel);



//        ParticipanteDTO participanteDonoDto = new ParticipanteDTO();
//
//        // pegar via autenticação
//        Integer codUsuario = 1; //participanteDonoDto.getCodUsuario();
//
//        CampanhaRequestDTO campanhaExistente = campanhaService.obterCampanhaPorIdRequest(codCampanha);
//        UsuarioRequestDTO usuarioExistente = usuarioService.obterUsuarioPorIdRequest(codUsuario);
//
//        ParticipanteModel participanteModel = participanteMapper.toEntity(participanteDonoDto);
//
//        participanteModel.setNome("Participante 1");
//        participanteModel.setUsuarioModel(usuarioMapper.toEntity(usuarioExistente)); // puxar a autenticação do usuário para pegar seu id
//        participanteModel.setCampanhaModel(campanhaMapper.toEntity(campanhaExistente));
//        participanteModel.setAdm(true);
//        participanteModel.setAdmMaster(true);
//        participanteModel.setCargo("Mestre");
//        participanteRepository.save(participanteModel);
    }

    public void atualizarParticipante(Integer id, ParticipanteRequestDTO participanteDto) {
//        if(!(id.equals(participanteDto.getCodUsuario()))){
//            throw new RuntimeException("Não é possível alterar o codigo de usuário.");


//        }
        ParticipanteRequestDTO participanteExistente = obterParticipantePorIdRequest(id);

        Integer codUsuario = participanteExistente.getCodUsuario();
        Integer codCampanha = participanteExistente.getCodCampanha();

        UsuarioModel usuarioExistente =  usuarioMapper.toEntity(usuarioService.obterUsuarioPorIdRequest(codUsuario));
        CampanhaModel campanhaExistente = campanhaMapper.toEntity(campanhaService.obterCampanhaPorIdRequest(codCampanha));

        if(!(usuarioExistente.getCodUsuario().equals(participanteDto.getCodUsuario()))){
            throw new RuntimeException("Não é possível desvincular um participante de um usuário.");
        }
        else if(!(campanhaExistente.getCodCampanha().equals(participanteDto.getCodCampanha()))){
            throw new RuntimeException("Não é possível desvincular um participante de uma campanha.");

        }


        if(!(participanteExistente.isAdm() || participanteExistente.isAdmMaster())){
            participanteDto.setAdm(false);
            participanteDto.setAdmMaster(false);
            participanteDto.setCargo(participanteExistente.getCargo());
        }


        //System.out.println(participanteRepository.existsByUsuarioModel_CodUsuarioAndCampanhaModel_CodCampanha(codUsuario, codCampanha));
        ParticipanteModel participanteAtualizado = participanteMapper.toEntity(participanteDto);
        participanteAtualizado.setCodParticipante(id);
        participanteAtualizado.setUsuarioModel(usuarioExistente);
        participanteAtualizado.setCampanhaModel(campanhaExistente);
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
