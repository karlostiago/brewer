package com.algaworks.brewer.repository.helper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Grupo;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.model.UsuarioGrupo;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.repository.helper.UsuariosQueries;

public class UsuariosImpl extends AbstractHelperImpl<Usuario> implements UsuariosQueries {

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		Criteria criteria = getSession(Usuario.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
		
		paginator(criteria, pageable);
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<Usuario>(criteria.list(), pageable, total(filter));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> permissoes(Usuario usuario) {
		return getManager().createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
					.setParameter("usuario", usuario)
					.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Optional<Usuario> porEmailAtivo(String email) {
		Criteria criteria = getSession(Usuario.class);
		criteria.add(Restrictions.eq("email", email.toUpperCase()));
		criteria.add(Restrictions.eq("ativo", true));
		return criteria.list().stream().findFirst();
	}
	
	private void adicionarFiltro(UsuarioFilter filter, Criteria criteria) {
		if(filter != null) {
			if(!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filter.getEmail())) {
				criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.ANYWHERE));
			}
			
			if(isGrupoPresente(filter)) {
				List<Criterion> subqueries = new ArrayList<>();
				for(Long codigoGrupo : filter.getGrupos().stream().mapToLong(Grupo::getCodigo).toArray()) {
					 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UsuarioGrupo.class);
					 detachedCriteria.add(Restrictions.eq("id.grupo.codigo", codigoGrupo));
					 detachedCriteria.setProjection(Projections.property("id.usuario"));
					 
					 subqueries.add(Subqueries.propertyIn("codigo", detachedCriteria));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
			}
		}
	}

	private boolean isGrupoPresente(UsuarioFilter filter) {
		return (filter.getGrupos() != null && !filter.getGrupos().isEmpty());
	}

	private Long total(UsuarioFilter filter) {
		Criteria criteria = getSession(Usuario.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
