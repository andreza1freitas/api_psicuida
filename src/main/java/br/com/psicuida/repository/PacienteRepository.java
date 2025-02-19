package br.com.psicuida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.psicuida.entity.Paciente;

@Repository 
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	@Query("""
			SELECT paciente FROM Paciente paciente
			WHERE paciente.email = :email
			""")
	
	Optional<Paciente> findByEmail(String email);

}
