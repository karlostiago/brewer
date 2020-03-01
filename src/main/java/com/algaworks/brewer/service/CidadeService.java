package com.algaworks.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;

@Service
public class CidadeService extends AbstractService<Cidade, Long> {
	
	@Autowired
	private Cidades cidades;
	
	@Override
	public JpaRepository<Cidade, Long> repositorioPrincipal() {
		return cidades;
	}
	
	public List<Cidade> porCodigoEstado(Long codigoEstado) {
		return cidades.findByEstadoCodigo(codigoEstado);
	}

}
