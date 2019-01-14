package com.brunno.desafio.dto;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.brunno.desafio.domain.Contrato;

public class ContratoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long servicoId;
	private String servico;

	private Long cliente;
	
	private String nome;
	
	private Date inicio;

	private Date fim;

	private Long diaFim;

	private Double vlrPgto;
	
	private Long empresaId;
	private String empresa;

	public ContratoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ContratoDTO(Contrato obj) {
		this.id = obj.getId();
		this.servico = obj.getServico().getTitulo();
		this.servicoId = obj.getServico().getId();
		this.empresaId = obj.getServico().getEmpresa().getId();
		this.inicio = obj.getInicio();
		this.fim = obj.getFim();
		this.vlrPgto = obj.getServico().getValor();
		this.cliente = obj.getCliente().getId();
		this.setCliente(obj.getCliente().getId());
		this.nome = obj.getCliente().getNome();
		this.empresa = obj.getServico().getEmpresa().getNome();
		
		DateTime atual = new DateTime(System.currentTimeMillis());
		DateTime fina = new DateTime(obj.getFim());
		
		Days dias = Days.daysBetween(atual, fina);
		Double desconto = obj.getCliente().getTipoCliente().getDesconto();

		this.diaFim = (long) dias.getDays() + 1;

		desconto = (this.diaFim >= 10) ? desconto + 5 : desconto;

		this.vlrPgto = vlrPgto - (vlrPgto * (desconto / 100));

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Long getDiaFim() {
		return diaFim;
	}

	public void setDiaFim(Long diaFim) {
		this.diaFim = diaFim;
	}

	public Double getVlrPgto() {
		return vlrPgto;
	}

	public void setVlrPgto(Double vlrPgto) {
		this.vlrPgto = vlrPgto;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public Long getServicoId() {
		return servicoId;
	}

	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}

}
