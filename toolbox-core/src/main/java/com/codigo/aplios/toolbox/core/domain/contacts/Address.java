package com.codigo.aplios.toolbox.core.domain.contacts;

// import com.codigo.aplios.toolbox.core.domain.common.Identity;
// import com.codigo.aplios.toolbox.core.domain.locale.Country;
//
// @Entity
// @Table(name = "BLC_ADDRESS")
// @Inheritance(strategy = InheritanceType.JOINED)
// public class Address extends Identity {
//
// private static final long serialVersionUID = -2478551510027360371L;
//
// @Column(name = "FULL_NAME")
// protected String fullName;
//
// @Column(name = "FIRST_NAME")
// protected String firstName;
//
// @Column(name = "MIDDLE_NAME")
// protected String middleName;
//
// @Column(name = "LAST_NAME")
// protected String lastName;
//
// @Column(name = "EMAIL_ADDRESS")
// protected String emailAddress;
//
// @Column(name = "COMPANY_NAME")
// protected String companyName;
//
// @Column(name = "ADDRESS_LINE1")
// protected String addressLine1;
//
// @Column(name = "ADDRESS_LINE2")
// protected String addressLine2;
//
// @Column(name = "ADDRESS_LINE3")
// protected String addressLine3;
//
// @Column(name = "CITY")
// protected String city;
//
// @Column(name = "ISO_COUNTRY_SUB")
// protected String isoCountrySubdivision;
//
// @Column(name = "SUB_STATE_PROV_REG")
// protected String stateProvinceRegion;
//
// @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Country.class)
// @PrimaryKeyJoinColumn(name = "ID")
// protected Country isoCountryAlpha2;
//
// @Column(name = "POSTAL_CODE")
// protected String postalCode;
//
// @Column(name = "ZIP_FOUR")
// protected String zipFour;
//
// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
// @PrimaryKeyJoinColumn(name = "ID")
// protected Phone phonePrimary;
//
// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
// @PrimaryKeyJoinColumn(name = "ID")
// protected Phone phoneSecondary;
//
// @ManyToOne(targetEntity = Phone.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
// @PrimaryKeyJoinColumn(name = "ID")
// protected Phone phoneFax;
//
// @Column(name = "IS_DEFAULT")
// protected boolean isDefault = false;
//
// @Column(name = "IS_ACTIVE")
// protected boolean isActive = true;
//
// @Column(name = "IS_BUSINESS")
// protected boolean isBusiness = false;
//
// // @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity =
// // CountryImpl.class)
// // @JoinColumn(name = "COUNTRY")
// // @Index(name="ADDRESS_COUNTRY_INDEX", columnNames={"COUNTRY"})
// // @AdminPresentation(friendlyName = "CountryImpl_Country", order=100, group =
// // "AddressImpl_Address", prominent = true, translatable = true)
// // @Deprecated
// // protected Country country;
//
// /**
// * Intended to be used to differentiate whether this address is a physical address (e.g. street/house) or a mailing
// * address (e.g. P.O. Box etc..)
// */
// @Column(name = "IS_STREET")
// protected boolean isStreet = false;
//
// /**
// * Intended to be used to differentiate whether this address is a physical address (e.g. street/house) or a mailing
// * address (e.g. P.O. Box etc..)
// */
// @Column(name = "IS_MAILING")
// protected boolean isMailing = false;
//
// /**
// * This is intented to be used for address verification integrations and should not be modifiable in the admin
// */
// @Column(name = "TOKENIZED_ADDRESS")
// protected String tokenizedAddress;
//
// /**
// * This is intented to be used for address verification integrations and should not be modifiable in the admin
// */
// @Column(name = "STANDARDIZED")
// protected Boolean standardized = Boolean.FALSE;
//
// /**
// * This is intented to be used for address verification integrations and should not be modifiable in the admin
// */
// @Column(name = "VERIFICATION_LEVEL")
// protected String verificationLevel;
//
// @Column(name = "PRIMARY_PHONE")
// @Deprecated
// protected String primaryPhone;
//
// @Column(name = "SECONDARY_PHONE")
// @Deprecated
// protected String secondaryPhone;
//
// @Column(name = "FAX")
// @Deprecated
// protected String fax;
//
// public String getMiddleName() {
//
// return this.middleName;
// }
//
// public void setMiddleName(String middleName) {
//
// this.middleName = middleName;
// }
//
// }
