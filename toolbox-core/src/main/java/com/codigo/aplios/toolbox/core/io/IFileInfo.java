/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

/**
 *
 * @author andrzej.radziszewski
 */
/**
 *
 * @author andrzej.radziszewski
 */
public interface IFileInfo extends IPathInfo {

	static enum FileAttributes {
		Archive,
		Compressed,
		Device,
		Directory,
		Encrypted,
		Hidden,
		IntegritySystem,
		Normal,
		NoScrubData,
		NoContentIndexed,
		Offline,
		ReadOnly,
		ReparsePoint,
		SparseFile,
		System,
		Temporary
	}

	FileAttributes attributes();

	IDirectoryInfo directory();

	String directoryName();

	boolean exists();

	String extension();

	boolean isReadOnly();

}
