package com.codigo.aplios.toolbox.core.domain.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BLC_CATEGORY")
// , uniqueConstraints = {
// @UniqueConstraint(columnNames =
// {
// "",
// "" }) })
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORy_ID")
	protected Long id;

	public Long getId() {

		return this.id;
	}

	public void setId(Long id) {

		this.id = id;
	}
}
