package com.apa.model;

public class MediHistoryViewDTO {
	private String mediHistorySeq;
	private String treatmentDate;
	private String userName;
	private String mediName;
	private String mediCode;
	private String hospitalName;
	public String getMediHistorySeq() {
		return mediHistorySeq;
	}
	public void setMediHistorySeq(String mediHistorySeq) {
		this.mediHistorySeq = mediHistorySeq;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	public String getMediCode() {
		return mediCode;
	}
	public void setMediCode(String mediCode) {
		this.mediCode = mediCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
}
