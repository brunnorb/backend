package com.brunno.desafio.exceptionhandler;

public class Erro {
	private String mensagem;
	private String mensagemDev;

	public Erro(String mensagem, String mensagemDev) {
		this.mensagem = mensagem;
		this.mensagemDev = mensagemDev;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagemDev() {
		return mensagemDev;
	}

	public void setMensagemDev(String mensagemDev) {
		this.mensagemDev = mensagemDev;
	}

}