package com.brunno.desafio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.brunno.desafio.dto.ClienteNewDTO;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 40)
	private String nome;

	@NotNull
	@Size(max = 14)
	private String cpfCnpj;

	public List<Contrato> getContratos() {
		return contratos;
	}


	@Email
	private String email;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "tipoCliente_id")
	private TipoCliente tipoCliente;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Contrato> contratos = new ArrayList<>();

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(ClienteNewDTO obj) {
		this.id = obj.getId();
		this.cpfCnpj = obj.getCpfCnpj();
		this.nome = obj.getNome();
		this.tipoCliente = obj.getTipoCliente();
		this.email = obj.getEmail();
	}

	public Cliente(Long id, @NotNull @Size(min = 3, max = 40) String nome, @NotNull @Size(max = 14) String cpfCnpj,
			@Email String email, TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.tipoCliente = tipoCliente;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
