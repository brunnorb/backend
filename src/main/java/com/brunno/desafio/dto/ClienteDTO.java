package com.brunno.desafio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.brunno.desafio.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;

	private String cpfCnpj;

	private String email;

	private String tipoCliente;
	
	private Long tipoId;
	
	private List<ContratoDTO> contratos = new ArrayList<>();

	public List<ContratoDTO> getContratos() {
		return contratos;
	}

	public void setContratos(List<ContratoDTO> contratos) {
		this.contratos = contratos;
	}

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpfCnpj = obj.getCpfCnpj();
		this.email = obj.getEmail();
		this.tipoCliente = obj.getTipoCliente().getTitulo();
		this.setTipoId(obj.getTipoCliente().getId());
		this.contratos = obj.getContratos().stream().map(objCont -> new ContratoDTO(objCont)).collect(Collectors.toList());
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

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Long getTipoId() {
		return tipoId;
	}

	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}

}
