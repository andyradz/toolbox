package com.codigo.aplios.toolbox.core.domain;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

public class TestRunner {

	public static void main(String[] args) throws UnsupportedEncodingException {

		// Result result = JUnitCore.runClasses(PrimeNumberCheckerTest.class);
		// for (Failure failure : result.getFailures()) {
		// System.out.println(failure.toString());
		// }
		// System.out.println(result.wasSuccessful());

		Charset utf8 = Charset.forName("utf-8");
		String asB64 = Base64.getEncoder()
			.encodeToString("andrzej marek radziszewski".getBytes(utf8));
		System.out.println(asB64);

		byte[] asBytes = Base64.getDecoder()
			.decode("YW5kcnplaiBtYXJlayByYWR6aXN6ZXdza2k=");
		System.out.println(new String(asBytes, utf8));

	}
}
