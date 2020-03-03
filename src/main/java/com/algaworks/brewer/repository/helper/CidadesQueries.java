package com.algaworks.brewer.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.filter.CidadeFilter;

public interface CidadesQueries {
	
	public boolean isExist(Cidade cidade, Estado estado);
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);
}
