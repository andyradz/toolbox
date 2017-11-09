package com.codigo.aplios.toolbox.core.sandbox;

import java.security.AccessControlContext;
import java.security.AllPermission;
import java.security.Permission;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

// http://www.informit.com/articles/article.aspx?p=1187967&seqNum=3

/**
 * This class establishes a security manager that confines the permissions for code executed through specific classes,
 * which may be specified by class, class name and/or class loader.
 * <p>
 * To 'execute through a class' means that the execution stack includes the class. E.g., if a method of class {@code A}
 * invokes a method of class {@code B}, which then invokes a method of class {@code C}, and all three classes were
 * previously {@link #confine(Class, Permissions) confined}, then for all actions that are executed by class {@code C}
 * the <i>intersection</i> of the three {@link Permissions} apply.
 * <p>
 * Once the permissions for a class, class name or class loader are confined, they cannot be changed; this prevents any
 * attempts (e.g. of the confined class itself) to release the confinement.
 * <p>
 * Code example:
 *
 * <pre>
 * Runnable unprivileged = new Runnable() {
 * 	public void run() {
 *
 * 		System.getProperty("user.dir");
 * 	}
 * };
 *
 * // Run without confinement.
 * unprivileged.run(); // Works fine.
 *
 * // Set the most strict permissions.
 * Sandbox.confine(unprivileged.getClass(), new Permissions());
 * unprivileged.run(); // Throws a SecurityException.
 *
 * // Attempt to change the permissions.
 * {
 * 	Permissions permissions = new Permissions();
 * 	permissions.add(new AllPermission());
 * 	Sandbox.confine(unprivileged.getClass(), permissions); // Throws a SecurityException.
 * }
 * unprivileged.run();
 * </pre>
 */
public final class Sandbox {

	public static void main(String[] args) {

		Runnable unprivileged = () -> System.getProperty("user.dir");

		// Run without confinement.
		unprivileged.run(); // Works fine.

		// Set the most strict permissions.
		// Sandbox.confine(unprivileged.getClass(), new Permissions());
		// unprivileged.run(); // Throws a SecurityException.

		// Attempt to change the permissions.
		{
			Permissions permissions = new Permissions();
			permissions.add(new Permission("") {

				@Override
				public boolean implies(Permission permission) {

					// TODO Auto-generated method stub
					/* RETURN */
					return false;
				}

				@Override
				public int hashCode() {

					// TODO Auto-generated method stub
					/* RETURN */
					return 0;
				}

				@Override
				public String getActions() {

					// TODO Auto-generated method stub
					/* RETURN */
					return null;
				}

				@Override
				public boolean equals(Object obj) {

					// TODO Auto-generated method stub
					/* RETURN */
					return false;
				}
			});
			permissions.add(new AllPermission());
			Sandbox.confine(unprivileged.getClass(), permissions); // Throws a SecurityException.
		}
		unprivileged.run();
	}

	// -----------------------------------------------------------------------------------------------------------------

	private Sandbox() {

	}

	private static final Map<Class<?>, AccessControlContext> CHECKED_CLASSES =
			Collections.synchronizedMap(new WeakHashMap<Class<?>, AccessControlContext>());

	private static final Map<String, AccessControlContext> CHECKED_CLASS_NAMES =
			Collections.synchronizedMap(new HashMap<String, AccessControlContext>());

	private static final Map<ClassLoader, AccessControlContext> CHECKED_CLASS_LOADERS =
			Collections.synchronizedMap(new WeakHashMap<ClassLoader, AccessControlContext>());

	// -----------------------------------------------------------------------------------------------------------------

	static {

		// Install our custom security manager.
		if (System.getSecurityManager() != null)
			throw new ExceptionInInitializerError("There's already a security manager set");
		System.setSecurityManager(new SecurityManager() {

			@Override
			public void checkPermission(Permission perm) {

				assert perm != null;

				for (Class<?> clasS : this.getClassContext()) {

					// Check if an ACC was set for the class.
					{
						AccessControlContext acc = Sandbox.CHECKED_CLASSES.get(clasS);
						if (acc != null)
							acc.checkPermission(perm);
					}

					// Check if an ACC was set for the class name.
					{
						AccessControlContext acc = Sandbox.CHECKED_CLASS_NAMES.get(clasS.getName());
						if (acc != null)
							acc.checkPermission(perm);
					}

					// Check if an ACC was set for the class loader.
					{
						AccessControlContext acc = Sandbox.CHECKED_CLASS_LOADERS.get(clasS.getClassLoader());
						if (acc != null)
							acc.checkPermission(perm);
					}
				}
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * All future actions that are executed through the given {@code clasS} will be checked against the given {@code
	 * accessControlContext}.
	 *
	 * @throws SecurityException Permissions are already confined for the {@code clasS}
	 */
	public static void confine(Class<?> clasS, AccessControlContext accessControlContext) {

		if (Sandbox.CHECKED_CLASSES.containsKey(clasS))
			throw new SecurityException("Attempt to change the access control context for '" + clasS + "'");

		Sandbox.CHECKED_CLASSES.put(clasS, accessControlContext);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * All future actions that are executed through the given {@code clasS} will be checked against the given {@code
	 * protectionDomain}.
	 *
	 * @throws SecurityException Permissions are already confined for the {@code clasS}
	 */
	public static void confine(Class<?> clasS, ProtectionDomain protectionDomain) {

		/* BODY */
		Sandbox.confine(clasS, new AccessControlContext(new ProtectionDomain[] { protectionDomain }));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * All future actions that are executed through the given {@code clasS} will be checked against the given {@code
	 * permissions}.
	 *
	 * @throws SecurityException Permissions are already confined for the {@code clasS}
	 */
	public static void confine(Class<?> clasS, Permissions permissions) {

		Sandbox.confine(clasS, new ProtectionDomain(null, permissions));
	}

	// Code for 'CHECKED_CLASS_NAMES' and 'CHECKED_CLASS_LOADERS' omitted here.

}