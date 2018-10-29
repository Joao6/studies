package com.joaopedro.springboot.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaopedro.springboot.domain.Category;
import com.joaopedro.springboot.services.CategoryService;
import com.joaopedro.springboot.threads.MyThread;

@RestController	
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Integer id) {	
		Category obj = service.readById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<Category>> readByCriteria(
			@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
	          @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
	          @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
	          @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Category> categories = service.readByCriteria(name, page, linesPerPage, orderBy, direction);
		MyThread t1 = new MyThread("Thread#1", 900);
		return ResponseEntity.ok().body(categories);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Category obj){
		obj = service.create(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@RequestBody Category obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
