package com.joaopedro.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.joaopedro.springboot.domain.Category;
import com.joaopedro.springboot.repositories.CategoryRepository;
import com.joaopedro.springboot.repositories.specificaton.CategorySpecification;
import com.joaopedro.springboot.repositories.specificaton.SpecificationUtils;

@Service 
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category readById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null);		
	}	
	
	public Page<Category> readByCriteria(String name, Integer page, Integer linesPerPage, String orderBy, String direction) {		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Specification where = applyCriteria(name);		
		return repo.findAll(where, pageRequest);
	}
	
	public Specification applyCriteria(String name) {
	    Specification spec = null;
	    if (name != null && !name.isEmpty()) {
	      spec = SpecificationUtils.addClausule(spec, CategorySpecification.byName(name));
	    }
	    return spec;
	  }
	
	public Category create(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
