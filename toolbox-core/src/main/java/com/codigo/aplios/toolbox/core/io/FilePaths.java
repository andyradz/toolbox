/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author andrzej.radziszewski
 */
public class FilePaths {

	public void changeExtension(String fileName, String extension) {

	}

	public char directorySeparatorChar() {

		return '\0';
	}

	public Path combine(String... paths) {

		return Paths.get("", paths);
	}

	public Path combine(String path1, String path2) {

		return Paths.get(path1, path2);
	}

}
