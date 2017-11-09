package com.codigo.aplios.toolbox.core.metric;

import java.math.BigDecimal;

enum UnitOfSI {
	W1,
	W2;

	static enum Prefix {
		JOTTA("jotta", "Y", BigDecimal.valueOf(10E24)),
		ZETTA("zetta", "Z", BigDecimal.valueOf(10E21)),
		EXA("exa", "E", BigDecimal.valueOf(10E10)),
		PETA("peta", "P", BigDecimal.valueOf(10E15)),
		TERA("tera", "T", BigDecimal.valueOf(10E12)),
		GIGA("giga", "G", BigDecimal.valueOf(10E9)),
		MEGA("mega", "M", BigDecimal.valueOf(10E6)),
		KILO("kilo", "k", BigDecimal.valueOf(10E3)),
		HEKTO("hekto", "h", BigDecimal.valueOf(10E2)),
		DEKA("deka", "da", BigDecimal.valueOf(10E1));

		private final String name;

		/*
		 *
		 */
		private final String symbol;

		/*
		 *
		 */
		private final BigDecimal rate;

		/**
		 *
		 * @param name
		 * @param symbol
		 * @param rate
		 */
		private Prefix(String name, String symbol, BigDecimal rate) {

			this.name = name;
			this.symbol = symbol;
			this.rate = rate;
		}

		// ------------------------------------------------------------------------------------------------------------------
	}

}
