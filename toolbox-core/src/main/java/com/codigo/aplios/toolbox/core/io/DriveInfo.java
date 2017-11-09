package com.codigo.aplios.toolbox.core.io;

import java.awt.Image;
import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

public class DriveInfo extends PathInfo implements IDriveInfo {

	public static void main(String[] args) {

		final DriveInfo driveInfo = new DriveInfo(Paths.get("c://"));
		// long value = driveInfo.availableFreeSpace();
		// value = driveInfo.totalFreeSpace();
		// value = driveInfo.totalSize();
		// driveInfo.driveType();
	}

	public DriveInfo(String driveName) throws IllegalArgumentException {

		super(Paths.get(driveName));
	}

	public DriveInfo(IDirectoryInfo driveName) throws IllegalArgumentException {

		super(Paths.get(driveName.fullName()));
	}

	public DriveInfo(File driveName) throws IllegalArgumentException {

		super(driveName.toPath());
	}

	public DriveInfo(URI driveName) throws IllegalArgumentException {

		super(Paths.get(driveName));
	}

	public DriveInfo(Path driveName) throws IllegalArgumentException {

		super(driveName);
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isReady() {

		return super.path().getFileSystem()
			.isOpen();
	}

	@Override
	public String name() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public IDirectoryInfo rootDirectory() {

		// return new DirectoryInfo(
		// this.driveName.toFile().getParentFile());
		return null;
	}

	@Override
	public String volumeLabel() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public long totalSize() {

		return super.path().toFile()
			.getTotalSpace();
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public long totalFreeSpace() {

		return super.path().toFile()
			.getFreeSpace();
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public IDriveInfo.DriveType driveType() {

		// FileSystemView fsv = this.driveName.getFileSystem();
		FileSystems.getDefault()
			.getFileStores()
			.forEach(fileStore -> {
				System.out.println(fileStore.type() + " " + fileStore.name());
			});
		return null;
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public IDriveInfo.DriveFormat driveFormat() {

		return null;
	}

	@Override
	public long availableFreeSpace() {

		return this.totalSize() - super.path().toFile()
			.getUsableSpace();
	}

	@Override
	public Image icon() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public String toString() {

		return Objects.toString(this);
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this);
	}

	@Override
	public boolean equals(Object driveInfo) {

		if (this == driveInfo)
			return true;
		if (driveInfo == null)
			return false;
		if (this.getClass() != driveInfo.getClass())
			return false;
		final DriveInfo other = (DriveInfo) driveInfo;
		return (!Objects.equals(super.path(), other.path()));
	}

	@Override
	public String fullName() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public String localName() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public boolean isRoot() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public boolean isDirectory() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public boolean isFile() {

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
	public LocalDateTime creationTime() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public LocalDateTime creationTimeUtc() {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public void accept(IPathVisitor visitor) {

		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		// Tools | Templates.
	}

}
