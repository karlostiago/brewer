package com.algaworks.brewer.repository.helper.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractHelperImpl<T> {
	
	@PersistenceContext
	private EntityManager manager;
	
	public final void paginator(Criteria criteria, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalDeRegistroPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalDeRegistroPorPagina);
		
		Sort sort = pageable.getSort();
		if(sort != null && !sort.equals(Sort.unsorted())) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
	}
	
	public Criteria getSession(Class<T> typeClass) {
		return (Criteria) manager.unwrap(Session.class).createCriteria(typeClass);
	}
	
	public EntityManager getManager() {
		return manager;
	}
}
