package com.apa.model;

import lombok.Data;

@Data
public class LoginHospitalDTO {

	private String hospitalId;
	private String hospitalPw;
	private String hospitalName;
	private String hospitalSsn;
	private String hospitalAddress;
	private String hospitalEmail;
	private String hospitalTel;
	private String departmentSeq;
	private String isHospital;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalPw() {
		return hospitalPw;
	}

	public void setHospitalPw(String hospitalPw) {
		this.hospitalPw = hospitalPw;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalSsn() {
		return hospitalSsn;
	}

	public void setHospitalSsn(String hospitalSsn) {
		this.hospitalSsn = hospitalSsn;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public String getHospitalTel() {
		return hospitalTel;
	}

	public void setHospitalTel(String hospitalTel) {
		this.hospitalTel = hospitalTel;
	}

	public String getDepartmentSeq() {
		return departmentSeq;
	}

	public void setDepartmentSeq(String departmentSeq) {
		this.departmentSeq = departmentSeq;
	}

	public String getIsHospital() {
		return isHospital;
	}

	public void setIsHospital(String isHospital) {
		this.isHospital = isHospital;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getIsHospitalUnRegister() {
		return isHospitalUnRegister;
	}

	public void setIsHospitalUnRegister(String isHospitalUnRegister) {
		this.isHospitalUnRegister = isHospitalUnRegister;
	}

	private String regDate;
	private String isHospitalUnRegister;
}
