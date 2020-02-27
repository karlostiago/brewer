package com.algaworks.brewer.service.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.brewer.service.event.CervejaSalvaEvent;
import com.algaworks.brewer.storage.local.FotoStorage;

@Component
public class CervejaSalvaListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#event.temFoto()")
	public void cervejaSalva(CervejaSalvaEvent event) {
		fotoStorage.salvar(event.getCerveja().getFoto());
	}
}
