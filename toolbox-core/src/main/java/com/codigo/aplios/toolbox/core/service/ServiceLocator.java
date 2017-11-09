package com.codigo.aplios.toolbox.core.service;

public class ServiceLocator {
	private static Cache cache;

	static {
		ServiceLocator.cache = new Cache();
	}

	public static Service getService(String jndiName) {

		Service service = ServiceLocator.cache.getService(jndiName);

		if (service != null)
			return service;

		InitialContext context = new InitialContext();
		Service service1 = (Service) context.lookup(jndiName);
		ServiceLocator.cache.addService(service1);
		return service1;
	}
}
