package com.brunno.desafio.repositories.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.Cliente_;
import com.brunno.desafio.repositories.filter.ClienteFilter;

public class ClienteRepositoryQueryImpl implements ClienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Cliente> buscaPorFiltro(ClienteFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		// criar as restrições
		Predicate[] predicates = criarPredicate(filter, builder, root);
		criteria.where(predicates);
		List<Order> ordens = new ArrayList<>();
		ordens.add(builder.asc((root.get(Cliente_.nome))));
		criteria.orderBy(ordens);

		TypedQuery<Cliente> query = manager.createQuery(criteria);
		addRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter, predicates));
	}

	private Long total(ClienteFilter filter, Predicate[] predicates) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cliente> root = criteria.from(Cliente.class);

		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void addRestricoesDePaginacao(TypedQuery<Cliente> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primerioRegistroDaPagina = paginaAtual * totalRegistroPorPagina;

		query.setFirstResult(primerioRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarPredicate(ClienteFilter filter, CriteriaBuilder builder, Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(
					builder.like(builder.lower(root.get(Cliente_.nome)), "%" + filter.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(filter.getCpfCnpj())) {
			predicates.add(
					builder.like(builder.lower(root.get(Cliente_.cpfCnpj)), "%" + filter.getCpfCnpj() + "%"));
		}

		if (!StringUtils.isEmpty(filter.getEmail())) {
			predicates.add(
					builder.like(builder.lower(root.get(Cliente_.email)), "%" + filter.getEmail() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);

	}

}
