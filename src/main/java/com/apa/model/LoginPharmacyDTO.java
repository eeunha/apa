package com.apa.model;

import lombok.Data;

@Data
public class LoginPharmacyDTO {
	private String pharmacyId;
	private String pharmacyPw;
	private String pharmacyName;
	private String pharmacySsn;
	private String pharmacyAddress;
	private String pharmacyEmail;
	private String pharmacyTel;
	private String isDispense;
	private String isPharmacy;
	private String regDate;
	private String isPharmacyUnRegister;

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getPharmacyPw() {
		return pharmacyPw;
	}

	public void setPharmacyPw(String pharmacyPw) {
		this.pharmacyPw = pharmacyPw;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getPharmacySsn() {
		return pharmacySsn;
	}

	public void setPharmacySsn(String pharmacySsn) {
		this.pharmacySsn = pharmacySsn;
	}

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public String getPharmacyEmail() {
		return pharmacyEmail;
	}

	public void setPharmacyEmail(String pharmacyEmail) {
		this.pharmacyEmail = pharmacyEmail;
	}

	public String getPharmacyTel() {
		return pharmacyTel;
	}

	public void setPharmacyTel(String pharmacyTel) {
		this.pharmacyTel = pharmacyTel;
	}

	public String getIsDispense() {
		return isDispense;
	}

	public void setIsDispense(String isDispense) {
		this.isDispense = isDispense;
	}

	public String getIsPharmacy() {
		return isPharmacy;
	}

	public void setIsPharmacy(String isPharmacy) {
		this.isPharmacy = isPharmacy;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getIsPharmacyUnRegister() {
		return isPharmacyUnRegister;
	}

	public void setIsPharmacyUnRegister(String isPharmacyUnRegister) {
		this.isPharmacyUnRegister = isPharmacyUnRegister;
	}

}
