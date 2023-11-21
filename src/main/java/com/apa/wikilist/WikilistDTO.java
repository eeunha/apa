package com.apa.wikilist;

public class WikilistDTO {
	
	/**
	 * @author 안대명
	 * 위키 리스트의 정보를 담는 DTO (Data Transfer Object) 클래스입니다.
	 */
	
	private String wikiSeq;
	private String diseaseName;
	private String diseaseExplanation; 
	private String diseaseCause;
	private String wikiSymptom;
	private String diagnosis;
	private String care;
	private int isnew;
	private int ccnt;
	private int wikiReadcount;

	public String getWikiSeq() {
		return wikiSeq;
	}
	public void setWikiSeq(String wikiSeq) {
		this.wikiSeq = wikiSeq;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDiseaseExplanation() {
		return diseaseExplanation;
	}
	public void setDiseaseExplanation(String diseaseExplanation) {
		this.diseaseExplanation = diseaseExplanation;
	}
	public String getDiseaseCause() {
		return diseaseCause;
	}
	public void setDiseaseCause(String diseaseCause) {
		this.diseaseCause = diseaseCause;
	}
	public String getWikiSymptom() {
		return wikiSymptom;
	}
	public void setWikiSymptom(String wikiSymptom) {
		this.wikiSymptom = wikiSymptom;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getCare() {
		return care;
	}
	public void setCare(String care) {
		this.care = care;
	}
	public int getIsnew() {
		return isnew;
	}
	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}
	public int getCcnt() {
		return ccnt;
	}
	public void setCcnt(int ccnt) {
		this.ccnt = ccnt;
	}
	public int getWikiReadcount() {
		return wikiReadcount;
	}
	public void setWikiReadcount(int wikiReadcount) {
		this.wikiReadcount = wikiReadcount;
	}
	
	
}





