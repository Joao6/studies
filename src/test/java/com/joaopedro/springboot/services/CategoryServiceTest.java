package com.joaopedro.springboot.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.joaopedro.springboot.builders.CategoryBuilder;
import com.joaopedro.springboot.domain.Category;
import com.joaopedro.springboot.repositories.CategoryRepository;

public class CategoryServiceTest {

	@InjectMocks
	private CategoryService service;

	@Mock
	private CategoryRepository repo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCreateOneCategory() {
		//Scene
		Category category = CategoryBuilder.oneCategory().now();
		Mockito.when(
				repo.save(Mockito.any(Category.class)))
		.thenReturn(CategoryBuilder.oneCategory().withId().now());

		//Action
		category = service.create(category);

		//Assert
		Assert.assertTrue(category.getId() != null);
	}

	@Test
	public void shouldUpdateOneCategory() {
		//Scene
		String name = "Eletrodomesticos";
		Category category = CategoryBuilder.oneCategory().withName(name).now();
		Mockito.when(
				repo.save(Mockito.any(Category.class)))
		.thenReturn(CategoryBuilder.oneCategory().withName(name).withId().now());

		//Action
		category = service.create(category);

		//Assert
		Assert.assertTrue(category.getName() == name);
	}

	@Test
	public void shouldDeleteOneCategory() {
		//Scene
		Category category = CategoryBuilder.oneCategory().now();
		Mockito.when(
				repo.save(Mockito.any(Category.class)))
		.thenReturn(CategoryBuilder.oneCategory().withId().now());

		//Action create
		category = service.create(category);

		//Action delete
		service.delete(category.getId());
		
		//Action readById
		Category categoryDeleted = service.readById(category.getId());
		
		//Assert
		Assert.assertTrue(categoryDeleted == null);
	}

}
