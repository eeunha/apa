package com.apa.model;

public class RegisterDTO {
	private String hospitalName;
	private String doctorName;
	private String treatmentDate;
	private String symptom;
	private String mediWay;
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
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getMediWay() {
		return mediWay;
	}
	public void setMediWay(String mediWay) {
		this.mediWay = mediWay;
	}
	
}
