package com.brunno.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunno.desafio.domain.TipoCliente;
import com.brunno.desafio.repositories.TipoClienteRepository;
import com.brunno.desafio.services.exception.TipoClienteInexistenteException;

@Service
public class TipoClienteService {

	@Autowired
	private TipoClienteRepository repository;

	public void existeTipoCliente(TipoCliente obj) {
		System.out.println(obj.getId());
		if (!repository.existsById(obj.getId())) {
			throw new TipoClienteInexistenteException();
		}

	}

}
