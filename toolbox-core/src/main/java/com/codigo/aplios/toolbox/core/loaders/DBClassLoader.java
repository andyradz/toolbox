package com.codigo.aplios.toolbox.core.loaders;

// TODO: dodać implementacje db class loadera
public class DBClassLoader extends ClassLoader {

	// -----------------------------------------------------------------------------------------------------------------

	public DBClassLoader(ClassLoader parent) {

		super(parent);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private Class<?> getClass(String name) throws ClassNotFoundException {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------
}
