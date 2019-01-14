package com.brunno.desafio.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.Contrato;
import com.brunno.desafio.domain.Servico;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	boolean existsByServicoAndClienteAndInicioAndFim(Servico servico,Cliente cliente, Date inicio,Date fim);
	
}
