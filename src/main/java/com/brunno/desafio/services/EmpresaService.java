package com.brunno.desafio.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	public Empresa atualizar(Long id, Empresa obj) {
		Optional<Empresa> objSalvo = repository.findById(id);
		if (!objSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(obj, objSalvo.get(), "id");
		return repository.save(objSalvo.get());

	}

	public Optional<Empresa> buscarPorId(Long id) {
		Optional<Empresa> obj = repository.findById(id);
		return obj;
	}
}
