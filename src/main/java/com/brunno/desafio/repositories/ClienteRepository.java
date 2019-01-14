package com.brunno.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.repositories.cliente.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {
	boolean existsByCpfCnpj(String nome);

}
