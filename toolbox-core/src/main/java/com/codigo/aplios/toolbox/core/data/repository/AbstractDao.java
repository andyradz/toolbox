package com.codigo.aplios.toolbox.core.data.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.codigo.aplios.toolbox.core.data.repository.query.IQueryable;
import com.codigo.aplios.toolbox.core.data.repository.query.PredicateBuilder;

/**
 * Implements support for lambda queries. The most important class of the project.
 *
 * @param <T> Entity class
 * @author janhalasa
 */
public abstract class AbstractDao<T> {

	private final Logger logger = Logger.getLogger(this.getClass()
		.getName());

	private final EntityManager em;
	private final Class<T> entityClass;

	protected AbstractDao(Class<T> entityClass, EntityManager em) {

		this.em = em;
		this.entityClass = entityClass;
	}

	public Logger logger() {

		return this.logger;
	}

	public void persist(T entity) {

		this.logger()
			.log(Level.INFO, "Creating entity {0}", entity);
		this.em.persist(entity);
	}

	public T merge(T entity) {

		this.logger()
			.log(Level.INFO, "Updating entity {0}", entity);
		return this.em.merge(entity);
	}

	public void remove(T entity) {

		this.logger()
			.log(Level.INFO, "Deleting entity {0}", entity);
		this.em.remove(entity);
	}

	public T getById(Long id) {

		this.logger()
			.log(Level.INFO, "Getting entity {0} with ID {1}", new Object[]
			{ this.entityClass.getSimpleName(), id });
		return this.em.find(this.entityClass, id);
	}

	public List<T> findAll() {

		return this.findWhere();
	}

	/**
	 * Creates a TypedQuery which can be further customized by calling its methods such as setMaxResults() or
	 * setFirstResult. To get results, call its getResultList() or getSingleResult() method. Method is private, so it
	 * cannot be overridden - it's used by other methods.
	 */
	private TypedQuery<T> createTypedQuery(IQueryable<T> queryBuilder) {

		CriteriaBuilder cb = this.em()
			.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(this.entityClass);
		Root<T> root = q.from(this.entityClass);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		CriteriaQuery<T> criteriaQuery = q.select(root);
		criteriaQuery = queryBuilder.build(cb, root, criteriaQuery);
		TypedQuery<T> typedQuery = this.em.createQuery(criteriaQuery);
		return typedQuery;
	}

	protected TypedQuery<T> createQuery(IQueryable<T> queryBuilder) {

		return this.createTypedQuery(queryBuilder);
	}

	public List<T> find(IQueryable<T> queryBuilder) {

		return this.createQuery(queryBuilder)
			.getResultList();
	}

	/**
	 * @param predicateBuilders Restricting query conditions. If you supply more than one predicate, they will be joined
	 *            by conjunction.
	 */
	protected List<T> findWhere(PredicateBuilder<T>... predicateBuilders) {

		return this
			.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
			.getResultList();
	}

	/**
	 * @param predicateBuilders Restricting query conditions. If you supply more than one predicate, they will be joined
	 *            by conjunction.
	 */
	protected T getWhere(PredicateBuilder<T>... predicateBuilders) {

		return this
			.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
			.getSingleResult();
	}

	/**
	 * @param predicateBuilders Restricting query conditions. If you supply more than one predicate, they will be joined
	 *            by conjunction.
	 */
	protected void deleteWhere(PredicateBuilder<T>... predicateBuilders) {

		CriteriaBuilder cb = this.em()
			.getCriteriaBuilder();
		CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityClass);
		Root<T> root = delete.from(this.entityClass);

		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			delete.where(this.buildPredicates(cb, root, predicateBuilders));
		this.em()
			.createQuery(delete)
			.executeUpdate();
	}

	private Predicate[] buildPredicates(CriteriaBuilder cb, Root<T> root, PredicateBuilder<T>... predicateBuilders) {

		List<Predicate> predicates = new LinkedList<>();
		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			for (PredicateBuilder<T> builder : predicateBuilders)
				predicates.add(builder.build(cb, root));
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	protected EntityManager em() {

		return this.em;
	}

}