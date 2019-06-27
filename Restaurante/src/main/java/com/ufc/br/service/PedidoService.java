package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Pedido;
import com.ufc.br.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void cadastrar(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> retornarPedidos(Long cliente_id){
		return pedidoRepository.findByCliente_id(cliente_id);
	}
	
}
