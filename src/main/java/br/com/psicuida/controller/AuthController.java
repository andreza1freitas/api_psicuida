package br.com.psicuida.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.psicuida.entity.Paciente;
import br.com.psicuida.enums.Situacao;
import br.com.psicuida.model.SocialLoginDTO;
import br.com.psicuida.repository.PacienteRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
    public AuthController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
	    Optional<Paciente> paciente = pacienteRepository.findByEmail(loginRequest.getEmail());

	    if (paciente.isPresent() && paciente.get().getSenha().equals(loginRequest.getSenha())) {

	        Map<String, Object> response = new HashMap<>();
	        response.put("userId", paciente.get().getId());
	        response.put("userName", paciente.get().getNome());

	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	}
	
	@PostMapping("/social-login")
	public ResponseEntity<?> socialLogin(@RequestBody SocialLoginDTO request) {
	    Optional<Paciente> existingUser = pacienteRepository.findByEmail(request.getEmail());

	    Paciente user;
	    if (existingUser.isPresent()) {
	        user = existingUser.get();

	        // Se o usuário estiver inativo, reativa-o e atualiza os dados
	        if (user.getSituacao() == Situacao.INATIVO) {
	            user.setSituacao(Situacao.ATIVO);  
	        }

	        // Atualiza o socialId e o socialProvider, se necessário
	        user.setSocialId(request.getProviderId());
	        user.setSocialProvider(request.getProvider());
	        pacienteRepository.save(user);

	    } else {
	        // Caso o usuário não exista, cria um novo
	        user = new Paciente();
	        user.setNome(request.getName());
	        user.setEmail(request.getEmail());
	        user.setSocialId(request.getProviderId());
	        user.setSocialProvider(request.getProvider());
	        user.setSituacao(Situacao.ATIVO); 
	        pacienteRepository.save(user);
	    }

	    // Retorna a resposta com o ID e o nome do usuário
	    Map<String, Object> response = new HashMap<>();
	    response.put("userId", user.getId());
	    response.put("userName", user.getNome());

	    return ResponseEntity.ok(response);
	}

	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate(); // Invalida a sessão atual
		return ResponseEntity.ok("Logged out successfully");
	}

	@Data
	public static class LoginRequest {
        private String email;
        private String senha;
	}
}
