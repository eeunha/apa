package com.apa.model;

import lombok.Data;

@Data
public class TestDTO {

	private String mediTestSeq;
	private String mediTestSaveSeq;
	private String userSeq;
	private String mediTestTotalScore;
	private String testDate;
	private String mediTestName;
	private String minScore;
	private String maxScore;
	private String mediTestResultContent;
	private String mediTestQuestionContent;
	private String mediTestQuestionNo;

	public String getMediTestSeq() {
		return mediTestSeq;
	}

	public void setMediTestSeq(String mediTestSeq) {
		this.mediTestSeq = mediTestSeq;
	}

	public String getMediTestSaveSeq() {
		return mediTestSaveSeq;
	}

	public void setMediTestSaveSeq(String mediTestSaveSeq) {
		this.mediTestSaveSeq = mediTestSaveSeq;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getMediTestTotalScore() {
		return mediTestTotalScore;
	}

	public void setMediTestTotalScore(String mediTestTotalScore) {
		this.mediTestTotalScore = mediTestTotalScore;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getMediTestName() {
		return mediTestName;
	}

	public void setMediTestName(String mediTestName) {
		this.mediTestName = mediTestName;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getMediTestResultContent() {
		return mediTestResultContent;
	}

	public void setMediTestResultContent(String mediTestResultContent) {
		this.mediTestResultContent = mediTestResultContent;
	}

	public String getMediTestQuestionContent() {
		return mediTestQuestionContent;
	}

	public void setMediTestQuestionContent(String mediTestQuestionContent) {
		this.mediTestQuestionContent = mediTestQuestionContent;
	}

	public String getMediTestQuestionNo() {
		return mediTestQuestionNo;
	}

	public void setMediTestQuestionNo(String mediTestQuestionNo) {
		this.mediTestQuestionNo = mediTestQuestionNo;
	}

}
