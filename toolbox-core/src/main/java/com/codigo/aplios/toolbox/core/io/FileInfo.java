/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

/**
 *
 * @author andrzej.radziszewski
 */
/**
 *
 * @author andrzej.radziszewski
 */
public final class FileInfo extends PathInfo implements IFileInfo {

	public FileInfo(String fileName) throws IllegalArgumentException {

		super(Paths.get(fileName));
	}

	// ------------------------------------------------------------------------------------------------------------------
	public FileInfo(IDirectoryInfo fileName) throws IllegalArgumentException {

		super(Paths.get(fileName.fullName()));
	}

	// ------------------------------------------------------------------------------------------------------------------
	public FileInfo(File fileName) throws IllegalArgumentException {

		super(fileName.toPath());
	}

	// ------------------------------------------------------------------------------------------------------------------
	public FileInfo(URI fileName) throws IllegalArgumentException {

		super(Paths.get(fileName));
	}

	@Override
	public IDirectoryInfo directory() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String directoryName() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public boolean exists() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String extension() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public boolean isReadOnly() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public long deeph() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public long size() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public long capacity() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String type() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void accept(IPathVisitor visitor) {

		visitor.visit(this);
	}

}
