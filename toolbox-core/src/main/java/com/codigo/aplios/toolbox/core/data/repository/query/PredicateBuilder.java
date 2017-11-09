package com.codigo.aplios.toolbox.core.data.repository.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateBuilder<T> {

	Predicate build(CriteriaBuilder criteriaBuilder, Root<T> root);
}