package com.brunno.desafio.repositories.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.repositories.filter.ServicoFilter;


public interface ServicoRepositoryQuery {
	
	public Page<Servico> buscaPorFiltro(ServicoFilter filter, Pageable pageable);
	
}
