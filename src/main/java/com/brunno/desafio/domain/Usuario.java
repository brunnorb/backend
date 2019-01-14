package com.brunno.desafio.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	private String email;
	
	@NotNull
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name= "usuario_permissao",joinColumns = @JoinColumn(name= "usuario_id"),
			   inverseJoinColumns = @JoinColumn(name="permissao_id")
	)
	private List<Permissao> permissoes;
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(Long id, @Email String email, @NotNull String senha, @NotNull Empresa empresa) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.empresa = empresa;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
