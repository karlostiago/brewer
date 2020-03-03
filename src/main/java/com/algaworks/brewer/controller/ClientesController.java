package com.algaworks.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.enums.TipoPessoa;
import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.filter.ClienteFilter;
import com.algaworks.brewer.service.ClienteService;
import com.algaworks.brewer.service.EstadoService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastro-cliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estadoService.todos());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(cliente);
		}
		
		try {
			clienteService.save(cliente);
		}
		catch(RuntimeException e) {
			result.rejectValue("cpfCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso.");
		return new ModelAndView("redirect:/clientes/novo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cliente/pesquisa-cliente");
		
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clienteService.filtrar(clienteFilter, pageable), request);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
}
