package com.brunno.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.TipoCliente;

public interface TipoClienteRepository  extends JpaRepository<TipoCliente, Long>{
	boolean existsById(Long id);
}
