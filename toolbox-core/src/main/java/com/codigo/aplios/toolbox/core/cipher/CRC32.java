package com.codigo.aplios.toolbox.core.cipher;

public class CRC32 {
	public static void main(String[] args) {

		int data = 0b11010011101110;
		int div = 0b1011;

		data <<= 3;
		div <<= 13;
		System.out.println(Integer.toBinaryString(data));
		System.out.println(Integer.toBinaryString(div));

		data ^= div;
		System.out.println(Integer.toBinaryString(data));
	}
}
