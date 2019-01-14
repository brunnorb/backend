package com.brunno.desafio.dto;

import java.io.Serializable;

import com.brunno.desafio.domain.Servico;

public class ServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String titulo;

	private String empresa;

	private String descricao;

	private Double valor;

	public ServicoDTO() {

	}

	public ServicoDTO(Servico obj) {
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.empresa = obj.getEmpresa().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

  
	
}
