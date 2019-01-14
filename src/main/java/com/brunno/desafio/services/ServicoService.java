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

import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.dto.ServicoDTO;
import com.brunno.desafio.dto.ServicoListDTO;
import com.brunno.desafio.repositories.EmpresaRepository;
import com.brunno.desafio.repositories.ServicoRepository;
import com.brunno.desafio.repositories.filter.ServicoFilter;
import com.brunno.desafio.services.exception.EmpresaInexistenteException;
import com.brunno.desafio.services.exception.ServicoJaCadastradoException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repository;

	@Autowired
	private EmpresaRepository empresaRepository;

	public Optional<Servico> buscarPorId(Long id) {
		Optional<Servico> obj = repository.findById(id);
		return obj;
	}

	public Page<ServicoDTO> listaServico(ServicoFilter filter, Pageable pageable) {
		Page<Servico> page = repository.buscaPorFiltro(filter, pageable);
		List<ServicoDTO> listDto = page.getContent().stream().map(obj -> new ServicoDTO(obj))
				.collect(Collectors.toList());
		return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());

	}

	public Page<ServicoDTO> listaServicoEmpresa(ServicoFilter filter, Pageable pageable) {
		validarEmpresa(filter.getEmpresa());

		Page<Servico> page = repository.buscaPorFiltro(filter, pageable);
		List<ServicoDTO> listDto = page.getContent().stream().map(obj -> new ServicoDTO(obj))
				.collect(Collectors.toList());
		return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
	}

	private void validarEmpresa(Long id) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		if (!empresa.isPresent()) {
			throw new EmpresaInexistenteException();
		}

	}

	private void validarServico(Servico obj) {
		validarEmpresa(obj.getEmpresa().getId());
		if (repository.existsByTituloIgnoreCaseAndEmpresa(obj.getTitulo(), obj.getEmpresa())) {
			throw new ServicoJaCadastradoException();
		}
	}

	public Servico adicionar(Servico obj) {
		validarServico(obj);
		return repository.save(obj);
	}

	public Servico atualizar(Long id, Servico obj) {
		Optional<Servico> objSalvo = repository.findById(id);
		if (!objSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		if (!obj.getTitulo().equals(objSalvo.get().getTitulo())) {

			validarServico(obj);
		}
		BeanUtils.copyProperties(obj, objSalvo.get(), "id");
		return repository.save(objSalvo.get());

	}

	public void deletar(Long id) { // Brunno
		repository.deleteById(id);
	}

	public List<ServicoListDTO> lista() {
		List<ServicoListDTO> lista = repository.findAll().stream().map(obj -> new ServicoListDTO(obj))
				.collect(Collectors.toList());		
		return lista;
	}

}
