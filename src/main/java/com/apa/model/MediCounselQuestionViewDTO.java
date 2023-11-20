package com.apa.model;

public class MediCounselQuestionViewDTO {

	private String mediCounselQuestionSeq;
	private String userId;
	private String departmentName;
	private String counselTitle;
	private String counselContent;
	private String counselAnswerContent;
	private String doctorName;
	private String hospitalName;
	private String regdate;
	private String answerTime;
	public String getMediCounselQuestionSeq() {
		return mediCounselQuestionSeq;
	}
	public void setMediCounselQuestionSeq(String mediCounselQuestionSeq) {
		this.mediCounselQuestionSeq = mediCounselQuestionSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCounselTitle() {
		return counselTitle;
	}
	public void setCounselTitle(String counselTitle) {
		this.counselTitle = counselTitle;
	}
	public String getCounselContent() {
		return counselContent;
	}
	public void setCounselContent(String counselContent) {
		this.counselContent = counselContent;
	}
	public String getCounselAnswerContent() {
		return counselAnswerContent;
	}
	public void setCounselAnswerContent(String counselAnswerContent) {
		this.counselAnswerContent = counselAnswerContent;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

}
