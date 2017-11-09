package com.codigo.aplios.toolbox.core.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.CRC32;

public class CsvFileRead {

	public static void main(String[] args) throws IOException {

		CsvFileRead.readStreamOfLinesUsingFilesWithTryBlock();
	}

	private static void readStreamOfLinesUsingFilesWithTryBlock() throws IOException {

		// String fileName =
		// String fileName ="c://Windows//Microsoft.NET//Framework//v2.0.50727//mscorlib.xml";//
		// "c://windows/csup.txt";
		String fileName =
				"D:/kdpw.source/rationalsdp/workspace/.metadata/.plugins/com.ibm.etools.iseries.edit/information/44P755J.nsdrtest_5Fobjects10.250.50.129_3AMECADEV_2FQRPGLESRC(ECANTF03MG).xml";

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			try {
				stream.forEach(CsvFileRead::compute);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		CRC32 crc = new CRC32();
		for (String string : CsvFileRead.context)
			crc.update(string.getBytes("UTF-8"));

		System.out.println("Koniec");
		System.out.println(CsvFileRead.context.size());
		System.out.println(crc.getValue());

		System.out.println("Rozmiar pliku: " + CsvFileRead.formatSize(CsvFileRead.fileSize));
	}

	static List<String> context = new ArrayList<>();
	private static long fileSize = 0;

	private static void compute(final String str) {

		CsvFileRead.context.add(str);
		byte[] utf8Bytes;
		byte[] winBytes;
		byte[] isoBytes;
		byte[] asciBytes;
		try {
			asciBytes = str.getBytes(StandardCharsets.US_ASCII);
			utf8Bytes = str.getBytes("UTF-8");
			winBytes = str.getBytes("CP1252");
			isoBytes = str.getBytes("ISO-8859-1");

			CsvFileRead.fileSize += asciBytes.length;

			System.out.print(asciBytes.length);
			System.out.print("::");
			System.out.print(utf8Bytes.length);
			System.out.print("::");
			System.out.print(winBytes.length);
			System.out.print("::");
			System.out.print(isoBytes.length);
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 4020122765

	public static String formatSize(long v) {

		if (v < 1024)
			return v + " B";
		int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
		return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
	}
}
