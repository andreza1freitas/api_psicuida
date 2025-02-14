package br.com.psicuida.controller;

import br.com.psicuida.entity.Avaliacao;
import br.com.psicuida.repository.AvaliacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }
    
 // Endpoint para criar ou atualizar avaliação
    @PostMapping	
    public ResponseEntity<Avaliacao> criarOuAtualizarAvaliacao(@RequestBody Avaliacao avaliacao) {
        if (avaliacao.getPacienteId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Verifica se o paciente já tem uma avaliação
        Avaliacao avaliacaoExistente = avaliacaoRepository.findByPacienteId(avaliacao.getPacienteId());
        
        if (avaliacaoExistente != null) {
            // Atualiza a avaliação existente
            avaliacaoExistente.setNota(avaliacao.getNota());
            avaliacaoExistente.setComentario(avaliacao.getComentario());
            Avaliacao atualizada = avaliacaoRepository.save(avaliacaoExistente);
            return ResponseEntity.ok(atualizada);
        }

        // Cria uma nova avaliação
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.ok(novaAvaliacao);
    }

    @GetMapping
    public ResponseEntity<?> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

}
