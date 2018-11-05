package com.binaru.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binaru.cursomc.domain.Categoria;
import com.binaru.cursomc.repositories.CategoriaRepository;
import com.binaru.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repo;
	

	public Categoria buscar(Integer id) {
		Optional<Categoria> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrao! Id = " + id + ", Tipo:" 
				+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //forca entendimento para o save enteder que é um insert
		return repo.save(obj);
	}
}
