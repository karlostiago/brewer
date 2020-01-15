package com.algaworks.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.brewer.controller.CervejasController;

@EnableWebMvc
@Configuration
@ComponentScan( basePackageClasses = { CervejasController.class } )
public class WebConfig implements WebMvcConfigurer {
	
	
}
