package com.apa.model;

import lombok.Data;

@Data
public class LoginUserDTO {
	private int userSeq;
	private String userName;
	private String userSsn;
	private String userId;
	private String userPw;
	private String userTel;
	private String userAddress;
	private String userEmail;
	private String userChild;
	private String userCautionCount;
	private String registerDate;
	private String isUserUnregister;

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(String userSsn) {
		this.userSsn = userSsn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserChild() {
		return userChild;
	}

	public void setUserChild(String userChild) {
		this.userChild = userChild;
	}

	public String getUserCautionCount() {
		return userCautionCount;
	}

	public void setUserCautionCount(String userCautionCount) {
		this.userCautionCount = userCautionCount;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getIsUserUnregister() {
		return isUserUnregister;
	}

	public void setIsUserUnregister(String isUserUnregister) {
		this.isUserUnregister = isUserUnregister;
	}

}
