package com.algaworks.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;
import com.algaworks.brewer.repository.filter.CidadeFilter;

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
	
	@Override
	public Cidade save(Cidade cidade) {
		
		boolean isExist = cidades.isExist(cidade, cidade.getEstado());
		if(isExist) {
			throw new RuntimeException("Já existe uma cidade cadastrada para o estado selecionado.");
		}
		
		return super.save(cidade);
	}
	
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable) {
		return cidades.filtrar(filter, pageable);
	}
}
