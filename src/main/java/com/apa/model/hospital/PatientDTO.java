package com.apa.model.hospital;

public class PatientDTO {
	private String patientSeq;
	private String patientName;
	private String patientSSN;
	private String patientTel;
	private String patientAddress;
	private String patientEmail;
	private String patientStatus;
	private String memo;

	public String getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}

	public String getPatientSeq() {
		return patientSeq;
	}

	public void setPatientSeq(String patientSeq) {
		this.patientSeq = patientSeq;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSSN() {
		return patientSSN;
	}

	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}

	public String getPatientTel() {
		return patientTel;
	}

	public void setPatientTel(String patientTel) {
		this.patientTel = patientTel;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
