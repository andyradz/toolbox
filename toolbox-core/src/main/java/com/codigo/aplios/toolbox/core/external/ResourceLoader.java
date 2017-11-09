package com.codigo.aplios.toolbox.core.external;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Klasa realizuje mechanizm dostępu do elementów statycznych programu. Umożliwia ładowanie zasobów typu napisy
 * tekstowe, ikony, obrazy, konfiguracje, pliki binarne. Zasoby podzielone są na grupy, które odpowiadają warstwą
 * aplikacyjnym dostępnym w systemie.
 *
 * @author Andrzej Radziszewski ar.radziszewski@gmail.com File name ResourceLoader.java Created date 2016-05-25
 */
public final class ResourceLoader {

	/**
	 * Typ wyliczeniowy definiuje listę indykatorów, które przedstawiają znaczki poziomów środowisk dostępnych w
	 * systemi.
	 */
	enum EnvironmentLevel {
		/* Poziom aplikacji wskazuje na środowisko rozwojowe systemu */
		DEVELOPMENT("development"),
		/* Poziom aplikacji wskazuje środowisko testowe systemu */
		TEST("test"),
		/* Poziom aplikacji wskazuje środowisko produkcyjne systemu */
		PRODUCTION("production");

		/*
		 * Atrybut przedstawia poziom srodowiska systemu.
		 */
		private final String mEnvironmentLevel;

		/**
		 * Podstawowy konstruktor struktury.
		 *
		 * @param bundle parametr przedstawia poziom środowiska systemu.
		 */
		EnvironmentLevel(String bundle) {

			this.mEnvironmentLevel = bundle;
		}

		/**
		 * Metoda pobiera pakiet elementów statycznych z zasobu systemu. Dane zawarte w pakiecie dotyczą wskazanego
		 * poziomu aplikacji systemu.
		 *
		 * @param indykator obszaru zasobów statycznych systemu.
		 * @return pakiet elementów statycznych z zasobów systemu.
		 */
		public ResourceBundle getBundle(Bundle element) {

			return EnumSet.of(element)
				.stream()
				.findFirst()
				.orElse(null)
				.getContext(this.mEnvironmentLevel);
		}

		/*
		 * Typ wyliczeniowy definuje listę indykatorów, które przedstawiają obaszar elementów statycznych systemu.
		 */
		enum Bundle {
			/* Definicje opisów dla wyjątków systemowych */
			ERRORS("strings.errors"),
			/* Konfiguracja modułu Log4J */
			LOG4J("strings.log4j"),
			/* Definicje opisów dla komunikatów systemowych */
			MESSAGES("strings.messages"),
			/* Pliki graficzne używane w systemie */
			IMAGES("images"),
			/* Pliki ikon używane w systemie */
			ICONS("icons");

			/*
			 * Atrybut przedstawia nazwę statycznego pakietu zasobu systemu.
			 */
			private String mBundle;

			/**
			 * Podstawowy konstruktor klasy.
			 *
			 * @param bundle parametr przedstawia element obszaru zasobów statycznych systemu.
			 */
			Bundle(String bundle) {

				this.mBundle = bundle;
			}

			/**
			 * Metoda pobiera pakiet elementów statycznych z zasobu systemu. Dane zawarte w pakiecie dotyczą wskazanego
			 * poziomu środowiska systemu.
			 *
			 * @param environmentLevel indykator obszaru zasobów statycznych systemu.
			 * @return pakiet elementów statycznych z zasobów systemu.
			 */
			private ResourceBundle getContext(String environmentLevel) {

				final Locale myLocale = Locale.getDefault();
				// final ResourceBundle myBundle =
				// ResourceBundle.getBundle(environmentLevel + "." + this.mBundle, myLocale);
				final Object myBundle = new ImageBundle().getObject("");
				// ImageBundle.getBundle("").getObject("PIC1");
				return null;// myBundle;
			}

			public void setBundle(String bundle) {

				this.mBundle = bundle;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		ImageBundle img = new ImageBundle();
		for (final String item : img.keySet()) {
			URL resource = (URL) (new ImageBundle().getObject(item));

			try (InputStream is = resource.openStream()) {
				System.out.println(item + ":" + (is.available() / 1024) + "KB" + "(" + is.available() + " bytes" + ")");
			}
		}

		// ResourceBundle param1 = EnvironmentLevel.DEVELOPMENT.getBundle(Bundle.IMAGES);
		//
		// ResourceBundle params = EnvironmentLevel.DEVELOPMENT.getBundle(Bundle.LOG4J);
		//
		// for (String key : params.keySet()) {
		// System.out.println(key);
		// System.out.println(params.getString(key));
		// }

		// Bundle.ERRORS.setBundle("labels.metka");
		// ResourceBundle params = EnvironmentLevel.DEVELOPMENT.getBundle(Bundle.ERRORS);
		// System.out.println(MessageFormat.format(params.getString("author"), "A_", "R_"));

		// for (String key : params.keySet()) {
		// System.out.println(key);
		// System.out.println(params.getString(key));
		// }
	}
}

class ImageBundle extends ListResourceBundle {

	@Override
	public Object[][] getContents() {

		return this.mImages;
	}

	private Object[][] mImages =
			{
				{ "PIC1", ResourceLoader.class.getResource("/development/images/pic1.jpg") },
				{ "PIC2", ResourceLoader.class.getResource("/development/images/pic2.jpg") },
				{ "PIC3", ResourceLoader.class.getResource("/development/images/pic3.jpg") },
				{ "PIC4", ResourceLoader.class.getResource("/development/images/pic4.jpg") } };
}
