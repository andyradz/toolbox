package com.codigo.aplios.toolbox.core.domain.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AuditSection implements Serializable {

	private static final long serialVersionUID = -1934446958975060889L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED")
	private Date dateModified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_DELETED")
	private Date dateDelated;

	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;

	@Column(name = "MODIFIED_BY", nullable = true)
	private String modifiedBy;

	@Column(name = "DELETED_BY", nullable = true)
	private String deletedBy;

	@Column(name = "RECORD_STATE", nullable = false)
	private String recordState;

	public AuditSection() {

	}

	public Date getDateDelated() {

		return this.dateDelated;
	}

	public void setDateDelated(Date dateDelated) {

		this.dateDelated = dateDelated;
	}

	public Date getDateCreated() {

		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {

		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {

		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {

		this.dateModified = dateModified;
	}

	public String getModifiedBy() {

		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {

		this.modifiedBy = modifiedBy;
	}

	public String getCreatedBy() {

		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	public String getDeletedBy() {

		return this.deletedBy;
	}

	public void setDeletedBy(String deletedBy) {

		this.deletedBy = deletedBy;
	}
}
