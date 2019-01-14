package com.brunno.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
}
