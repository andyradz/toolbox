package com.codigo.aplios.toolbox.core.attributes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PACKAGE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Version {

	/**
	 * Marker określa tryby w jakich może się znajdować element kodu programu. Element kodu programu należy rozumieć
	 * jako metoda, klasa, itp.
	 *
	 * @author amndrzej.radziszewski
	 */
	enum VersionMode {
		Develop,
		Tested,
		Released
	}

	/**
	 * Liczba major (główna) to wspólne oznaczenie wszystkich wersji programu bazujących na tych samych założeniach,
	 * mechanizmach itd. Zmienia się bardzo rzadko, najczęściej w wyniku zmiany koncepcji, założeń, API, podejścia itp.
	 * Czasami wiąże się z porzuceniem poprzednio rozwijanego programu i pisaniem kodu na nowo.
	 *
	 * @return Wartość numeryczna int.
	 */
	int major();

	/**
	 * Kolejnymi liczbami minor (drugorzędna) oznacza się kolejne etapy rozwoju programu w ramach tej samej koncepcji
	 * (wersji major). Zmienia się razem z zaimplementowaniem nowej funkcji, zmianą jakiejś istniejącej itp. Na tym
	 * etapie są często realizowane punkty TODO programu – razem z zaimplementowaniem kolejnej funkcjonalności podnosi
	 * się numer minor.
	 *
	 * @return Wartość numeryczna int.
	 */
	int minor();

	/**
	 * Używany niekiedy numer wydania (z ang. release) mówi, którym wydaniem w ramach wersji minor jest dana paczka
	 * programu. To ten numer rośnie najczęściej - razem z wydaniem kolejnej wersji. Tutaj zachodzą zmiany wynikające z
	 * poprawek błędów, integracji łatek, wkładania kolejnych fragmentów kodu do repozytorium projektu. Na tym etapie
	 * realizowane są punkty BUGS.
	 *
	 * @return Wartość numeryczna int.
	 */
	int build();

	/**
	 * Numer rewizji generowany jest podczas kolejnych kompilacji wykonywanych na projekcie, komponancie czy pakiecie.
	 * Jest automatycznie inkrementowany.
	 *
	 * @return Wartość numeryczna int.
	 */
	int revision();

	/**
	 * Atrybut określa tryb wersji elementu. Element może istnieć w trybach określonych za pomocą markera VersionMode.
	 *
	 * @return Wartość wyliczeniowa Enum.
	 */
	VersionMode mode();
}
