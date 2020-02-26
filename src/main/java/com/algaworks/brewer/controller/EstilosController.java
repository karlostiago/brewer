package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.exceptions.BrewerRuntimeException;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.EstiloService;

@Controller
@RequestMapping("/estilos")
public class EstilosController {
	
	@Autowired
	private EstiloService estiloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilo/cadastro-estilo");
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(estilo);
		}
		
		try {			
			estiloService.save(estilo);
			attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso.");
			return new ModelAndView("redirect:/estilos/novo");
		}
		catch(BrewerRuntimeException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> cadastroRapido(@RequestBody @Valid Estilo estilo, BindingResult result) {

		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		try {
			estilo = estiloService.save(estilo);
		}
		catch(BrewerRuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(estilo);
	}
}