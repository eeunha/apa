package com.apa.model.hospital;

/**
 * 진료내역서 정보를 담을 DTO 클래스입니다.
 * @author Eunha
 *
 */
public class DgnsMediHistoryDTO {
	private String mediHistorySeq;
	private String mediSeq;
	private String mediName;
	private String mediCode;
	private String mediContent;

	public String getMediHistorySeq() {
		return mediHistorySeq;
	}

	public void setMediHistorySeq(String mediHistorySeq) {
		this.mediHistorySeq = mediHistorySeq;
	}

	public String getMediSeq() {
		return mediSeq;
	}

	public void setMediSeq(String mediSeq) {
		this.mediSeq = mediSeq;
	}

	public String getMediName() {
		return mediName;
	}

	public void setMediName(String mediName) {
		this.mediName = mediName;
	}

	public String getMediCode() {
		return mediCode;
	}

	public void setMediCode(String mediCode) {
		this.mediCode = mediCode;
	}

	public String getMediContent() {
		return mediContent;
	}

	public void setMediContent(String mediContent) {
		this.mediContent = mediContent;
	}

}
