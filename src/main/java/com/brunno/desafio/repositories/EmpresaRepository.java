package com.brunno.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
}
