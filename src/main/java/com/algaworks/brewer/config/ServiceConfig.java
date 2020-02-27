package com.algaworks.brewer.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.algaworks.brewer.service.CervejaService;
import com.algaworks.brewer.storage.local.FotoStorage;
import com.algaworks.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CervejaService.class)
public class ServiceConfig {
	
	@Autowired
	private ServletContext context;
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal(context);
	}
}
