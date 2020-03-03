package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.Clientes;
import com.algaworks.brewer.repository.filter.ClienteFilter;

@Service
public class ClienteService extends AbstractService<Cliente, Long> {
	
	@Autowired
	private Clientes clientes;
	
	@Override
	public JpaRepository<Cliente, Long> repositorioPrincipal() {
		return clientes;
	}
	
	@Override
	public Cliente save(Cliente cliente) {
		
		Optional<Cliente> clienteOptional = clientes.findByCpfCnpj(cliente.getCpfCnpjWithoutFormatter());
		
		if(clienteOptional.isPresent()) {
			throw new RuntimeException("Já existe um cliente cadastrado com o cpf ou cnpj, " + cliente.getCpfCnpj());
		}
		
		return super.save(cliente);
	}
	
	public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {
		return clientes.filtrar(filter, pageable);
	}
}
