package com.apa.model;

public class MediCounselQuestionDTO {
	
	private String mediCounselQuestionSeq;
	private String mediCounselAnswerSeq;
	private String departmentName;
	private String counselTitle;
	private String regdate;
	
	public String getMediCounselAnswerSeq() {
		return mediCounselAnswerSeq;
	}
	public void setMediCounselAnswerSeq(String mediCounselAnswerSeq) {
		this.mediCounselAnswerSeq = mediCounselAnswerSeq;
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
	public String getCounselTitle() {
		return counselTitle;
	}
	public void setCounselTitle(String counselTitle) {
		this.counselTitle = counselTitle;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MediCounselQuestionDTO [mediCounselQuestionSeq=" + mediCounselQuestionSeq + ", departmentName="
				+ departmentName + ", counselTitle=" + counselTitle + ", regdate=" + regdate + "]";
	}
	
	
}
