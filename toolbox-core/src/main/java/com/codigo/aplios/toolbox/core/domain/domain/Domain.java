package com.codigo.aplios.toolbox.core.domain.domain;

import java.io.Serializable;

import javax.persistence.Version;

public abstract class Domain<PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Version
	// @Column(name = "VERSION", nullable = false)
	private Long version;

	public Domain(PK id, Long version) {

		this.version = version;
	}

	public Domain() {

	}

	public abstract PK getId();

	public abstract void setId(PK id);

	public Long getVersion() {

		return this.version;
	}

	public void setVersion(Long version) {

		this.version = version;
	}

	@Override
	public String toString() {

		return "Bean [id=" + this.getId() + ", version=" + this.version + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.getId() == null) ? 0
				: this.getId()
					.hashCode());
		result = (prime * result) + ((this.version == null) ? 0
				: this.version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Domain<PK> other = Domain.class.cast(obj);
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId()
			.equals(other.getId()))
			return false;
		if (this.version == null) {
			if (other.version != null)
				return false;
		} else if (!this.version.equals(other.version))
			return false;
		return true;
	}
}
