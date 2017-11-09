package com.codigo.aplios.toolbox.core.loaders;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Our Custom Class Loader to load the classes. Any class in the com.journaldev package will be loaded using this
 * ClassLoader. For other classes, it will delegate the request to its Parent ClassLoader.
 *
 */
public class CCLoader extends ClassLoader {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String progClass = "12";
		String progArgs[] = new String[args.length - 1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);

		CCLoader ccl = null;// new CCLoader(Screen.class.getClassLoader());
		Class<?> clas = ccl.loadClass("com.codigo.aplios.contos.system.identity.Screen");
		Class<?> mainArgType[] = { (String[].class) };
		Method main = clas.getMethod("main", mainArgType);
		// Method main = clas.getMethod("main");
		Object argsArray[] = { progClass };
		main.invoke("23", argsArray);
		// main.invoke(null);

		// Below method is used to check that the Foo is getting loaded
		// by our custom class loader i.e CCLoader
		// Method printCL = clas.getMethod("printCL", null);
		// printCL.invoke(null, new Object[0]);

	}

	/**
	 * This constructor is used to set the parent ClassLoader
	 */
	public CCLoader(ClassLoader parent) {

		super(parent);
	}

	/**
	 * Loads the class from the file system. The class file should be located in the file system. The name should be
	 * relative to get the file location
	 *
	 * @param name Fully Classified name of class, for example com.journaldev.Foo
	 */
	private Class getClass(String name) throws ClassNotFoundException {

		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] b = null;
		try {
			// This loads the byte code data from the file
			b = this.loadClassFileData(file);
			// defineClass is inherited from the ClassLoader class
			// that converts byte array into a Class. defineClass is Final
			// so we cannot override it
			Class c = this.defineClass(name, b, 0, b.length);
			this.resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Every request for a class passes through this method. If the class is in com.journaldev package, we will use this
	 * classloader or else delegate the request to parent classloader.
	 *
	 *
	 * @param name Full class name
	 */
	@Override
	public Class loadClass(String name) throws ClassNotFoundException {

		System.out.println("Loading Class '" + name + "'");
		if (name.startsWith("com.journaldev")) {
			System.out.println("Loading Class using CCLoader");
			return this.getClass(name);
		}
		return super.loadClass(name);
	}

	/**
	 * Reads the file (.class) into a byte array. The file should be accessible as a resource and make sure that its not
	 * in Classpath to avoid any confusion.
	 *
	 * @param name File name
	 * @return Byte array read from the file
	 * @throws IOException if any exception comes in reading the file
	 */
	private byte[] loadClassFileData(String name) throws IOException {

		InputStream stream = this.getClass()
			.getClassLoader()
			.getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}

class Foo {
	static public void main(String args[]) throws Exception {

		System.out.println("Foo Constructor >>> " + args[0] + " " + args[1]);
		Bar bar = new Bar(args[0], args[1]);
		bar.printCL();
	}

	public static void printCL() {

		System.out.println("Foo ClassLoader: " + Foo.class.getClassLoader());
	}
}

class Bar {

	public Bar(String a, String b) {

		System.out.println("Bar Constructor >>> " + a + " " + b);
	}

	public void printCL() {

		System.out.println("Bar ClassLoader: " + Bar.class.getClassLoader());
	}
}
