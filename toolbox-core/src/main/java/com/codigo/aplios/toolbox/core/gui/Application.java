package com.codigo.aplios.toolbox.core.gui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JFrame;

/**
 * @author andrzej.radziszewski
 *
 *         Code file : Application.java Create date: 08.05.2017
 */
public class Application {
	private final ArrayList<ApplicationListener> applicationListenerList = new ArrayList<>();
	private final EventQueue eventQueue = Toolkit.getDefaultToolkit()
		.getSystemEventQueue();

	public static Application run(JFrame form) {

		return new Application();
	}

	public void restart() {

	}

	public void processEvents() {

	}

	public synchronized void registerApplicationListener(ApplicationListener listener) {

		if (!this.applicationListenerList.contains(listener))
			this.applicationListenerList.add(listener);
	}

	public synchronized void unregisterApplicationListener(ApplicationListener listener) {

		if (!this.applicationListenerList.contains(listener))
			this.applicationListenerList.remove(listener);
	}

	public boolean allowQuit() {

		return false;
	}

	public Path startupPath() throws URISyntaxException {

		// sciezka bez nazwy pliku
		return Paths.get(Application.class.getProtectionDomain()
			.getCodeSource()
			.getLocation()
			.toURI());
	}

	public Path executablePath() throws URISyntaxException {

		// sciezka z nazwą pliku
		return Paths.get(Application.class.getProtectionDomain()
			.getCodeSource()
			.getLocation()
			.toURI());
	}

	public Frame[] openForms() {

		return Frame.getFrames();
	}

}

class ApplicationEvent extends EventObject {

	/**
	 *
	 */
	private static final long serialVersionUID = -1174049357811672589L;

	/**
	 * @param source
	 */
	public ApplicationEvent(Object source) {

		super(source);
		// TODO Auto-generated constructor stub
	}

}

class ThreadExceptionEvent extends EventObject {

	/**
	 * @param source
	 */
	public ThreadExceptionEvent(Object source) {

		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -2993906135374930720L;

}

interface IApplicationListener {
	void applicationLoad(ApplicationEvent appEvent);

	void applicationInit(ApplicationEvent appEvent);

	void applicationExit(ApplicationEvent appEvent);

	void threadException(ThreadExceptionEvent thrEvent);

	void threadExit(ThreadExceptionEvent thrEvent);

	void applicationIdle(ApplicationEvent appEvent);
}

class ApplicationListener implements IApplicationListener {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#applicationLoad(com.codigo.aplios.explorer.app.
	 * ApplicationEvent)
	 */
	@Override
	public void applicationLoad(ApplicationEvent appEvent) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#applicationInit(com.codigo.aplios.explorer.app.
	 * ApplicationEvent)
	 */
	@Override
	public void applicationInit(ApplicationEvent appEvent) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#applicationExit(com.codigo.aplios.explorer.app.
	 * ApplicationEvent)
	 */
	@Override
	public void applicationExit(ApplicationEvent appEvent) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#threadException(com.codigo.aplios.explorer.app.
	 * ThreadExceptionEvent)
	 */
	@Override
	public void threadException(ThreadExceptionEvent thrEvent) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#threadExit(com.codigo.aplios.explorer.app.
	 * ThreadExceptionEvent)
	 */
	@Override
	public void threadExit(ThreadExceptionEvent thrEvent) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.explorer.app.IApplicationListener#applicationIdle(com.codigo.aplios.explorer.app.
	 * ApplicationEvent)
	 */
	@Override
	public void applicationIdle(ApplicationEvent appEvent) {
		// TODO Auto-generated method stub

	}
}