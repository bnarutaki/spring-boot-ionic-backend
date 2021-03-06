package com.binaru.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.binaru.cursomc.domain.Categoria;
import com.binaru.cursomc.dto.CategoriaDTO;
import com.binaru.cursomc.repositories.CategoriaRepository;
import com.binaru.cursomc.services.exception.DataIntegrityException;
import com.binaru.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repo;
	

	public Categoria find(Integer id) {
		Optional<Categoria> ob = repo.findById(id);
		return ob.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrao! Id = " + id + ", Tipo:" 
				+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //forca entendimento para o save enteder que é um insert
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); // chama o find somente para confirmar se o Id existe.
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // chama o find somente para confirmar se o Id existe.
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possível excluir uma categoria associada a produtos");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
}
