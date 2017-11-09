package com.codigo.aplios.toolbox.core.metric;

import java.util.EnumSet;

interface Dimension {

	double getWidth();

	double getHeight();

}
// standard iso 216
// system amerykański
// system brytyjski
// system japoński
// system w drukarniach
// http://www.dmk.com.pl/index.php/tytul1/111-formty-papieru.html

public enum PaperSizes {
	SerieA(null),
	SerieB(null),
	SerieC(null),
	A0(SerieA, 841d, 1189d),
	A1(SerieA, 594d, 841d),
	A2(SerieA, 420d, 594d),
	A3(SerieA, 297d, 420d),
	A4(SerieA, 210d, 297d),
	A5(SerieA, 148d, 210d),
	A6(SerieA, 105d, 148d),
	A7(SerieA, 74d, 105d),
	A8(SerieA, 52d, 74d),
	A9(SerieA, 37d, 52d),
	A10(SerieA, 26d, 37d),
	B1(SerieB),
	B2(SerieB),
	B3(SerieB),
	B4(SerieB),
	B5(SerieB),
	B6(SerieB),
	B7(SerieB),
	B8(SerieB),
	B9(SerieB),
	B10(SerieB),
	C1(SerieC),
	C2(SerieC),
	C3(SerieC),
	C4(SerieC),
	C5(SerieC),
	C6(SerieC),
	C7(SerieC),
	C8(SerieC),
	C9(SerieC),
	C10(SerieC);

	private final double width;

	private final double height;

	PaperSizes parent = null;

	PaperSizes(PaperSizes parent) {

		this(parent, 0, 0);
	}

	PaperSizes(PaperSizes parent, double width, double height) {

		this.width = width;
		this.height = height;
		this.parent = parent;
	}

	public double getWidth() {

		return this.width;
	}

	public double getHeight() {

		return this.height;
	}

	public boolean is(PaperSizes other) {

		if (other == null)
			return false;

		for (PaperSizes t = this; t != null; t = t.parent)
			if (other == t)
				return true;
		return false;
	}

	public static EnumSet<PaperSizes> getElements(PaperSizes parent) {

		EnumSet<PaperSizes> set = EnumSet.noneOf(PaperSizes.class);

		for (PaperSizes page : EnumSet.allOf(PaperSizes.class))
			// if (page.parent == parent)
			set.add(page);

		return set;
	}

	@Override
	public String toString() {

		return String.format("[W]:%5s x [H]:%6s", Double.toString(this.width), Double.toString(this.height));
	}

	public static void main(String[] args) {

		EnumSet<PaperSizes> set = PaperSizes.getElements(SerieA);
		set.addAll(PaperSizes.getElements(SerieB));
		set.addAll(PaperSizes.getElements(SerieC));
		for (PaperSizes paper : set)
			System.out.println(paper);
	}

}
