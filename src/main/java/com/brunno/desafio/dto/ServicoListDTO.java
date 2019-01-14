package com.brunno.desafio.dto;

import java.io.Serializable;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.domain.Servico;

public class ServicoListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long code;

	private String name;

	public ServicoListDTO() {

	}

	public ServicoListDTO(Servico obj) {
		this.code = obj.getId();
		this.name = obj.getTitulo();
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
