package com.algaworks.brewer.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable);
}
