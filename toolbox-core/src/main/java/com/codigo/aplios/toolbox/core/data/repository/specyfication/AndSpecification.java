package com.codigo.aplios.toolbox.core.data.repository.specyfication;

// public abstract class AndSpecification<T> extends AbstractSpecification<T> {
//
// private Specification<T> first;
// private Specification<T> second;
//
// public AndSpecification(Specification<T> first, Specification<T> second) {
// this.first = first;
// this.second = second;
// }
//
// @Override
// public boolean isSatisfiedBy(T t) {
//
// return first.isSatisfiedBy(t) && second.isSatisfiedBy(t);
// }
//
// @Override
// public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
//
// return cb.and(first.toPredicate(root, cb), second.toPredicate(root, cb));
// }
//
// @Override
// public Class<T> getType() {
//
// return first.getType();
// }
// }