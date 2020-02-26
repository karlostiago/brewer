package com.algaworks.brewer.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public abstract class AbstractService<T, ID> {
	
	public abstract JpaRepository<T, ID> repositorioPrincipal();
	
	@Transactional
	public T save(T entity) {
		return repositorioPrincipal().save(entity);
	}
	
	@Transactional
	public T saveAndFlush(T entity) {
		return repositorioPrincipal().saveAndFlush(entity);
	}
}
