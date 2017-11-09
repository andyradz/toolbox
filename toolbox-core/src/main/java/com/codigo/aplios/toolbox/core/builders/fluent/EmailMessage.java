package com.codigo.aplios.toolbox.core.builders.fluent;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.codigo.aplios.toolbox.core.attributes.Author;
import com.codigo.aplios.toolbox.core.attributes.Changelog;

@Changelog(date = "2016.04.15", authors = {
		@Author(name = "", login = "", email = ""),
		@Author(name = "", login = "", email = "") })
public class EmailMessage {

	public static void main(final String[] args) throws InterruptedException {

		EmailMessage.from("sd")
				.to("and.radz@wp.pl", "izabela141@wp.pl")
				.subject("Informacja raportowa")
				.content("Test admin")
				.mimeType("html")
				.build();

		// {// Przetwarzanie w wątku
		// BlockingQueue<EmailMessage> usersQueue = new LinkedBlockingQueue<>();
		// Supplier<EmailMessage> userSupplier = new Supplier<EmailMessage>() {
		// @Override
		// public EmailMessage get() {
		//
		// try {
		// return usersQueue.take();
		// } catch (InterruptedException e) {
		// throw new RuntimeException(e);
		// }
		// }
		// };

		// Consumer<EmailMessage> userConsumer = new Consumer<EmailMessage>() {
		// @Override
		// public void accept(EmailMessage user) {
		//
		// if (0 == (Integer.parseInt(user.to) % 1_000_000))
		// System.out.println("Processing email " + user.from);
		// }
		// };
		//
		// new SupplierConsumer<>(userSupplier, userConsumer).start();
		//
		// for (int i = 0; i < 1_500_000_000; i++) {
		// usersQueue.put(EmailMessage.from(String.format("Lp. %,015d", i))
		// .to(String.valueOf(i))
		// .content("sds")
		// .build());
		// }
		//
		// usersQueue.put(EmailMessage.from(String.format("Lp. %,015d", 500001))
		// .to("500001")
		// .content("500001")
		// .build());
		// }
	}

	private String from;
	private ArrayList<String> to;
	private String subject;
	private String content;
	private String mimeType;

	private EmailMessage() {

	}

	public static ITo from(final String from) {

		return new EmailMessage.Builder(from);
	}

	public interface ITo {
		IContent to(String to);

		IContent to(String... to);
	}

	public interface ISubject {
		IContent subject(String subject);
	}

	public interface IContent {
		IContent subject(String subject);

		IBuild content(String content);
	}

	public interface IBuild {
		IBuild mimeType(String mimeType);

		EmailMessage build();
	}

	private static class Builder implements ITo, ISubject, IContent, IBuild {
		private final EmailMessage instance = new EmailMessage();

		public Builder(final String from) {

			this.instance.from = from;
		}

		@Override
		public IBuild mimeType(final String mimeType) {

			this.instance.mimeType = mimeType;
			return this;
		}

		@Override
		public EmailMessage build() {

			return this.instance;
		}

		@Override
		public IBuild content(final String content) {

			this.instance.content = content;
			return this;
		}

		@Override
		public IContent subject(final String subject) {

			this.instance.subject = subject;
			return this;
		}

		@Override
		public IContent to(final String to) {

			this.instance.to.add(to);
			return this;
		}

		@Override
		public IContent to(final String... to) {

			for (final String str : to)
				this.instance.to.add(str);
			return this;
		}
	}
}

/**
 * @author Andrzej Radziszewski
 * @category Thread
 * @param <T> Typ danych obsługiwany przez obiekt klasy.
 */
class SupplierConsumer<T> extends Thread {

	private final Supplier<T> supplier;
	private final Consumer<T> consumer;
	private final boolean shouldRun = true;

	public SupplierConsumer(final Supplier<T> supplier, final Consumer<T> consumer) {

		this.supplier = supplier;
		this.consumer = consumer;
	}

	@Override
	public void run() {

		while (this.shouldRun) {
			final T item = this.supplier.get();
			this.consumer.accept(item);
		}
	}
}
