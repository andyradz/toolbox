package com.codigo.aplios.toolbox.core.domain.evidence;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.codigo.aplios.toolbox.core.data.repository.convert.CryptoConverter;

@Embeddable
public class CreditCard {

	@Convert(converter = CryptoConverter.class)
	@Column(name = "ccNumber")
	private String ccNumber;

	public String getCcNumber() {

		return this.ccNumber;
	}

	public void setCcNumber(String ccNumber) {

		this.ccNumber = ccNumber;
	}

}