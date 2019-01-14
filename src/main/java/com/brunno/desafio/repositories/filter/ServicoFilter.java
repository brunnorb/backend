package com.brunno.desafio.repositories.filter;

public class ServicoFilter {

	private String titulo;

	private Long empresa;

	private Double valorIni;
	
	private Double valorFim;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	public Double getValorIni() {
		return valorIni;
	}

	public void setValorIni(Double valorIni) {
		this.valorIni = valorIni;
	}

	public Double getValorFim() {
		return valorFim;
	}

	public void setValorFim(Double valorFim) {
		this.valorFim = valorFim;
	}
	

}
