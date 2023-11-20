package com.apa.model;

public class MediQuestionDTO {
	//mcq.medicounselquestionseq, mcq.counseltitle, mcq.counselcontent, mcq.iscounselanswer, mcq.regdate
	private String seq;
	private String title;
	private String content;
	private String answer;
	private String regdate;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
