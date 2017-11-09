package com.codigo.aplios.toolbox.core.domain.domain;

import java.util.regex.Pattern;

public class DomainHelpers {

	private static Pattern pDomainName;

	private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

	static {
		DomainHelpers.pDomainName = Pattern.compile(DomainHelpers.DOMAIN_NAME_PATTERN);
	}

	// is this a valid domain name?
	public static boolean isValidDomainName(String domainName) {

		return DomainHelpers.pDomainName.matcher(domainName)
			.find();
	}
}
