package com.brunno.desafio.repositories.servico;

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

import com.brunno.desafio.domain.Empresa_;
import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.domain.Servico_;
import com.brunno.desafio.repositories.filter.ServicoFilter;

public class ServicoRepositoryQueryImpl implements ServicoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Servico> buscaPorFiltro(ServicoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Servico> criteria = builder.createQuery(Servico.class);
		Root<Servico> root = criteria.from(Servico.class);
		// criar as restrições
		Predicate[] predicates = criarPredicate(filter, builder, root);
		criteria.where(predicates);
		List<Order> ordens = new ArrayList<>();
		ordens.add(builder.desc((root.get(Servico_.valor))));
		criteria.orderBy(ordens);

		TypedQuery<Servico> query = manager.createQuery(criteria);
		addRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(filter, predicates));
	}

	private Long total(ServicoFilter filter, Predicate[] predicates) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Servico> root = criteria.from(Servico.class);

		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void addRestricoesDePaginacao(TypedQuery<Servico> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primerioRegistroDaPagina = paginaAtual * totalRegistroPorPagina;

		query.setFirstResult(primerioRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Predicate[] criarPredicate(ServicoFilter filter, CriteriaBuilder builder, Root<Servico> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getTitulo())) {
			predicates.add(
					builder.like(builder.lower(root.get(Servico_.titulo)), "%" + filter.getTitulo().toLowerCase() + "%"));
		}

		if (filter.getEmpresa() != null ) {
			predicates.add(builder.equal(root.get(Servico_.empresa).get(Empresa_.id) , filter.getEmpresa()));
		}
		
		if (filter.getValorIni()!= null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Servico_.valor),
					filter.getValorIni()));
		}

		if (filter.getValorFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Servico_.valor),
					filter.getValorFim()));
		}
		

		return predicates.toArray(new Predicate[predicates.size()]);

	}

}
