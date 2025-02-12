package br.com.psicuida.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conteudo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = true)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pergunta_id")
    private Pergunta pergunta;
    
    @CreationTimestamp
    private LocalDate data;

}
