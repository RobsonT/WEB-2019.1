package com.ufc.br.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Role;
import com.ufc.br.service.PessoaService;
@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping("/cadastro/usuario")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("CadastroUser");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}
	
	@RequestMapping("/cadastro/gerente")
	public ModelAndView formG() {
		ModelAndView mv = new ModelAndView("CadastroGerente");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView cadastrar(@Validated Pessoa pessoa, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastroUser");
		if (result.hasErrors()) {
			return mv;
		}
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setPapel("ROLE_USER");
		roles.add(role);
		pessoa.setRoles(roles);
		pessoaService.cadastrar(pessoa);
		mv.addObject("Mensagem", "Pessoa cadastrada com sucesso!!!");
		return mv;
	}
	
	@PostMapping("/salvarGerente")
	public ModelAndView cadastrargerente(@Validated Pessoa pessoa, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastroGerente");
		if (result.hasErrors()) {
			return mv;
		}
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setPapel("ROLE_ADIN");
		roles.add(role);
		pessoa.setRoles(roles);
		pessoaService.cadastrar(pessoa);
		mv.addObject("Mensagem", "Pessoa cadastrada com sucesso!!!");
		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView formLogin() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
		
	}
	
}
