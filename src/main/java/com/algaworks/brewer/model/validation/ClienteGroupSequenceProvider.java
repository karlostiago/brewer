package com.algaworks.brewer.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.algaworks.brewer.model.Cliente;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Cliente.class);
		
		if(isTipoPessoaSelecionada(cliente)) {
			groups.add(cliente.getTipoPessoa().getGroup());
		}
		
		return groups;
	}
	
	private boolean isTipoPessoaSelecionada(Cliente cliente) {
		return cliente!= null && cliente.getTipoPessoa() != null;
	}
}
