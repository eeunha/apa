package com.apa.model;

public class CommunityStorageCommentDTO {
	private String communityCommentContent;
	private String communityCommentDate;
	private String userId;
	public String getCommunityCommentContent() {
		return communityCommentContent;
	}
	public void setCommunityCommentContent(String communityCommentContent) {
		this.communityCommentContent = communityCommentContent;
	}
	public String getCommunityCommentDate() {
		return communityCommentDate;
	}
	public void setCommunityCommentDate(String communityCommentDate) {
		this.communityCommentDate = communityCommentDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
