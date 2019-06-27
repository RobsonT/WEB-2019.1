package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.imageUtils;

@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepository;
	
	public void cadastrar(Prato prato, MultipartFile imagem) {
		String[] values = imagem.getContentType().split("/");
		String caminho = "images/" + prato.getNome() + "." + values[values.length - 1];
		prato.setImagem(caminho);
		imageUtils.salvarImagem(caminho, imagem);
		pratoRepository.save(prato);
	}
	
	public List<Prato> retornarPratos(){
		return pratoRepository.findAll();
	}

	public void excluirPrato(Long id) {
		Prato p = buscarPorId(id);
		imageUtils.removerImagem(p.getImagem());
		pratoRepository.deleteById(id);
		
	}

	public Prato buscarPorId(Long codigo) {
		return pratoRepository.getOne(codigo);
	}
	
}
