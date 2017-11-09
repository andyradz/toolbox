package com.codigo.aplios.toolbox.core.domain.contacts;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_PHONE")
public class Phone extends Identity {
	private static final long serialVersionUID = 6282306950384374334L;

	@Column(name = "COUNTRY_CODE")
	protected String countryCode;

	@Column(name = "PHONE_NUMBER", nullable = false)
	protected String phoneNumber;

	@Column(name = "EXTENSION")
	protected String extension;

	@Column(name = "IS_DEFAULT")
	protected boolean isDefault = false;

	@Enumerated(EnumType.STRING)
	@Column(name = "PHONE_TYPE")
	protected PhoneType phoneType;

	@Column(name = "IS_ACTIVE")
	protected boolean isActive = true;

	public String getCountryCode() {

		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {

		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {

		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public String getExtension() {

		return this.extension;
	}

	public void setExtension(String extension) {

		this.extension = extension;
	}

	public boolean isDefault() {

		return this.isDefault;
	}

	public void setDefault(boolean isDefault) {

		this.isDefault = isDefault;
	}

	public boolean isActive() {

		return this.isActive;
	}

	public void setActive(boolean isActive) {

		this.isActive = isActive;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.isActive, this.isDefault, this.countryCode, this.phoneNumber, this.extension);
	}
}
