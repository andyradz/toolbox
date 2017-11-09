package com.codigo.aplios.toolbox.core.winapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// http://krzysztofjelonek.net/java-i-listing-wszystkich-plikow-w-katalogu/
// Fabryki
// http://krzysztofjelonek.net/wzorce-projektowe-fabryki/
public class FileVisit {

	public static void main(String[] args) {

		FileVisitable visitor = new PrintToOutFileVisitor();
		new JavaFileVisitor(visitor).walk("d://");
	}
}

interface FileVisitable {

	void visit(String filePath);
}

class PrintToOutFileVisitor implements FileVisitable {

	private long no = 0;

	@Override
	public void visit(String filePath) {

		this.no++;
		System.out.println(this.no + ":" + filePath);
	}
}

abstract class AbstractFileVisitor {

	protected FileVisitable visitor;

	public AbstractFileVisitor(FileVisitable visitor) {

		this.visitor = visitor;
	}

	public abstract void walk(String rootPath);

}

class JavaFileVisitor extends AbstractFileVisitor {

	public JavaFileVisitor(FileVisitable visitor) {

		super(visitor);
	}

	@Override
	public void walk(String rootPath) {

		try {
			Files.walk(Paths.get(rootPath))
				.filter(path -> path.toFile()
					.isFile())
				.forEach(this::visit);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void visit(Path path) {

		this.visitor.visit(path.toString());
	}

}
