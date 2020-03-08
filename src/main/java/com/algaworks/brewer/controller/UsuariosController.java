package com.algaworks.brewer.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.exceptions.BrewerRuntimeException;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.service.GrupoService;
import com.algaworks.brewer.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController implements Serializable {
	
	private static final long serialVersionUID = -4835538468751111745L;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/cadastro-usuario");
		mv.addObject("grupos", grupoService.todos());
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			usuarioService.save(usuario);
		}
		catch(BrewerRuntimeException e) {
			result.rejectValue(e.getComponent(), e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso.");
		return new ModelAndView("redirect:/usuarios/novo");
	}

	public ModelAndView pesquisar() {
		// TODO Auto-generated method stub
		return new ModelAndView("redirect:/usuarios/novo");
	}
}
