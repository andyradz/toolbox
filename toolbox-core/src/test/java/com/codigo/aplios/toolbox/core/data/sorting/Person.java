package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

public final class Person implements Formattable {

	public static void main(String[] args) {

		Person e = new Person("Andrzej", "Radziszewski", (byte) 37);

		System.out.printf("%#s\n", e);
		System.out.printf("%20s\n", e);
		System.out.printf("%#30S\n", e);
		System.out.printf("%#.10S\n", e);
	}

	private String name;
	private String surname;
	private byte age;

	// -----------------------------------------------------------------------------------------------------------------

	public Person(String name, String surname, byte age) {

		this.setName(name);
		this.setSurname(surname);
		this.setAge(age);
	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getSurname() {

		return this.surname;

	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getName() {

		return this.name;
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void setName(String name) {

		this.name = name;
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void setSurname(String surname) {

		this.surname = surname;
	}

	public byte getAge() {

		return this.age;
	}

	private void setAge(byte age) {

		this.age = age;
	}

	@Override
	public String toString() {

		return "person" + "{" + "name:" + "\"" + this.getName() + "\"" + "," + "surname:" + "\"" + this.getSurname()
				+ "\"" + "," + "age:" + "\"" + this.getAge() + "\"" + "}";
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {

		String txt = this.surname;
		if ((flags & FormattableFlags.ALTERNATE) == FormattableFlags.ALTERNATE)
			txt += ' ' + this.name;
		String fs = "%";
		if ((flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY)
			fs += '-';
		if (width >= 0)
			fs += width;
		if (precision >= 0)
			fs += "." + precision;
		fs += ((flags & FormattableFlags.UPPERCASE) == FormattableFlags.UPPERCASE) ? "S"
				: "s";
		formatter.format(fs, txt);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
