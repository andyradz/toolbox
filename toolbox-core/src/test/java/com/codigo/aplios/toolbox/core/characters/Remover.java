package com.codigo.aplios.toolbox.core.characters;

public class Remover {

	public static void main(String[] args) {

		String reference1 = new String("something");
		String reference2 = reference1;
		System.out.println("reference1 == reference2: " + (reference1 == reference2));

		new Test1();
	}
}

abstract class Test0 {
	protected StringBuilder sb;

	public Test0() {

		this.method();
		this.sb = new StringBuilder();
	}

	public abstract void method();

}

class Test1 extends Test0 {
	public Test1() {

	}

	public Test1(String name) {

		this();
	}

	@Override
	public void method() {

		this.sb.setLength(0);
	}
}
