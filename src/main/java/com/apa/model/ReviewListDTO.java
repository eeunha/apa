package com.apa.model;

public class ReviewListDTO {

	private String reviewSeq;
	private String hospitalName;
	private String reviewContent;
	private String treatmentDate;
	private String isReviewDelete;
	
	public String getIsReviewDelete() {
		return isReviewDelete;
	}
	public void setIsReviewDelete(String isReviewDelete) {
		this.isReviewDelete = isReviewDelete;
	}
	public String getReviewSeq() {
		return reviewSeq;
	}
	public void setReviewSeq(String reviewSeq) {
		this.reviewSeq = reviewSeq;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	
}
