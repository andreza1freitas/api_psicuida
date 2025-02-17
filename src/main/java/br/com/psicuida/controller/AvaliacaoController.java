package br.com.psicuida.controller;

import br.com.psicuida.entity.Avaliacao;
import br.com.psicuida.model.AvaliacaoDTO;
import br.com.psicuida.repository.AvaliacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    // Endpoint para criar ou atualizar avaliação
    @PostMapping
    public ResponseEntity<Avaliacao> criarOuAtualizarAvaliacao(@RequestBody Avaliacao avaliacao) {
        if (avaliacao.getPaciente() == null || avaliacao.getPaciente().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Long pacienteId = avaliacao.getPaciente().getId();

        // Verifica se o paciente já tem uma avaliação
        Avaliacao avaliacaoExistente = avaliacaoRepository.findByPaciente_Id(pacienteId);

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
    @Transactional
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        List<AvaliacaoDTO> avaliacaoDTOs = avaliacoes.stream()
                .map(a -> new AvaliacaoDTO(a.getId(), a.getComentario(), a.getNota(), a.getPaciente().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(avaliacaoDTOs);
    }
}
