package com.brunno.desafio.resources;

import java.util.List;
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

import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.dto.ServicoDTO;
import com.brunno.desafio.dto.ServicoListDTO;
import com.brunno.desafio.event.CriarEvento;
import com.brunno.desafio.repositories.filter.ServicoFilter;
import com.brunno.desafio.services.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoResource {

	@Autowired
	private ServicoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	private Page<ServicoDTO> buscaPorFiltro(ServicoFilter filter, Pageable pageable) {
		return service.listaServico(filter, pageable);
	}
	
	@GetMapping(path="/lista")
	private List<ServicoListDTO> listaServicos(){
		return service.lista();
	} 

	@GetMapping("/{id}")
	private ResponseEntity<Servico> buscaPorId(@PathVariable Long id) {
		Optional<Servico> obj = service.buscarPorId(id);
		return obj.isPresent() ? ResponseEntity.ok(obj.get()) : ResponseEntity.noContent().build();
	}

	@GetMapping("/empresa/{id}")
	private Page<ServicoDTO> buscaPorEmpresa(@PathVariable Long id, ServicoFilter filter, Pageable pageable) {
		filter.setEmpresa(id);
		return service.listaServicoEmpresa(filter, pageable);
	}

	@PostMapping
	public ResponseEntity<Servico> novo(@Valid @RequestBody Servico obj, HttpServletResponse response) {
		Servico objSalvo = service.adicionar(obj);
		publisher.publishEvent(new CriarEvento(this, response, objSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(objSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Servico> alterar(@PathVariable Long id, @RequestBody Servico obj) {
		try {
			service.atualizar(id, obj);
			return ResponseEntity.ok(obj);
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
