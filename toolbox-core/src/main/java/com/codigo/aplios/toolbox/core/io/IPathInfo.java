/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.awt.Image;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author andrzej.radziszewski
 */
/**
 * Interfejs reprezentuje zachownanie i właściwości obiektu będącego ścieżką do konkretnego obiektu systemu plików
 * danego systemu operacyjnego.
 *
 * @author andrzej.radziszewski
 */
public interface IPathInfo {

	/**
	 * Właściwość reprezentuje nazwę obiektu systemu plików.
	 *
	 * @return
	 */
	String name();

	/**
	 * Właściwość reprezentuje pełną nazwę obiektu systemu plików. Pełna nazwa składa się z volumenu, ścieżki i nazwy
	 * obiektu.
	 *
	 * @return
	 */
	String fullName();

	String localName();

	boolean isRoot();

	boolean isDirectory();

	boolean isFile();

	long deeph();

	long size();

	long capacity();

	long length();

	LocalDateTime creationTime();

	LocalDateTime creationTimeUtc();

	LocalDateTime lastAccessTime();

	LocalDateTime lastAccessTimeUtc();

	LocalDateTime lastWriteTime();

	LocalDateTime lastWriteTimeUtc();

	String type();

	String description();

	String label();

	List<?> atributes();

	Image icon();

	void accept(IPathVisitor visitor);

}
