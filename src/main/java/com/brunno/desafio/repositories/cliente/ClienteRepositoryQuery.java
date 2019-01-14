package com.brunno.desafio.repositories.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.repositories.filter.ClienteFilter;


public interface ClienteRepositoryQuery {
	
	public Page<Cliente> buscaPorFiltro(ClienteFilter filter, Pageable pageable);
	
}
