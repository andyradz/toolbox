package com.codigo.aplios.toolbox.core.data.repository.audit;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.toolbox.core.domain.audit.AuditListener;

@MappedSuperclass
@EntityListeners({ AuditListener.class })
public abstract class AuditableEntity implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3077961887990985379L;

	public static ThreadLocal currentUser = new ThreadLocal();

	@Column(name = "AUDIT_USER")
	protected String auditUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_TIMESTAMP")
	protected Calendar auditTimestamp;

	public String getAuditUser() {

		return this.auditUser;
	}

	public void setAuditUser(String auditUser) {

		this.auditUser = auditUser;
	}

	public Calendar getAuditTimestamp() {

		return this.auditTimestamp;
	}

	public void setAuditTimestamp(Calendar auditTimestamp) {

		this.auditTimestamp = auditTimestamp;
	}

	@PrePersist
	@PreUpdate
	public void updateAuditInfo() {

		this.setAuditUser((String) AuditableEntity.currentUser.get());
		this.setAuditTimestamp(Calendar.getInstance());
	}

	// -----------------------------------------------------------------------//
	// toString
	// -----------------------------------------------------------------------//

	@Override
	public String toString() {

		return "";
	}
}
