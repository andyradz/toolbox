package com.codigo.aplios.toolbox.core.domain.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Table(name = "BLC_ZIP_CODE")
public class ZipCode extends Identity implements Serializable {
	private static final long serialVersionUID = 4072853729370151441L;

	@Column(name = "ZIPCODE", insertable = false, updatable = false)
	private String zipCode;

	@Column(name = "ZIPSTATE", insertable = false, updatable = false)
	private String zipState;

	@Column(name = "ZIPCITY")
	private String zipCity;

	// długośc geograficzna
	@Column(name = "ZIP_LONGITUDE")
	@Index(name = "ZIPCODE_LONGITUDE_INDEX", columnNames = { "ZIP_LONGITUDE" })
	private double zipLongitude;

	// szerokość geograficzna
	@Column(name = "ZIP_LATITUDE")
	@Index(name = "ZIPCODE_LATITUDE_INDEX", columnNames = { "ZIP_LATITUDE" })
	private double zipLatitude;

	public String getZipCode() {

		return this.zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

	public String getZipState() {

		return this.zipState;
	}

	public void setZipState(String zipState) {

		this.zipState = zipState;
	}

	public String getZipCity() {

		return this.zipCity;
	}

	public void setZipCity(String zipCity) {

		this.zipCity = zipCity;
	}

	public double getZipLongitude() {

		return this.zipLongitude;
	}

	public void setZipLongitude(double zipLongitude) {

		this.zipLongitude = zipLongitude;
	}

	public double getZipLatitude() {

		return this.zipLatitude;
	}

	public void setZipLatitude(double zipLatitude) {

		this.zipLatitude = zipLatitude;
	}
}
