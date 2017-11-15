/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author andrzej.radziszewski
 */
/**
 *
 * @author andrzej.radziszewski
 */
public abstract class PathInfo implements IPathInfo {

    public static void main(String[] args) {

        // FileInfo pathInfo = new FileInfo("c://INS_afadc337.TMP");
        FileInfo pathInfo = new FileInfo("c:");

        System.out.println(pathInfo.name());
        System.out.println(pathInfo.localName());
        System.out.println(pathInfo.fullName());
        System.out.println(pathInfo.label());
        System.out.println(pathInfo.creationTime());
        System.out.println(pathInfo.creationTimeUtc());
        System.out.println(pathInfo.lastAccessTime());
        System.out.println(pathInfo.lastAccessTimeUtc());
        System.out.println(pathInfo.lastWriteTime());
        System.out.println(pathInfo.lastWriteTimeUtc());
        System.out.println(pathInfo.isFile());
        System.out.println(pathInfo.isDirectory());
        System.out.println(pathInfo.isRoot());

    }

    /**
     * @param fileTime
     * @return
     */
    private static LocalDateTime fileTimeToUtcTime(FileTime fileTime) {

        return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.of("UTC"));
    }

    private final Path pathName;

    // -----------------------------------------------------------------------------------------------------------------
    public PathInfo(String pathName) throws IllegalArgumentException {

        this(Paths.get(pathName));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public PathInfo(IDirectoryInfo pathName) throws IllegalArgumentException {

        this(Paths.get(pathName.fullName()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public PathInfo(File pathName) throws IllegalArgumentException {

        this(pathName.toPath());
    }

    // -----------------------------------------------------------------------------------------------------------------
    public PathInfo(URI pathName) throws IllegalArgumentException {

        this(Paths.get(pathName));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Path path() {

        return this.pathName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected PathInfo(Path pathName) throws IllegalArgumentException {

        this.pathName = pathName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public IFileInfo.FileAttributes attributes() {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime creationTime() {

        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return LocalDateTime.ofInstant(pathAttr.creationTime()
                    .toInstant(), ZoneId.systemDefault());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime creationTimeUtc() {

        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return PathInfo.fileTimeToUtcTime(pathAttr.creationTime());

        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String name() {

        return this.pathName.toFile()
                .getName()
                .toString();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String fullName() {

        return this.pathName.toFile()
                .getAbsolutePath()
                .toString();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String localName() {

        return this.pathName.toFile()
                .getPath();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isRoot() {

        return FileSystemView.getFileSystemView()
                .isFileSystemRoot(this.pathName.toFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isDirectory() {

        return (this.pathName.toFile()
                .isDirectory());
    }

    @Override
    public boolean isFile() {

        return (this.pathName.toFile()
                .isFile());
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
    public long length() {

        return this.pathName.getFileName()
                .toString()
                .length();
    }

    @Override
    public String type() {

        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public String description() {

        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public List<?> atributes() {

        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public Image icon() {

        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public String label() {

        return FileSystemView.getFileSystemView()
                .getSystemDisplayName(this.pathName.toFile());
    }

    @Override
    public abstract void accept(IPathVisitor visitor);

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime lastAccessTime() {

        BasicFileAttributes pathAttribute;
        try {
            pathAttribute = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return LocalDateTime.ofInstant(pathAttribute.lastAccessTime()
                    .toInstant(), ZoneId.systemDefault());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime lastAccessTimeUtc() {

        BasicFileAttributes pathAttribute;
        try {
            pathAttribute = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return PathInfo.fileTimeToUtcTime(pathAttribute.lastAccessTime());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime lastWriteTime() {

        BasicFileAttributes pathAttribute;
        try {
            pathAttribute = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return LocalDateTime.ofInstant(pathAttribute.lastModifiedTime()
                    .toInstant(), ZoneId.systemDefault());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime lastWriteTimeUtc() {

        BasicFileAttributes pathAttribute;
        try {
            pathAttribute = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class)
                    .readAttributes();
            return PathInfo.fileTimeToUtcTime(pathAttribute.lastModifiedTime());

        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    // -----------------------------------------------------------------------------------------------------------------
}
