package com.brunno.desafio.resources;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.dto.ClienteDTO;
import com.brunno.desafio.dto.ClienteNewDTO;
import com.brunno.desafio.event.CriarEvento;
import com.brunno.desafio.repositories.filter.ClienteFilter;
import com.brunno.desafio.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	private Page<ClienteDTO> buscaPorFiltro(ClienteFilter filter, Pageable pageable) {
		return service.listaCliente(filter, pageable);
	}

	@GetMapping("/{id}")
	private ResponseEntity<ClienteDTO> buscaPorId(@PathVariable Long id) {
		Optional<Cliente> obj = service.buscarPorId(id);
		ClienteDTO dto = null;
		if (obj.isPresent()) {
			dto = new ClienteDTO(obj.get());
		}
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<Cliente> novo(@Valid @RequestBody ClienteNewDTO obj, HttpServletResponse response) {
		Cliente cliente = new Cliente(obj);
		Cliente objSalvo = service.adicionar(cliente);
		publisher.publishEvent(new CriarEvento(this, response, objSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(objSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @RequestBody ClienteNewDTO obj) {
		try {
			Cliente cliente = new Cliente(obj);
			service.atualizar(id, cliente);
			return ResponseEntity.ok(cliente);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.deletar(id);
	}
}
