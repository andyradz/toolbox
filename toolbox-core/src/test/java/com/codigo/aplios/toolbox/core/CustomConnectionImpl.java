package com.codigo.aplios.toolbox.core;

public class CustomConnectionImpl implements AutoCloseable {

	@Override
	public void close() {

		System.out.println("Closing new connection()");

	}

	public void open() throws MyException1 {

		// throw new MyException1();
		System.out.println("Opening new connection()");

	}

	public static void main(String[] args) {

		byte ubyte = (byte) (200);
		int uint = ubyte & 0xff;
		// narrowing or widing
		byte op1 = 127, op2 = 12;

		if ((byte) (op1 + op2) > Byte.MAX_VALUE)
			System.out.println("TAK");

		try (CustomConnectionImpl autoClose = new CustomConnectionImpl()) {
			autoClose.open();
		} catch (MyException1 e) {
			e.printStackTrace();
		}
	}
}

final class MyException1 extends Exception {

	public MyException1() {

		super();
	}

	public MyException1(String message) {

		super(message);
	}
}