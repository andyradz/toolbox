package com.codigo.aplios.toolbox.core.service;

// wzorce http://www.tutorialspoint.com/design_pattern/proxy_pattern.htm

import java.util.ArrayList;
import java.util.List;

public class Cache {

	private List<Service> services;

	public Cache() {

		this.services = new ArrayList<>();
	}

	public Service getService(String serviceName) {

		for (Service service : this.services)
			if (service.getName()
				.equalsIgnoreCase(serviceName)) {
				System.out.println("Returning cached  " + serviceName + " object");
				return service;
			}
		return null;
	}

	public void addService(Service newService) {

		boolean exists = false;

		for (Service service : this.services)
			if (service.getName()
				.equalsIgnoreCase(newService.getName()))
				exists = true;
		if (!exists)
			this.services.add(newService);
	}
}
