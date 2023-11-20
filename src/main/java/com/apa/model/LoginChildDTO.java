package com.apa.model;

import lombok.Data;

@Data
public class LoginChildDTO {
	private String childSeq;
	private String userSeq;
	private String childName;
	private String childSsn;
	private String childTel;

	public String getChildSeq() {
		return childSeq;
	}

	public void setChildSeq(String childSeq) {
		this.childSeq = childSeq;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildSsn() {
		return childSsn;
	}

	public void setChildSsn(String childSsn) {
		this.childSsn = childSsn;
	}

	public String getChildTel() {
		return childTel;
	}

	public void setChildTel(String childTel) {
		this.childTel = childTel;
	}

}
