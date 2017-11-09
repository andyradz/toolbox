package com.codigo.aplios.toolbox.core.proc;

import java.time.LocalTime;

public class Watki {
	public static void main(String[] args) {

		Bufor c = new Bufor();
		Producent p1 = new Producent(c, 1);
		Konsument c1 = new Konsument(c, 1);
		p1.start();
		c1.start();
	}
}

class Bufor {
	private int contents;
	private boolean available = false;

	public synchronized int get() {

		while (!this.available)
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		this.available = false;
		this.notifyAll();
		return this.contents;
	}

	public synchronized void put(int value) {

		while (this.available)
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		this.contents = value;
		this.available = true;
		this.notifyAll();
	}
}

class Producent extends Thread {
	private Bufor buf;
	private int number;

	public Producent(Bufor c, int number) {

		this.buf = c;
		this.number = number;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10000000; i++) {
			this.buf.put(i);
			System.out.println(LocalTime.now() + "::" + this.getId() + "-> Producent #" + this.number + " put: " + i);
			try {
				Thread.sleep((int) (Math.random() * 3000));
			} catch (InterruptedException e) {
			}
		}
	}
}

class Konsument extends Thread {
	private Bufor buf;
	private int number;

	public Konsument(Bufor c, int number) {

		this.buf = c;
		this.number = number;
	}

	@Override
	public void run() {

		int value = 0;
		for (int i = 0; i < 10000000; i++) {
			value = this.buf.get();
			System.out
				.println(LocalTime.now() + "::" + this.getId() + "-> Konsument #" + this.number + " got: " + value);
		}
	}
}