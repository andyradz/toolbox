package com.codigo.aplios.toolbox.core.domain.orders;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6696537676309191692L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private VersionHistory versioningHistory = new VersionHistory();

	@OneToOne
	@JoinColumn(name = "ref_current_version")
	private OrderVersion currentVersion;
}