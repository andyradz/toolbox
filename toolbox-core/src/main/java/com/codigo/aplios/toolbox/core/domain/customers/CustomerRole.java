package com.codigo.aplios.toolbox.core.domain.customers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Table(name = "BLC_CUSTOMER_ROLE")
public class CustomerRole extends Identity {

	// private Set<PermissionRecord>

	private static final long serialVersionUID = 1801574957344616084L;

	@Column(name = "name")
	private String name;

	private boolean FreeShipping;

	private boolean taxExempt;

	private Integer TaxDisplayType;

	private boolean active;

	private boolean isSystemRole;

	private String systemName;

}
