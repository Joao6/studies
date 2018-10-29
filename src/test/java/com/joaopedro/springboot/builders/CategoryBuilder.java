package com.joaopedro.springboot.builders;

import com.joaopedro.springboot.domain.Category;

public class CategoryBuilder {

	private Category category;
	
	private CategoryBuilder() {}
	
	public static CategoryBuilder oneCategory() {
		CategoryBuilder builder = new CategoryBuilder();
		builder.category = new Category();
		builder.category.setName("Eletrônicos");
		return builder;
	}
	
	public CategoryBuilder withId() {
		category.setId(1);
		return this;
	}
	
	public CategoryBuilder withName(String name) {
		category.setName(name);
		return this;
	}
	
	public Category now() {
		return category;
	}
}
