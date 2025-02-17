package br.com.psicuida.repository;

import br.com.psicuida.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
	
    // Método para buscar avaliação pelo pacienteId
	Avaliacao findByPaciente_Id(Long pacienteId);	
}
