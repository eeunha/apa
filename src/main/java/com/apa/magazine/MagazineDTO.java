package com.apa.magazine;

/**
 * @author 안대명
 * 
 * Magazine 데이터를 저장하는 DTO (Data Transfer Object) 클래스입니다.
 */
public class MagazineDTO {
	
	/** 매거진의 시퀀스 번호 */
	private String magazineSeq;

	/** 매거진 제목 */
	private String magazineTitle;

	/** 매거진 부제목 */
	private String magazineSubTitle;

	/** 매거진 내용 */
	private String magazineContent;

	/** 매거진 작성일 */
	private String magazineDate;

	/** 새로운 항목 여부 */
	private int isnew;

	/** 조회수 */
	private int ccnt;

	/** 매거진 조회수 */
	private int magazineReadcount;

	/** 관리자 ID */
	private String adminId;

	public String getMagazineSeq() {
		return magazineSeq;
	}
	public void setMagazineSeq(String magazineSeq) {
		this.magazineSeq = magazineSeq;
	}
	public String getMagazineTitle() {
		return magazineTitle;
	}
	public void setMagazineTitle(String magazineTitle) {
		this.magazineTitle = magazineTitle;
	}
	public String getMagazineSubTitle() {
		return magazineSubTitle;
	}
	public void setMagazineSubTitle(String magazineSubTitle) {
		this.magazineSubTitle = magazineSubTitle;
	}
	public String getMagazineContent() {
		return magazineContent;
	}
	public void setMagazineContent(String magazineContent) {
		this.magazineContent = magazineContent;
	}
	public String getMagazineDate() {
		return magazineDate;
	}
	public void setMagazineDate(String magazineDate) {
		this.magazineDate = magazineDate;
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

	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public int getMagazineReadcount() {
		return magazineReadcount;
	}
	public void setMagazineReadcount(int magazineReadcount) {
		this.magazineReadcount = magazineReadcount;
	}
	
}





