package com.algaworks.brewer.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.brewer.model.Usuario;

public class UserSystem extends User {

	private static final long serialVersionUID = -4312754800203950978L;

	private Usuario usuario;

	public UserSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
