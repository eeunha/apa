package com.apa.model;

public class ReviewInsertInfoDTO {
	
	private String hospitalName;
	private String doctorName;
	private String mediWay;
	private String treatmentDate;
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
	public String getMediWay() {
		return mediWay;
	}
	public void setMediWay(String mediWay) {
		this.mediWay = mediWay;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	
}
