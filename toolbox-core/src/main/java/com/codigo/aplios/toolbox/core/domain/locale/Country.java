package com.codigo.aplios.toolbox.core.domain.locale;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;

import com.codigo.aplios.toolbox.core.data.repository.listener.HistoryEventListener;

// wzorzec temporal plus jpa
// http://www.javaexpress.pl/article/show/Wzorce_projektowe_Temporal_Object?printable=true

// opisanie alternatywy do klonowania
// https://stormit.pl/klonowanie/
// http://www.rafaljankowski.pl/daj-sie-poznac/spring-security-podstawy/

@Entity
@Table(name = "DIC_COUNTRY")
@EntityListeners(HistoryEventListener.class)
public class Country extends Directory {

	private static final long serialVersionUID = -4524030059918299014L;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "TWOLETTERISOCODE", nullable = false, unique = true, length = 2)
	private String twoLetterIsoCode;

	@Column(name = "THREELETTERISOCODE", nullable = true, length = 3)
	private String threLetterIsoCode;

	@Column(name = "NUMERICISOCODE", unique = true, nullable = false, length = 3)
	private String numericIsoCode;

	@Column(name = "SUBJECT_TO_VAT")
	private boolean subjectToVal;

	@Column(name = "LIMITEDTOSTORES")
	private boolean limitedToStores;

	@Column(name = "ALLOWSBILLING")
	private boolean allowsBilling;

	@Column(name = "ALLOWSSHIPPING")
	private boolean allowsShipping;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "PICTURE")
	private byte[] picture;

	@Valid
	@OneToMany(cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE,
		CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "country")
	@PrimaryKeyJoinColumn(name = "ID")
	private Set<StateProvince> stateProvinces = new HashSet<>();

	// Przenieść do własciwości
	// @Column(name = "MEMBEROFEU", nullable = true)
	// private boolean memberOfEu;
	//
	// @Column(name = "EUROZONE", nullable = true)
	// private boolean euroZone;

	public void setStateProvince(Collection<StateProvince> stateProvinces) {

		this.stateProvinces = stateProvinces.stream()
			.collect(Collectors.toSet());
	}

	public Set<StateProvince> getStateProvince() {

		return this.stateProvinces;
	}

	public byte[] getPicture() {

		return this.picture;
	}

	public void setPicture(byte[] picture) {

		this.picture = picture;
	}

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getTwoLetterIsoCode() {

		return this.twoLetterIsoCode;
	}

	public void setTwoLetterIsoCode(String twoLetterIsoCode) {

		this.twoLetterIsoCode = twoLetterIsoCode;
	}

	public String getThreLetterIsoCode() {

		return this.threLetterIsoCode;
	}

	public void setThreLetterIsoCode(String threLetterIsoCode) {

		this.threLetterIsoCode = threLetterIsoCode;
	}

	public String getNumericIsoCode() {

		return this.numericIsoCode;
	}

	public void setNumericIsoCode(String numericIsoCode) {

		this.numericIsoCode = numericIsoCode;
	}

	public boolean isSubjectToVal() {

		return this.subjectToVal;
	}

	public void setSubjectToVal(boolean subjectToVal) {

		this.subjectToVal = subjectToVal;
	}

	public boolean isLimitedToStores() {

		return this.limitedToStores;
	}

	public void setLimitedToStores(boolean limitedToStores) {

		this.limitedToStores = limitedToStores;
	}

	public boolean isAllowsBilling() {

		return this.allowsBilling;
	}

	public void setAllowsBilling(boolean allowsBilling) {

		this.allowsBilling = allowsBilling;
	}

	public boolean isAllowsShipping() {

		return this.allowsShipping;
	}

	public void setAllowsShipping(boolean allowsShipping) {

		this.allowsShipping = allowsShipping;
	}

	@Override
	public String toString() {

		return "Country=>\n" + "id" + ":" + this.getId() + ";\n" + "name" + ":" + this.getName() + ";\n"
				+ "twoletteriscode" + ":" + this.getTwoLetterIsoCode() + ";\n";
	}

}
