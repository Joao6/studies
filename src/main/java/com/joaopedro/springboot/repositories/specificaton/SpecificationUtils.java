package com.joaopedro.springboot.repositories.specificaton;

import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specification.where;

public class SpecificationUtils {

	public static Specification addClausule(Specification where, Specification novaClausula) {
		if (where == null) {
			return where(novaClausula);
		} else {
			return where(where).and(novaClausula);
		}
	}
}
