package com.codigo.aplios.toolbox.core.domain.evidence.pesel;

import com.codigo.aplios.toolbox.core.identity.PeselIdentity;

public class PeselData {

	private PeselIdentity pesel;

	public void setPesel(PeselIdentity pesel) {

		this.pesel = pesel;
	}

	public PeselIdentity getPesel() {

		return this.pesel;
	}
}
