package com.codigo.aplios.toolbox.core.domain.audit;

public interface Auditable {

	AuditSection getAuditSection();

	void setAuditSection(AuditSection audit);
}
