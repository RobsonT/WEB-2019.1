package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Pedido;
import com.ufc.br.model.Pessoa;
import com.ufc.br.service.PedidoService;
import com.ufc.br.service.PessoaService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PessoaService pessoaService;

	@RequestMapping("/meusPedidos")
	public ModelAndView listarPessoas() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Pessoa pessoa = pessoaService.buscarPorLogin(user.getUsername());
		List<Pedido> pedidos = pedidoService.retornarPedidos(pessoa.getId());
		ModelAndView mv = new ModelAndView("ListarPedidos");
		mv.addObject("listaDePedidos", pedidos);
		return mv;
	}
	
}
