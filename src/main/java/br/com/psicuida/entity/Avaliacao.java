package br.com.psicuida.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao")
public class Avaliacao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int nota; 

    @Column(length = 500)
    private String comentario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
