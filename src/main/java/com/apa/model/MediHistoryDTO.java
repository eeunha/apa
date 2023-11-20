package com.apa.model;

public class MediHistoryDTO {
	private String mediHistorySeq;
	private String hospitalName;
	private String doctorName;
	private String treatmentDate;
	private String mediName;
	
	private String childSeq;
	
	private String reviewSeq;
	private String reqDocumentSeq;
	
	
	
	public String getReqDocumentSeq() {
		return reqDocumentSeq;
	}
	public void setReqDocumentSeq(String reqDocumentSeq) {
		this.reqDocumentSeq = reqDocumentSeq;
	}
	public String getReviewSeq() {
		return reviewSeq;
	}
	public void setReviewSeq(String reviewSeq) {
		this.reviewSeq = reviewSeq;
	}
	public String getChildSeq() {
		return childSeq;
	}
	public void setChildSeq(String childSeq) {
		this.childSeq = childSeq;
	}
	public String getMediHistorySeq() {
		return mediHistorySeq;
	}
	public void setMediHistorySeq(String mediHistorySeq) {
		this.mediHistorySeq = mediHistorySeq;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	public String getMediName() {
		return mediName;
	}
	public void setMediName(String mediName) {
		this.mediName = mediName;
	}
	
}
