package com.apa.model;

public class MediTestSaveViewDTO {

	private String mediTestName;
	private String testDate;
	private String userName;
	private String mediTestResultContent;
	private String mediTestTotalScore;
	public String getMediTestName() {
		return mediTestName;
	}
	public void setMediTestName(String mediTestName) {
		this.mediTestName = mediTestName;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMediTestResultContent() {
		return mediTestResultContent;
	}
	public void setMediTestResultContent(String mediTestResultContent) {
		this.mediTestResultContent = mediTestResultContent;
	}
	public String getMediTestTotalScore() {
		return mediTestTotalScore;
	}
	public void setMediTestTotalScore(String mediTestTotalScore) {
		this.mediTestTotalScore = mediTestTotalScore;
	}
	
}
