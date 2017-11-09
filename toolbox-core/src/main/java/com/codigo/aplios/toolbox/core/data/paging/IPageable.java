/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.data.paging;

import java.util.ListIterator;

import com.codigo.aplios.toolbox.core.metric.Property;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IPageable extends ListIterator // extends Iterator{
{

	/**
	 *
	 * The 0-based current page index
	 */
	Property pageIndex();

	/**
	 * The number of items in each page.
	 */
	Property pageSize();

	/**
	 * The total number of items.
	 */
	Property totalCount();

	/**
	 * The 1-based current page index
	 */
	Property pageNumber();

	/**
	 * The total number of pages.
	 */
	Property totalPages();

	/**
	 * Whether there are pages before the current page.
	 */
	Property hasPreviousPage();

	/**
	 * Whether there are pages after the current page.
	 */
	Property hasNextPage();

	/**
	 * The 1-based index of the first item in the page.
	 */
	Property firstItemIndex();

	/**
	 * The 1-based index of the last item in the page.
	 */
	Property lastItemIndex();

	/**
	 * Whether the page is the first page
	 */
	Property isFirstPage();

	/**
	 * Whether the page is the last page
	 */
	Property isLastPage();

}

//
// }
// public abstract class PagedListBase : IPageable
// {
// protected PagedListBase()
// {
// this.PageIndex = 0;
// this.PageSize = 0;
// this.TotalCount = 1;
// }
//
// protected PagedListBase(IPageable pageable)
// {
// this.Init(pageable);
// }
//
// protected PagedListBase(int pageIndex, int pageSize, int totalItemsCount)
// {
// Guard.PagingArgsValid(pageIndex, pageSize, "pageIndex", "pageSize");
//
// this.PageIndex = pageIndex;
// this.PageSize = pageSize;
// this.TotalCount = totalItemsCount;
// }
//
// // only here for compat reasons with nc
// public void LoadPagedList<T>(IPagedList<T> pagedList)
// {
// this.Init(pagedList);
// }
//
// public void Init(IPageable pageable)
// {
// Guard.NotNull(pageable, "pageable");
//
// this.PageIndex = pageable.PageIndex;
// this.PageSize = pageable.PageSize;
// this.TotalCount = pageable.TotalCount;
// }
//
// public int PageIndex
// {
// get;
// set;
// }
//
// public int PageSize
// {
// get;
// set;
// }
//
// public int TotalCount
// {
// get;
// set;
// }
//
// public int PageNumber
// {
// get
// {
// return this.PageIndex + 1;
// }
// set
// {
// this.PageIndex = value - 1;
// }
// }
//
// public int TotalPages
// {
// get
// {
// if (this.PageSize == 0)
// return 0;
//
// var total = this.TotalCount / this.PageSize;
//
// if (this.TotalCount % this.PageSize > 0)
// total++;
//
// return total;
// }
// }
//
// public bool HasPreviousPage
// {
// get
// {
// return this.PageIndex > 0;
// }
// }
//
// public bool HasNextPage
// {
// get
// {
// return (this.PageIndex < (this.TotalPages - 1));
// }
// }
//
// public int FirstItemIndex
// {
// get
// {
// return (this.PageIndex * this.PageSize) + 1;
// }
// }
//
// public int LastItemIndex
// {
// get
// {
// return Math.Min(this.TotalCount, ((this.PageIndex * this.PageSize) + this.PageSize));
// }
// }
//
// public bool IsFirstPage
// {
// get
// {
// return (this.PageIndex <= 0);
// }
// }
//
// public bool IsLastPage
// {
// get
// {
// return (this.PageIndex >= (this.TotalPages - 1));
// }
// }
//
// public virtual IEnumerator GetEnumerator()
// {
// return Enumerable.Empty<int>().GetEnumerator();
// }
//
// }
//
// public class PagedList : PagedListBase
// {
// public PagedList(int pageIndex, int pageSize, int totalItemsCount)
// : base(pageIndex, pageSize, totalItemsCount)
// {
// }
// }
//
// }
//
// public class PagedList<T> : IPagedList<T>, IReadOnlyList<T>, IReadOnlyCollection<T>
// {
// private IQueryable<T> _query;
// private bool _queryIsPagedAlready;
//
// private int _pageIndex;
// private int _pageSize;
// private int? _totalCount;
//
// private List<T> _list;
//
// public PagedList(IEnumerable<T> source, int pageIndex, int pageSize)
// {
// Guard.NotNull(source, "source");
//
// Init(source.AsQueryable(), pageIndex, pageSize, null);
// }
//
// public PagedList(IEnumerable<T> source, int pageIndex, int pageSize, int totalCount)
// {
// Guard.NotNull(source, "source");
//
// Init(source.AsQueryable(), pageIndex, pageSize, totalCount);
// }
//
// private void Init(IQueryable<T> source, int pageIndex, int pageSize, int? totalCount)
// {
// Guard.NotNull(source, nameof(source));
// Guard.PagingArgsValid(pageIndex, pageSize, "pageIndex", "pageSize");
//
// _query = source;
// _queryIsPagedAlready = totalCount.HasValue;
//
// _pageIndex = pageIndex;
// _pageSize = pageSize;
// _totalCount = totalCount;
// }
//
// private void EnsureIsLoaded()
// {
// if (_list == null)
// {
// if (_totalCount == null)
// {
// _totalCount = _query.Count();
// }
//
// if (_queryIsPagedAlready)
// {
// _list = _query.ToList();
// }
// else
// {
// _list = ApplyPaging(_query).ToList();
// }
// }
// }
//
// #region IPageable Members
//
// public IQueryable<T> SourceQuery
// {
// get { return _query; }
// }
//
// public IQueryable<T> ApplyPaging(IQueryable<T> query)
// {
// if (_pageIndex == 0 && _pageSize == int.MaxValue)
// {
// // Paging unnecessary
// return query;
// }
// else
// {
// var skip = _pageIndex * _pageSize;
// if (query.Provider is IDbAsyncQueryProvider)
// {
// return query.Skip(() => skip).Take(() => _pageSize);
// }
// else
// {
// return query.Skip(skip).Take(_pageSize);
// }
// }
// }
//
// public IPagedList<T> Load(bool force = false)
// {
// if (force && _list != null)
// {
// _list.Clear();
// _list = null;
// }
//
// EnsureIsLoaded();
//
// return this;
// }
//
// public int PageIndex
// {
// get { return _pageIndex; }
// set { _pageIndex = value; }
// }
//
// public int PageSize
// {
// get { return _pageSize; }
// set { _pageSize = value; }
// }
//
// public int TotalCount
// {
// get
// {
// if (!_totalCount.HasValue)
// {
// _totalCount = _query.Count();
// }
//
// return _totalCount.Value;
// }
// set
// {
// _totalCount = value;
// }
// }
//
// public int PageNumber
// {
// get
// {
// return this.PageIndex + 1;
// }
// set
// {
// this.PageIndex = value - 1;
// }
// }
//
// public int TotalPages
// {
// get
// {
// var total = this.TotalCount / this.PageSize;
//
// if (this.TotalCount % this.PageSize > 0)
// total++;
//
// return total;
// }
// }
//
// public bool HasPreviousPage
// {
// get
// {
// return this.PageIndex > 0;
// }
// }
//
// public bool HasNextPage
// {
// get
// {
// return (this.PageIndex < (this.TotalPages - 1));
// }
// }
//
// public int FirstItemIndex
// {
// get
// {
// return (this.PageIndex * this.PageSize) + 1;
// }
// }
//
// public int LastItemIndex
// {
// get
// {
// return Math.Min(this.TotalCount, ((this.PageIndex * this.PageSize) + this.PageSize));
// }
// }
//
// public bool IsFirstPage
// {
// get
// {
// return (this.PageIndex <= 0);
// }
// }
//
// public bool IsLastPage
// {
// get
// {
// return (this.PageIndex >= (this.TotalPages - 1));
// }
// }
//
// #endregion
//
// #region IList<T> Members
//
// public void Add(T item)
// {
// EnsureIsLoaded();
// _list.Add(item);
// }
//
// public void Clear()
// {
// if (_list != null)
// {
// _list.Clear();
// _list = null;
// }
// }
//
// public bool Contains(T item)
// {
// EnsureIsLoaded();
// return _list.Contains(item);
// }
//
// public void CopyTo(T[] array, int arrayIndex)
// {
// EnsureIsLoaded();
// _list.CopyTo(array, arrayIndex);
// }
//
// public bool Remove(T item)
// {
// if (_list != null)
// {
// return _list.Remove(item);
// }
//
// return false;
// }
//
// public int Count
// {
// get
// {
// EnsureIsLoaded();
// return _list.Count;
// }
// }
//
// public bool IsReadOnly
// {
// get { return false; }
// }
//
// public int IndexOf(T item)
// {
// EnsureIsLoaded();
// return _list.IndexOf(item);
// }
//
// public void Insert(int index, T item)
// {
// EnsureIsLoaded();
// _list.Insert(index, item);
// }
//
// public void RemoveAt(int index)
// {
// if (_list != null)
// {
// _list.RemoveAt(index);
// }
// }
//
// public T this[int index]
// {
// get
// {
// EnsureIsLoaded();
// return _list[index];
// }
// set
// {
// EnsureIsLoaded();
// _list[index] = value;
// }
// }
//
// IEnumerator IEnumerable.GetEnumerator()
// {
// return this.GetEnumerator();
// }
//
// public IEnumerator<T> GetEnumerator()
// {
// EnsureIsLoaded();
// return _list.GetEnumerator();
// }
//
// #endregion
//
// #region Utils
//
// public void AddRange(IEnumerable<T> collection)
// {
// EnsureIsLoaded();
// _list.AddRange(collection);
// }
//
// public ReadOnlyCollection<T> AsReadOnly()
// {
// EnsureIsLoaded();
// return _list.AsReadOnly();
// }
//
// #endregion
// }
// }
