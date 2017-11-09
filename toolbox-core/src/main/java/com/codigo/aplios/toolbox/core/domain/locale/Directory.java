package com.codigo.aplios.toolbox.core.domain.locale;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Table(name = "DIC_BASE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Directory extends Identity {

	private static final long serialVersionUID = -1118611759382185681L;

	@Column(name = "PUBLISHED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date published;

	@Column(name = "DISPLAYORDER")
	private long displayOrder;

	@Column(name = "PUBLISHED")
	private boolean isPublished;

	public Date getPublishedDate() {

		return this.published;
	}

	public void setPublishedDate(Date published) {

		this.published = published;
	}

	public long getDisplayOrder() {

		return this.displayOrder;
	}

	public void setDisplayOrder(long displayOrder) {

		this.displayOrder = displayOrder;
	}

	public boolean isPublished() {

		return this.isPublished;
	}

	public void setPublished(boolean isPublished) {

		this.isPublished = isPublished;
	}
}
