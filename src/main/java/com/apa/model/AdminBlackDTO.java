package com.apa.model;

public class AdminBlackDTO {
	private String blacklistSeq;
	private int userSeq;
	private String blacklistDate;
	private String blacklistReason;
	
	private String userId;
	private String userName;

	public String getBlacklistSeq() {
		return blacklistSeq;
	}

	public void setBlacklistSeq(String blacklistSeq) {
		this.blacklistSeq = blacklistSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getBlacklistDate() {
		return blacklistDate;
	}

	public void setBlacklistDate(String blacklistDate) {
		this.blacklistDate = blacklistDate;
	}

	public String getBlacklistReason() {
		return blacklistReason;
	}

	public void setBlacklistReason(String blacklistReason) {
		this.blacklistReason = blacklistReason;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
