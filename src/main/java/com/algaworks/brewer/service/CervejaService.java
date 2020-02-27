package com.algaworks.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;
import com.algaworks.brewer.repository.filter.CervejaFilter;
import com.algaworks.brewer.service.event.CervejaSalvaEvent;

@Service
public class CervejaService extends AbstractService<Cerveja, Long> {
	
	@Autowired
	private Cervejas cervejas;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public JpaRepository<Cerveja, Long> repositorioPrincipal() {
		return cervejas;
	}
	
	@Override
	public Cerveja save(Cerveja cerveja) {
		Cerveja cervejaSalva = super.save(cerveja);
		publisher.publishEvent(new CervejaSalvaEvent(cervejaSalva));
		
		return cervejaSalva;
	}
	
	public List<Cerveja> todas() {
		return cervejas.findAll();
	}
	
	public List<Cerveja> filtrar(CervejaFilter filter) {
		return cervejas.filtrar(filter);
	}
}
