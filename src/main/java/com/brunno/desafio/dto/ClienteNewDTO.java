package com.brunno.desafio.dto;

import java.io.Serializable;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.TipoCliente;

public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;

	private String cpfCnpj;

	private String email;

	private TipoCliente tipoCliente;


	public ClienteNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteNewDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpfCnpj = obj.getCpfCnpj();
		this.email = obj.getEmail();
		this.tipoCliente = obj.getTipoCliente();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
