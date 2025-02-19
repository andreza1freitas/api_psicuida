package br.com.psicuida.model;

import lombok.Data;

@Data
public class SocialLoginDTO {
	
	private String name;
    private String email;
    private String providerId;  
    private String provider;   
}
