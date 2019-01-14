package com.brunno.desafio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.Contrato;
import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.dto.ContratoDTO;
import com.brunno.desafio.repositories.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository repository;


	private Contrato convertToContrato(ContratoDTO objDto) {
		Cliente cliente = new Cliente();
		cliente.setId(objDto.getCliente());

		Servico servico = new Servico();
		servico.setId(objDto.getServicoId());
		Contrato obj = new Contrato();
		obj.setCliente(cliente);
		obj.setServico(servico);
		obj.setInicio(objDto.getInicio());
		obj.setFim(objDto.getFim());
		return obj;
	}

	public Optional<Contrato> buscarPorId(Long id) {
		Optional<Contrato> obj = repository.findById(id);

		return obj;
	}


	public Contrato adicionar(ContratoDTO objDto) {
		Contrato obj = convertToContrato(objDto);
		System.out.println(obj.getCliente().getId());
		System.out.println(obj.getFim());
		System.out.println(obj.getInicio());
		System.out.println(obj.getServico().getId());
		
		
		return repository.save(obj);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}
}
