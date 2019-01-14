package com.brunno.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.repositories.servico.ServicoRepositoryQuery;

public interface ServicoRepository extends JpaRepository<Servico, Long>, ServicoRepositoryQuery {
	boolean existsByTituloIgnoreCaseAndEmpresa(String titulo, Empresa empresa);
}
