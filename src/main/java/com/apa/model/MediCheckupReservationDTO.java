package com.apa.model;

public class MediCheckupReservationDTO {
	private String mediCheckupSeq;
	private String hospitalName;
	private String reservationDate;
	private String isCheckup;
	public String getMediCheckupSeq() {
		return mediCheckupSeq;
	}
	public void setMediCheckupSeq(String mediCheckupSeq) {
		this.mediCheckupSeq = mediCheckupSeq;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getIsCheckup() {
		return isCheckup;
	}
	public void setIsCheckup(String isCheckup) {
		this.isCheckup = isCheckup;
	}
	
}
