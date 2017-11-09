package com.codigo.aplios.toolbox.core.domain.orders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Embeddable
public class VersionHistory implements Serializable {

	@OneToMany(cascade = CascadeType.ALL)
	@MapKey(name = "date")

	@JoinColumn(name = "ref_order_history")
	private Map<Date, OrderVersion> orderVersions = new HashMap<>();

	@OneToMany
	@JoinColumn(name = "ref_order_hour_milestone")
	private List<OrderVersion> orderHourMilestones = new ArrayList<>();
}