package com.codigo.aplios.toolbox.core.loaders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

public class CodigoClassLoader extends ClassLoader {

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		URLClassLoader clsLoader = URLClassLoader.newInstance(new URL[] { new URL("file:/d://program.jar") });
		Class<?> cls = clsLoader.loadClass("com.codigo.aplios.contos.system.identity.Screen");

		// Method method = cls.getMethod("main", String[].class);
		// String[] params = new String[2];
		// method.invoke(null, (Object) params);

		CodigoClassLoader loader = new CodigoClassLoader(Arrays.asList(new URL("file:/d://program.jar")));
		cls = loader.loadClass("com.codigo.aplios.contos.system.identity.Screen");
		Method method = cls.getMethod("main", String[].class);
		String[] params = new String[2];
		method.invoke(null, (Object) params);
	}

	private ChildClassLoader childClassLoader;

	public CodigoClassLoader(List<URL> classpath) {

		super(Thread.currentThread()
			.getContextClassLoader());

		URL[] urls = classpath.toArray(new URL[classpath.size()]);
		this.childClassLoader = new ChildClassLoader(urls, new DetectClass(this.getParent()));
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

		try {
			return this.childClassLoader.findClass(name);
		} catch (ClassNotFoundException e) {
			return super.loadClass(name, resolve);
		}
	}

	private static class ChildClassLoader extends URLClassLoader {
		private DetectClass realParent;

		public ChildClassLoader(URL[] urls, DetectClass realParent) {

			super(urls, null);
			this.realParent = realParent;
		}

		@Override
		public Class<?> findClass(String name) throws ClassNotFoundException {

			try {
				Class<?> loaded = super.findLoadedClass(name);
				if (loaded != null)
					return loaded;
				return super.findClass(name);
			} catch (ClassNotFoundException e) {
				return this.realParent.loadClass(name);
			}
		}
	}

	private static class DetectClass extends ClassLoader {
		public DetectClass(ClassLoader parent) {

			super(parent);
		}

		@Override
		public Class<?> findClass(String name) throws ClassNotFoundException {

			return super.findClass(name);
		}
	}
}