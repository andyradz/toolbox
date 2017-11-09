package com.codigo.aplios.toolbox.core.service;

class ServiceLocatorPatternDemo {
	public static void main(String[] args) {

		Service service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
		service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
	}
}
