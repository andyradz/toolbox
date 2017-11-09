package com.codigo.aplios.toolbox.core.domain.common;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public final class PrimaryKey implements Serializable {

	// -----------------------------------------------------------------------------------------------------------------

	private static final long serialVersionUID = -1528223225651711166L;
	private final UUID keyGuid;
	private final String keyName;
	private final Long keyId;

	// -----------------------------------------------------------------------------------------------------------------

	public PrimaryKey() {

		this("zero", 1L);
	}

	public PrimaryKey(String keyName, Long keyId) {

		this.keyGuid = UUID.randomUUID();
		this.keyName = keyName;
		this.keyId = keyId;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getKeyName() {

		return this.keyName;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Long getKeyId() {

		return this.keyId;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public UUID getKeyGuid() {

		return this.keyGuid;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public int hashCode() {

		return Objects.hash(this.keyName, this.keyGuid, this.keyId);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean equals(Object instance) {

		if (this == instance)
			return true;
		if ((instance == null) || (this.getClass() != instance.getClass()))
			return false;
		final PrimaryKey primaryKey = PrimaryKey.class.cast(instance);

		return (this.keyName.equals(primaryKey) && (this.keyId == primaryKey.keyId)
				&& this.keyGuid.equals(primaryKey.keyGuid));
	}

	// -----------------------------------------------------------------------------------------------------------------
}
