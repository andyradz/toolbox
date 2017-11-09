package com.codigo.aplios.toolbox.core.domain.locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "DIC_STATEPROVINCE")
public class StateProvince extends Directory {

	private static final long serialVersionUID = 1769688424311131951L;

	@Column(name = "NAME", nullable = false)
	@Index(name = "STATE_NAME_INDEX", columnNames = { "NAME" })
	private String name;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "ID")
	private Country country;

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAbbreviation() {

		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {

		this.abbreviation = abbreviation;
	}

	public Country getCountry() {

		return this.country;
	}

	public void setCountry(Country country) {

		this.country = country;
	}

}
