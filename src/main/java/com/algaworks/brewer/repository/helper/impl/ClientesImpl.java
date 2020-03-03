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

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.filter.ClienteFilter;
import com.algaworks.brewer.repository.helper.ClientesQueries;

public class ClientesImpl extends AbstractHelperImpl<Cliente> implements ClientesQueries {
		
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {
		
		Criteria criteria = getSession(Cliente.class);
		paginator(criteria, pageable);
		adicionarFiltro(filter, criteria);
		
		criteria.createAlias("endereco.cidade", "cidade", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("cidade.estado", "estado", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<Cliente>(criteria.list(), pageable, total(filter));
	}

	private void adicionarFiltro(ClienteFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filter.getCpfCnpj())) {
				criteria.add(Restrictions.eq("cpfCnpj", filter.getCpfCnpj()));
			}
		}
	}

	private Long total(ClienteFilter filter) {
		Criteria criteria = getSession(Cliente.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
