package com.codigo.aplios.toolbox.core.domain.locale;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.toolbox.core.data.repository.Auditable;
import com.codigo.aplios.toolbox.core.data.repository.audit.AuditListener1;
import com.codigo.aplios.toolbox.core.domain.audit.AuditSection;
import com.codigo.aplios.toolbox.core.domain.common.Identity;

@Entity
@Table(name = "BLC_CURRENCY") // dodać implementacje interfejsu dto, tabel słownikowych
@EntityListeners(value = AuditListener1.class)
public class Currency extends Identity implements Auditable {
	private static final long serialVersionUID = -7381025110902179653L;

	@Embedded
	private AuditSection auditSection = new AuditSection();

	@Column(name = "NAME")
	private String name;

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	@Column(name = "RATE")
	private double rate;

	@Column(name = "DISPLAY_LOCALE")
	private String displayLocale;

	@Column(name = "CUSTOM_FORMATTING")
	private String customFormatting;

	// Gets or sets a value indicating whether the entity is limited/restricted to certain stores
	@Column(name = "LIMITEDTOSTORES")
	private boolean limitedToStores;

	// Gets or sets a value indicating whether the entity is published
	@Column(name = "PUBLISHED")
	private boolean published;

	// Gets or sets the display order
	@Column(name = "DISPLAYORDER")
	private int displayOrder;

	// Gets or sets the timestamp of instance creation
	@Column(name = "CREATEDONUTC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOnUtc;

	// Gets or sets the date and time of instance update
	@Column(name = "UPDATEDONUTC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date UpdatedOnUtc;

	// Gets or sets the (comma separated) list of domain endings (e.g. country code top-level
	// domains) to which this currency is the default one
	@Column(name = "DOMAINENDINGS")
	private String domainEndings;

	@Column(name = "SEQUENCE")
	private String sequence;

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getCurrencyCode() {

		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {

		this.currencyCode = currencyCode;
	}

	public double getRate() {

		return this.rate;
	}

	public void setRate(double rate) {

		this.rate = rate;
	}

	public String getDisplayLocale() {

		return this.displayLocale;
	}

	public void setDisplayLocale(String displayLocale) {

		this.displayLocale = displayLocale;
	}

	public String getCustomFormatting() {

		return this.customFormatting;
	}

	public void setCustomFormatting(String customFormatting) {

		this.customFormatting = customFormatting;
	}

	public boolean isLimitedToStores() {

		return this.limitedToStores;
	}

	public void setLimitedToStores(boolean limitedToStores) {

		this.limitedToStores = limitedToStores;
	}

	public boolean isPublished() {

		return this.published;
	}

	public void setPublished(boolean published) {

		this.published = published;
	}

	public int getDisplayOrder() {

		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {

		this.displayOrder = displayOrder;
	}

	public Date getCreatedOnUtc() {

		return this.createdOnUtc;
	}

	public void setCreatedOnUtc(Date createdOnUtc) {

		this.createdOnUtc = createdOnUtc;
	}

	public Date getUpdatedOnUtc() {

		return this.UpdatedOnUtc;
	}

	public void setUpdatedOnUtc(Date updatedOnUtc) {

		this.UpdatedOnUtc = updatedOnUtc;
	}

	public String getDomainEndings() {

		return this.domainEndings;
	}

	public void setDomainEndings(String domainEndings) {

		this.domainEndings = domainEndings;
	}

	// @Override
	public AuditSection getAuditSection() {

		return this.auditSection;
	}

	// @Override
	public void setAuditSection(AuditSection auditSection) {

		this.auditSection = auditSection;
	}

	public Optional<String> getSequence() {

		return Optional.ofNullable(this.sequence);
	}

	public void setSequence(String sequence) {

		this.sequence = sequence;
	}

	@Override
	public String toString() {

		return this.currencyCode + ';' + this.name + ';' + this.displayLocale + ';' + this.createdOnUtc + ';'
				+ this.displayOrder + ';' + this.published;
	}
}
