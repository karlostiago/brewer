package com.algaworks.brewer.repository.helper.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.repository.helper.CidadesQueries;
import com.algaworks.brewer.util.StringUtil;

public class CidadesImpl extends AbstractHelperImpl<Cidade> implements CidadesQueries {

	@Override
	@Transactional(readOnly = true)
	public boolean isExist(Cidade cidade, Estado estado) {
		
		Criteria criteria = getSession(Cidade.class);
		criteria.createAlias("estado", "estado", JoinType.INNER_JOIN);

		criteria.add(Restrictions.ilike("nome", StringUtil.removerAcentos(cidade.getNome()).toUpperCase(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("estado", estado));
		
		return criteria.uniqueResult() != null ? true : false;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable) {
		Criteria criteria = getSession(Cidade.class);
		criteria.createAlias("estado", "estado", JoinType.LEFT_OUTER_JOIN);
		paginator(criteria, pageable);
		adicionarFiltro(filter, criteria);	
		
		return new PageImpl<Cidade>(criteria.list(), pageable, total(filter));
	}
	
	private void adicionarFiltro(CidadeFilter filter, Criteria criteria) {
		if(filter != null) {
			if(isEstadoPresente(filter)) {
				criteria.add(Restrictions.eq("estado", filter.getEstado()));
			}
			
			if(!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", StringUtil.removerAcentos(filter.getNome()).toUpperCase(), MatchMode.ANYWHERE));
			}
		}
	}
	
	private Long total(CidadeFilter filter) {
		Criteria criteria = getSession(Cidade.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private boolean isEstadoPresente(CidadeFilter filter) {
		return filter.getEstado() != null && filter.getEstado().getCodigo() != null;
	}
}
