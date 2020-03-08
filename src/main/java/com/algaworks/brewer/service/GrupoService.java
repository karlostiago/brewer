package com.algaworks.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Grupo;
import com.algaworks.brewer.repository.Grupos;

@Service
public class GrupoService extends AbstractService<Grupo, Long> {
	
	@Autowired
	private Grupos grupos;
	
	@Override
	public JpaRepository<Grupo, Long> repositorioPrincipal() {
		return grupos;
	}
	
	public List<Grupo> todos() {
		return grupos.findAll();
	}
}
