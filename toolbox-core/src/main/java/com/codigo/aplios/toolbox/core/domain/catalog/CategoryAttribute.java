package com.codigo.aplios.toolbox.core.domain.catalog;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Table(name = "BLC_CATEGORY_ATTRIBUTE")
// usage=CacheConcurrenceStarategy.
public class CategoryAttribute extends Identity {

	// @Id
	// @GeneratedValue
	// @Column(name = "CATEGORY_ATTRINUTE_ID")
	// protected Long id;

	private static final long serialVersionUID = 7651592702745185585L;
	@Column(name = "NAME")
	protected String name;
	@Column(name = "VALUE")
	protected String value;

	@Column(name = "SEARCHABLE")
	protected Boolean searchable = false;

	@ManyToOne(targetEntity = Category.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CATEGORY_ID")
	protected Category category;

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getValue() {

		return this.value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	public Boolean getSearchable() {

		return this.searchable == null ? Boolean.FALSE
				: this.searchable;
	}

	public void setSearchable(Boolean searchable) {

		this.searchable = searchable;
	}

	public Category getCategory() {

		return this.category;
	}

	public void setCategory(Category category) {

		this.category = category;
	}

	@Override
	public boolean equals(Object instance) {

		if (Objects.isNull(instance))
			return false;
		if (this == instance)
			return true;
		CategoryAttribute cattAttr = CategoryAttribute.class.cast(instance);

		// if (this.id != null && cattAttr.id != null)
		// return this.id.equals(cattAttr.id);
		if (this.name != null)
			if (cattAttr != null) {
				if (cattAttr.category != null)
					return false;
			} else if (!this.category.equals(cattAttr.category))
				return false;
		if (this.value == null) {
			if (cattAttr.value != null)
				return false;
		} else if (!this.value.equals(cattAttr.value))
			return false;
		return true;
	};

	@Override
	public String toString() {

		return this.name;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.name, this.category, this.value);
	}
}
