package com.codigo.aplios.toolbox.core.service;

class Service1 implements Service {
	@Override
	public void execute() {

		System.out.println("Executing Service1");
	}

	@Override
	public String getName() {

		return "Service1";
	}
}
