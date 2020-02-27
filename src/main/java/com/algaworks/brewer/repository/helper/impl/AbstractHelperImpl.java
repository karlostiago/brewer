package com.algaworks.brewer.repository.helper.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class AbstractHelperImpl {
	
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
}
