package com.codigo.aplios.toolbox.core.domain.audit;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.toolbox.core.data.repository.audit.AuditOperation;

// @Entity
public class AuditEntry implements Serializable {

	private static final long serialVersionUID = 9876543210L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "AUDIT_USER")
	private String auditUser;

	@Column
	private Long eventId;
	private String tableName;

	@Enumerated(EnumType.STRING)
	private AuditOperation operation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_TIMESTAMP")
	private Calendar operationTime;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "AUDIT_ENTRY")
	private Collection<AuditField> fields;

	public Long getId() {

		return this.id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getAuditUser() {

		return this.auditUser;
	}

	public void setAuditUser(String auditUser) {

		this.auditUser = auditUser;
	}

	public Long getEventId() {

		return this.eventId;
	}

	public void setEventId(Long eventId) {

		this.eventId = eventId;
	}

	public String getTableName() {

		return this.tableName;
	}

	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	public AuditOperation getOperation() {

		return this.operation;
	}

	public void setOperation(AuditOperation operation) {

		this.operation = operation;
	}

	public Calendar getOperationTime() {

		return this.operationTime;
	}

	public void setOperationTime(Calendar operationTime) {

		this.operationTime = operationTime;
	}

	public Collection<AuditField> getFields() {

		return this.fields;
	}

	public void setFields(Collection<AuditField> fields) {

		this.fields = fields;
	}

	/**
	 * Set hashCode to audit entry's ID
	 */
	@Override
	public int hashCode() {

		return this.id.intValue();
	}

	/**
	 * Assign equivalence based on audit entry's ID
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof AuditEntry)
			if (((AuditEntry) obj).getId()
				.equals(this.id))
				return true;
		return false;
	}
}
