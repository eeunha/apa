package com.apa.model;

public class MediCounselingBoxDTO {

	private String mediCounselBoxSeq;
	private String mediCounselQuestionSeq;
	private String departmentName;
	private String counselTitle;
	private String regdate;
	
	
	public String getMediCounselBoxSeq() {
		return mediCounselBoxSeq;
	}
	public void setMediCounselBoxSeq(String mediCounselBoxSeq) {
		this.mediCounselBoxSeq = mediCounselBoxSeq;
	}
	public String getCounselTitle() {
		return counselTitle;
	}
	public void setCounselTitle(String counselTitle) {
		this.counselTitle = counselTitle;
	}
	public String getMediCounselQuestionSeq() {
		return mediCounselQuestionSeq;
	}
	public void setMediCounselQuestionSeq(String mediCounselQuestionSeq) {
		this.mediCounselQuestionSeq = mediCounselQuestionSeq;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

}
