package com.brunno.desafio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.dto.ClienteDTO;
import com.brunno.desafio.repositories.ClienteRepository;
import com.brunno.desafio.repositories.filter.ClienteFilter;
import com.brunno.desafio.services.exception.ClienteJaCadastradoException;
import com.brunno.desafio.services.exception.CpfOuCnpjInvalidoException;
import com.brunno.desafio.services.validation.utils.BR;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private TipoClienteService tipoClienteService;

	public Page<ClienteDTO> listaCliente(ClienteFilter filter, Pageable pageable) {
		Page<Cliente> page = repository.buscaPorFiltro(filter, pageable);
		List<ClienteDTO> listDto = page.getContent().stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
	}

	private void validarCliente(Cliente obj) {
		tipoClienteService.existeTipoCliente(obj.getTipoCliente());

		if (repository.existsByCpfCnpj(obj.getCpfCnpj())) {
			throw new ClienteJaCadastradoException();
		}

		if (!BR.isValidCNPJ(obj.getCpfCnpj())) {
			throw new CpfOuCnpjInvalidoException();
		}

	}

	public Cliente adicionar(Cliente obj) {
		validarCliente(obj);
		return repository.save(obj);
	}

	public Cliente atualizar(Long id, Cliente obj) {
		Optional<Cliente> objSalvo = repository.findById(id);

		if (!objSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		if (!obj.getCpfCnpj().equals(objSalvo.get().getCpfCnpj())) {
			validarCliente(obj);
		}

		tipoClienteService.existeTipoCliente(obj.getTipoCliente());

		BeanUtils.copyProperties(obj, objSalvo.get(), "id");
		return repository.save(objSalvo.get());

	}

	public void deletar(Long id) { // Brunno
		repository.deleteById(id);
	}

	public Optional<Cliente> buscarPorId(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj;
	}

}
