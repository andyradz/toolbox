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
public interface IDriveInfo extends IPathInfo {

	static enum DriveType {
		CDROM,
		FIXED,
		NETWORK,
		NOROOTDIRECTORY,
		RAM,
		REMOVABLE,
		UNKNOWN;
	}

	static enum DriveFormat {
		FAT16,
		FAT32,
		NTFS;
	}

	static IDriveInfo getDrives() {

		return null;
	}

	boolean isReady();

	IDirectoryInfo rootDirectory();

	String volumeLabel();

	long totalSize();

	long totalFreeSpace();

	DriveType driveType();

	DriveFormat driveFormat();

	long availableFreeSpace();

}
