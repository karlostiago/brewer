package com.algaworks.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.enums.StatusUsuario;
import com.algaworks.brewer.exceptions.BrewerRuntimeException;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Usuarios;
import com.algaworks.brewer.repository.filter.UsuarioFilter;

@Service
public class UsuarioService extends AbstractService<Usuario, Long> {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public JpaRepository<Usuario, Long> repositorioPrincipal() {
		return usuarios;
	}
	
	@Override
	public Usuario save(Usuario usuario) {
		
		Optional<Usuario> optional = usuarios.findByEmailIgnoreCase(usuario.getEmail());
		
		if(optional.isPresent()) {
			throw new BrewerRuntimeException("email", "E-mail já cadastrado.");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new BrewerRuntimeException("senha", "Senha é obrigatório para novo usuário.");
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoDeSenha(usuario.getSenha());
		}
		
		return super.save(usuario);
	}
	
	public List<Usuario> todos() {
		return usuarios.findAll();
	}

	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		return usuarios.filtrar(filter, pageable);
	}
	
	@Transactional
	public void updateStatus(Long[] codigos, StatusUsuario acao) {
		acao.executar(codigos, usuarios);
	}
}
