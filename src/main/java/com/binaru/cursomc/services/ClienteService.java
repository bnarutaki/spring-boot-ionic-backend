package com.binaru.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binaru.cursomc.domain.Categoria;
import com.binaru.cursomc.domain.Cliente;
import com.binaru.cursomc.repositories.ClienteRepository;
import com.binaru.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	

	public Cliente find(Integer id) {
		Optional<Cliente> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrao! Id = " + id + ", Tipo:" 
				+ Cliente.class.getName()));
	}
}
