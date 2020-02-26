package com.algaworks.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.exceptions.BrewerRuntimeException;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.Estilos;

@Service
public class EstiloService extends AbstractService<Estilo, Long> {
	
	@Autowired
	private Estilos estilos;
	
	@Override
	public JpaRepository<Estilo, Long> repositorioPrincipal() {
		return estilos;
	}
	
	@Override
	public Estilo save(Estilo estilo) {
		
		Optional<Estilo> optional = estilos.findByNomeIgnoreCase(estilo.getNome());
		
		if(optional.isPresent()) {
			throw new BrewerRuntimeException("Estilo " + estilo.getNome() + ", já cadastrado.");
		}
		
		return super.saveAndFlush(estilo);
	}
	
	public List<Estilo> todos() {
		return estilos.findAll();
	}
}
