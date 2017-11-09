package com.codigo.aplios.toolbox.core.domain.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

// @Entity
// @AuditableEntity
public class AuditField implements Serializable {

	private static final long serialVersionUID = 1234567890L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String fieldName;

	@Lob
	private String fieldValue;

	@ManyToOne
	@JoinColumn(name = "AUDIT_ENTRY_ID")
	private AuditEntry auditEntry;

	public Long getId() {

		return this.id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getFieldName() {

		return this.fieldName;
	}

	public void setFieldName(String fieldName) {

		this.fieldName = fieldName;
	}

	public String getFieldValue() {

		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {

		this.fieldValue = fieldValue;
	}

	public AuditEntry getAuditEntry() {

		return this.auditEntry;
	}

	public void setAuditEntry(AuditEntry auditEntry) {

		this.auditEntry = auditEntry;
	}
}