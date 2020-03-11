package com.algaworks.brewer.repository.helper.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.repository.helper.UsuariosQueries;

public class UsuariosImpl extends AbstractHelperImpl<Usuario> implements UsuariosQueries {

	@Override
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	@Transactional(readOnly = true)
	public List<String> permissoes(Usuario usuario) {
		return getManager().createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
					.setParameter("usuario", usuario)
					.getResultList();
	}

}
