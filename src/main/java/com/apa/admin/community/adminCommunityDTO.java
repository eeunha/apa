package com.apa.admin.community;

public class adminCommunityDTO {
	private String communitySeq;
	private String subject;
	private String content;
	private String regdate;
	private int likeCount;
	private String id;
	private String name;
	private int cct;
	private String userSeq;
	private String commentSeq;
	private String commentId;
	
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(String commentSeq) {
		this.commentSeq = commentSeq;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getCommunitySeq() {
		return communitySeq;
	}
	public void setCommunitySeq(String communitySeq) {
		this.communitySeq = communitySeq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCct() {
		return cct;
	}
	public void setCct(int cct) {
		this.cct = cct;
	}
	
	

}
