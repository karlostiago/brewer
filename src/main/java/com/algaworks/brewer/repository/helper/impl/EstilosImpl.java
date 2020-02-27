package com.algaworks.brewer.repository.helper.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.filter.EstiloFilter;
import com.algaworks.brewer.repository.helper.EstilosQueries;

public class EstilosImpl extends AbstractHelperImpl implements EstilosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Estilo> filtrar(EstiloFilter filter, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		
		paginator(criteria, pageable);
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<Estilo>(criteria.list(), pageable, total(filter));
	}

	private void adicionarFiltro(EstiloFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(EstiloFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
