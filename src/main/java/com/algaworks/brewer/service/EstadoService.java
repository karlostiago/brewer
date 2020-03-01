package com.algaworks.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.Estados;

@Service
public class EstadoService extends AbstractService<Estado, Long> {
	
	@Autowired
	private Estados estados;
	
	@Override
	public JpaRepository<Estado, Long> repositorioPrincipal() {
		return estados;
	}
	
	public List<Estado> todos() {
		return estados.findAll();
	}
}
