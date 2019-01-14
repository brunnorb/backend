package com.brunno.desafio.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunno.desafio.domain.Contrato;
import com.brunno.desafio.dto.ContratoDTO;
import com.brunno.desafio.event.CriarEvento;
import com.brunno.desafio.repositories.ContratoRepository;
import com.brunno.desafio.services.ContratoService;

@RestController
@RequestMapping(value = "/contratos")
public class ContratoResource {

	@Autowired
	private ContratoService service;

	@Autowired
	private ContratoRepository repo;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Contrato> findAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<ContratoDTO> buscaPorId(@PathVariable Long id) {
		Optional<Contrato> obj = service.buscarPorId(id);
		
		ContratoDTO dto = null;
		if (obj.isPresent()) {
			dto = new ContratoDTO(obj.get());
		}
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Contrato> novo(@RequestBody ContratoDTO obj, HttpServletResponse response) {
		Contrato objSalvo = service.adicionar(obj);
		publisher.publishEvent(new CriarEvento(this, response, objSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(objSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.deletar(id);
	}

}
