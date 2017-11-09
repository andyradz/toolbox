package com.codigo.aplios.toolbox.core.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SearchForWordWorker extends SwingWorker<Integer, String> {

	private static void failIfInterrupted() throws InterruptedException {

		TableColumnModel mod = new DefaultTableColumnModel();

		TableColumn col = new TableColumn();
		mod.addColumn(col);

		JTable tab;

		if (Thread.currentThread()
			.isInterrupted())
			throw new InterruptedException("Interrupted while searching files");
	}

	/** The word that is searched */
	private final String word;

	/**
	 * The directory under which the search occurs. All text files found under the given directory are searched.
	 */
	private final File directory;

	/** The text area where messages are written. */
	private final JTextArea messagesTextArea;

	/**
	 * Creates an instance of the worker
	 *
	 * @param word The word to search
	 * @param directory the directory under which the search will occur. All text files found under the given directory
	 *            are searched
	 * @param messagesTextArea The text area where messages are written
	 */
	public SearchForWordWorker(final String word, final File directory, final JTextArea messagesTextArea) {

		this.word = word;
		this.directory = directory;
		this.messagesTextArea = messagesTextArea;
	}

	@Override
	protected Integer doInBackground() throws Exception {

		// The number of instances the word is found
		int matches = 0;

		/*
		 * List all text files under the given directory using the Apache IO library. This process cannot be interrupted
		 * (stopped through cancellation). That is why we are checking right after the process whether it was
		 * interrupted or not.
		 */
		this.publish("Listing all text files under the directory: " + this.directory);
		final List<File> textFiles = new ArrayList<>();// FileUtils.listFiles(directory, new SuffixFileFilter(".txt"),
														// TrueFileFilter.TRUE));
		SearchForWordWorker.failIfInterrupted();
		this.publish("Found " + textFiles.size() + " text files under the directory: " + this.directory);

		for (int i = 0, size = textFiles.size(); i < size; i++) {
			/*
			 * In order to respond to the cancellations, we need to check whether this thread (the worker thread) was
			 * interrupted or not. If the thread was interrupted, then we simply throw an InterruptedException to
			 * indicate that the worker thread was cancelled.
			 */
			SearchForWordWorker.failIfInterrupted();

			// Update the status and indicate which file is being searched.
			final File file = textFiles.get(i);
			this.publish("Searching file: " + file);

			/*
			 * Read the file content into a string, and count the matches using the Apache common IO and Lang libraries
			 * respectively.
			 */
			// final String text = FileUtils.fileRead(file);
			// matches += StringUtils.countMatches(text, word);

			// Update the progress
			this.setProgress(((i + 1) * 100) / size);
		}

		// Return the number of matches found
		return matches;
	}

	@Override
	protected void process(final List<String> chunks) {

		// Updates the messages text area
		for (final String string : chunks) {
			this.messagesTextArea.append(string);
			this.messagesTextArea.append("\n");
		}
	}
}