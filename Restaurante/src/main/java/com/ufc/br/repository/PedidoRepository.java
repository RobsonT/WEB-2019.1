package com.ufc.br.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	public List<Pedido> findByCliente_id(Long cliente_id);
}
