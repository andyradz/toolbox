package com.codigo.aplios.toolbox.core.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author andrzej.radziszewski
 */
public class DriveInfoLoader implements IFileCommand {

	public static void main(String[] args) {

		DriveInfoLoader d = new DriveInfoLoader();
		d.execute();
	}

	// TODO: daoać odcztwanie w innym wątku aby przerwać
	private final List<IDriveInfo> driveInfo;

	// dodać filtry
	public DriveInfoLoader() {

		this.driveInfo = new ArrayList<>();
	}

	@Override
	public void execute() {

		this.process();
	}

	@Override
	public void cancel() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void restart() {

		this.driveInfo.clear();
	}

	@Override
	public void result() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	private void process() {

		File[] drives = File.listRoots();
		Stream.of(drives)
			.forEach(drive -> {
				// todo tutaj automatyczna wstrzykiwanie implementacji obiektu
				this.driveInfo.add(new DriveInfo(drive));
			});
	}

}
