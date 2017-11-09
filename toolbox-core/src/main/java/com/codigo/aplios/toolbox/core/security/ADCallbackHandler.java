package com.codigo.aplios.toolbox.core.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * Copyright Alvin Alexander, http://devdaily.com. This code is shared here under the Attribution 3.0 Unported License.
 * See this URL for details: http://creativecommons.org/licenses/by/3.0/
 *
 * This is an implementation of CallbackHandler to pass credentials to ActiveDirectoryValidator.java. See JAAS
 * documentation for more info.
 */
public class ADCallbackHandler implements CallbackHandler {
	private String ADUserId;
	private char[] ADPassword;

	@Override
	public void handle(Callback[] callbacks) throws java.io.IOException, UnsupportedCallbackException {

		for (Callback callback : callbacks)
			if (callback instanceof NameCallback) {
				NameCallback cb = (NameCallback) callback;
				cb.setName(this.ADUserId);
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback cb = (PasswordCallback) callback;
				cb.setPassword(this.ADPassword);
			} else
				throw new UnsupportedCallbackException(callback);
	}

	public void setUserId(String userid) {

		this.ADUserId = userid;
	}

	public void setPassword(String password) {

		this.ADPassword = new char[password.length()];
		password.getChars(0, this.ADPassword.length, this.ADPassword, 0);
	}

	public static void main(String[] args) throws IOException, UnsupportedCallbackException {

		// Test handler
		ADCallbackHandler ch = new ADCallbackHandler();
		ch.setUserId("test");
		ch.setPassword("test");

		Callback[] callbacks = new Callback[] { new NameCallback("user id:"), new PasswordCallback("password:", true) };

		ch.handle(callbacks);
	}
}
