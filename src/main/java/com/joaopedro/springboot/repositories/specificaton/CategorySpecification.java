package com.joaopedro.springboot.repositories.specificaton;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.joaopedro.springboot.domain.Category;

public final class CategorySpecification {

	public static Specification<Category> byName(String param) {
	    return (Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder
	            .like(builder.lower(root.<String>get("name")), "%" + param.trim().toLowerCase() + "%");
	  }
}
