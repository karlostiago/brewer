package com.algaworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;

@Service
public class CervejaService extends AbstractService<Cerveja, Long> {
	
	@Autowired
	private Cervejas cervejas;

	@Override
	public JpaRepository<Cerveja, Long> repositorioPrincipal() {
		return cervejas;
	}
}
