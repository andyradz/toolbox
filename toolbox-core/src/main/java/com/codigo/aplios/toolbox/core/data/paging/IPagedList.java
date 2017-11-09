/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.data.paging;

import javax.persistence.criteria.CriteriaQuery;

import com.codigo.aplios.toolbox.core.data.repository.query.IQueryable;

/**
 *
 * @author andrzej.radziszewski
 */
/**
 * Paged list interface
 *
 * @author andrzej.radziszewski
 * @param <T>
 */
public interface IPagedList<T> extends IPageable {

	/**
	 * Return the original query without any paging applied
	 *
	 * @return
	 */
	CriteriaQuery SourceQuery();

	/// <param name="query">The query</param>
	/// <returns>A query with applied paging args</returns>
	/**
	 * Applies the initial paging arguments to the passed query
	 *
	 */
	IQueryable<T> ApplyPaging(IQueryable<T> query);

	IPagedList<T> Load(boolean force);
}
