package com.apa.model;

public class AdminEntryDTO {
	
	private String entrySeq;
	private String entryType;
	private String hospitalId;
	private String pharmacyId;
	private String status;

	private String hospitalName;
	private String pharmacyName;
	
	private String hospitalregdate;
	private String pharmacyRegdate;

	private String entryRegdate;
	private String entryDate;
	
	public String getEntrySeq() {
		return entrySeq;
	}
	public void setEntrySeq(String entrySeq) {
		this.entrySeq = entrySeq;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getHospitalregdate() {
		return hospitalregdate;
	}
	public void setHospitalregdate(String hospitalregdate) {
		this.hospitalregdate = hospitalregdate;
	}
	public String getPharmacyRegdate() {
		return pharmacyRegdate;
	}
	public void setPharmacyRegdate(String pharmacyRegdate) {
		this.pharmacyRegdate = pharmacyRegdate;
	}
	public String getEntryRegdate() {
		return entryRegdate;
	}
	public void setEntryRegdate(String entryRegdate) {
		this.entryRegdate = entryRegdate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	
}
