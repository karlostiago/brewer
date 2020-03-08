package com.algaworks.brewer.repository.helper.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.repository.helper.UsuariosQueries;

public class UsuariosImpl extends AbstractHelperImpl<Usuario> implements UsuariosQueries {

	@Override
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
