package br.com.psicuida.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
	
    private Long id;
    private String comentario;
    private int nota;
    private Long pacienteId; 
    

}

