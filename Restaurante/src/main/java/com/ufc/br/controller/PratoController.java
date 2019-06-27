package com.ufc.br.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Item;
import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/prato")
public class PratoController {

	@Autowired
	private PratoService pratoService;
	
	private List<Item> itens = new ArrayList<Item>();
	
	@RequestMapping("/formulario")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("CadastroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(@Validated Prato prato, BindingResult result,
			@RequestParam(value = "img") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("CadastroPrato");
		if (result.hasErrors()) {
			return mv;
		}
		pratoService.cadastrar(prato, imagem);
		mv.addObject("Mensagem", "Prato cadastrada com sucesso!!!");
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView listarPratos() {
		List<Prato> pratos = pratoService.retornarPratos();
		ModelAndView mv = new ModelAndView("ListarPrato");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}

	@RequestMapping("/excluir/{id}")
	public ModelAndView excluirPrato(@PathVariable Long id) {
		pratoService.excluirPrato(id);;
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");
		return mv;
	}

	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizarPrato(@PathVariable Long id) {
		Prato prato = pratoService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("CadastroPrato");
		mv.addObject("prato", prato);
		return mv;
	}
	
	
	
}
