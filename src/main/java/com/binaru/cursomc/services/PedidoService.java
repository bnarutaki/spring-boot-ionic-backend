package com.binaru.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binaru.cursomc.domain.Pedido;
import com.binaru.cursomc.repositories.PedidoRepository;
import com.binaru.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repo;
	

	public Pedido find(Integer id) {
		Optional<Pedido> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrao! Id = " + id + ", Tipo:" 
				+ Pedido.class.getName()));
	}
}
