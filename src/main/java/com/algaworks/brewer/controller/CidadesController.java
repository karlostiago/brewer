package com.algaworks.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.service.CidadeService;
import com.algaworks.brewer.service.EstadoService;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/cadastro-cidade");
		mv.addObject("estados", estadoService.todos());
		
		return mv;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView cadastrar(@Valid Cidade cidade, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return nova(cidade);
		}
		
		try {
			cidadeService.save(cidade);
		}
		catch(RuntimeException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso.");
		return new ModelAndView("redirect:/cidades/nova");
	}
	
	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> porCodigoEstado(@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		List<Cidade> cidades = cidadeService.porCodigoEstado(codigoEstado); 
		return cidades;
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cidade/pesquisa-cidade");
		mv.addObject("estados", estadoService.todos());
		
		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidadeService.filtrar(cidadeFilter, pageable), request);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
}
