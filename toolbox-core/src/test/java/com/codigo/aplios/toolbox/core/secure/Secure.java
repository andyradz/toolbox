package com.codigo.aplios.toolbox.core.secure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.swing.filechooser.FileSystemView;

import com.codigo.aplios.toolbox.core.MockSecurityManager;

public class Secure {

	interface ComputingHash {
		// BCrypt
		// SCrypt
		// MD5
		// SHA1
		String computeHash();

	}

	interface FormattingHash {
		// duże małe litery
	}

	public static boolean isOdd(long number) {
		// MessageDigest.getInstance(algorithm)
		// ChecksumFactory.of(algorytm,string,locale)
		// ChecksumFactory.of(algorytm,array ,locale)
		// ChecksumFactory.of(algorytm,array)
		// ChecksumFactory.of(algorytm,URI)
		// ChecksumFactory.of(algorytm,Path)
		// ChecksumFactory.of(algorytm,File)

		if ((number % 2) == 0)
			return false;
		return true;
	}

	public static void main(String args[]) throws FileNotFoundException {
		// @Regex String validation = "(Java|JDK) [7,8]";
		// @NonNull String str1;
		// @NonNull String str2;
		// @NonNull String str3;

		int val = 0b11001001;
		System.out.println(Integer.toBinaryString(val));
		val = Integer.reverse(val);
		System.out.println(Integer.toBinaryString(val >>> 24));

		Stream.of(Arrays.asList("12", "12", "bx", "aaav", "zz", "", "", ""))
			.flatMap(Collection::stream)
			.filter(e -> !e.isEmpty())
			.forEach(System.out::println);

		Stream.iterate(2L, n -> n + 1)
			.filter(Secure::isOdd)
			.skip(100)
			.limit(10)
			.forEach(System.out::println);

		System.exit(0);
		Clock clock = Clock.systemDefaultZone();
		Instant instant = clock.instant();
		// Date date = Date.from(instant);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy");
		// DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
		System.out.println(dtf.format(instant));
		System.exit(0);

		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("d:/file.txt")), true));
		System.setSecurityManager(new MockSecurityManager());
		SecurityManager dc = System.getSecurityManager();
		dc.checkPackageAccess("allthings");
		System.out.println(dc.toString());

		StringBuilder builder;
		StringBuffer buffer;
		StringJoiner join = new StringJoiner("|", "|", "|");
		join.add("1212121212");
		join.add("ewqeqweqwewq");
		join.add("000000000000");
		System.out.println(join);

		String str1 = "Andrzej Radziszewski";
		String str2 = "Andrzej Radziszewski";

		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));

		str1 = new String("Andrzej Radziszewski");
		str2 = new String("Andrzej Radziszewski");

		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));

		str1 = new String("Andrzej Radziszewski").intern();
		str2 = new String("Andrzej Radziszewski").intern();

		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));

		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String s1 = FileSystemView.getFileSystemView()
				.getSystemDisplayName(f);
			String s2 = FileSystemView.getFileSystemView()
				.getSystemTypeDescription(f);
			boolean idDrive = FileSystemView.getFileSystemView()
				.isDrive(f);
			System.out.println("getSystemDisplayName : " + s1);
			System.out.println("getSystemTypeDescription : " + s2);
			System.out.println("isDrive                  : " + Boolean.toString(idDrive));
			// System.out.println("isFloopy : " + Boolean.TRUE.toString(idFloopy));

		}
		/*
		 * output (French WinXP)
		 *
		 * getSystemDisplayName : getSystemTypeDescription : Disquette 3½ pouces
		 *
		 * getSystemDisplayName : REGA1 (C:) getSystemTypeDescription : Disque local
		 *
		 * getSystemDisplayName : getSystemTypeDescription : Lecteur CD
		 *
		 * getSystemDisplayName : My Book (F:) getSystemTypeDescription : Disque local
		 */
	}
}
