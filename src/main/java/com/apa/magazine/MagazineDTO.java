package com.apa.magazine;

public class MagazineDTO {
	
	private String magazineSeq;
	private String magazineTitle;
	private String magazineSubTitle;
	private String magazineContent;
	private String magazineDate;
	private int isnew;
	private int ccnt;
	private int magazineReadcount;
	
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





