package com.codigo.aplios.toolbox.core.domain.catalog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
// @Customizer(HistoryCustomizer.class)
@Table(name = "Products", schema = "PUBLIC")
public class Product extends Identity {

	private static final long serialVersionUID = -1399154554678035069L;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "price", nullable = false)
	private Double price;

	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate", nullable = false)
	private Date createdDate;

	public Product() {

		super();
	}

	public String getName() {

		return this.name;
	}

	public Double getPrice() {

		return this.price;
	}

	public void setPrice(Double price) {

		this.price = price;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Date getCreatedDate() {

		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	@Override
	public String toString() {

		return '[' + this.getId()
			.toString() + ']' + '@' + this.getName();

	}
}
