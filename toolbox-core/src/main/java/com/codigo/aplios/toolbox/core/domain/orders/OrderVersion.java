package com.codigo.aplios.toolbox.core.domain.orders;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders_versions")
public class OrderVersion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 791951792884361183L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "custom_id")
	private String customId;
	private String name;
	private Double price;
	@Temporal(TemporalType.DATE)
	private Date date;
}