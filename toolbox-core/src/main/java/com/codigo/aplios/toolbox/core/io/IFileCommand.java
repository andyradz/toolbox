package com.codigo.aplios.toolbox.core.io;

/**
 *
 * @author andrzej.radziszewski
 */
public interface IFileCommand {

	void execute();

	void cancel();

	void restart();

	void result();

}

class FileLoader implements IFileCommand {

	@Override
	public void execute() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void cancel() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void restart() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void result() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}

/*
 * Klasa wykonuje polecenia dostarczone do wykonania
 */
class FileCommander {

	// TODO:dodać miernik czasu tutaj
	private final IFileCommand fileCommand;

	public FileCommander(IFileCommand fileCommand) {

		this.fileCommand = fileCommand;
	}

	public void run() {

		this.fileCommand.execute();
	}

	public void end() {

		this.fileCommand.cancel();
	}

}