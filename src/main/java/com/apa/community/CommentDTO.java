package com.apa.community;

public class CommentDTO {
	private String commentSeq;
	private String commentContent;
	private String commentId;
	private String userSeq;
	private String communitySeq;
	private String commentRegdate;
	
	
	
	public String getCommentRegdate() {
		return commentRegdate;
	}
	public void setCommentRegdate(String commentRegdate) {
		this.commentRegdate = commentRegdate;
	}
	public String getCommunitySeq() {
		return communitySeq;
	}
	public void setCommunitySeq(String communitySeq) {
		this.communitySeq = communitySeq;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(String commentSeq) {
		this.commentSeq = commentSeq;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	
	
	
}
