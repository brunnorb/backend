package com.brunno.desafio.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.services.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService service;

	@GetMapping("/{id}")
	private ResponseEntity<Empresa> buscaPorId(@PathVariable Long id) {
		Optional<Empresa> obj = service.buscarPorId(id);
		return  obj.isPresent() ? ResponseEntity.ok(obj.get()) : ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empresa> alterar(@PathVariable Long id, @RequestBody Empresa obj) {
		try {
			service.atualizar(id, obj);
			return ResponseEntity.ok(obj);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
