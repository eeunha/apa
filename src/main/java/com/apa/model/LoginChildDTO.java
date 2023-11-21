package com.apa.model;

/**
 * 사용자의 자녀 정보를 담는 데이터 모델 클래스입니다.
 */
public class LoginChildDTO {

    private String childSeq;  // 자녀 일련번호
    private String userSeq;   // 사용자 일련번호
    private String childName; // 자녀 이름
    private String childSsn;  // 자녀 주민등록번호
    private String childTel;  // 자녀 전화번호

    /**
     * 자녀 일련번호를 반환합니다.
     *
     * @return 자녀 일련번호
     */
    public String getChildSeq() {
        return childSeq;
    }

    /**
     * 자녀 일련번호를 설정합니다.
     *
     * @param childSeq 자녀 일련번호
     */
    public void setChildSeq(String childSeq) {
        this.childSeq = childSeq;
    }

    /**
     * 사용자 일련번호를 반환합니다.
     *
     * @return 사용자 일련번호
     */
    public String getUserSeq() {
        return userSeq;
    }

    /**
     * 사용자 일련번호를 설정합니다.
     *
     * @param userSeq 사용자 일련번호
     */
    public void setUserSeq(String userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * 자녀 이름을 반환합니다.
     *
     * @return 자녀 이름
     */
    public String getChildName() {
        return childName;
    }

    /**
     * 자녀 이름을 설정합니다.
     *
     * @param childName 자녀 이름
     */
    public void setChildName(String childName) {
        this.childName = childName;
    }

    /**
     * 자녀 주민등록번호를 반환합니다.
     *
     * @return 자녀 주민등록번호
     */
    public String getChildSsn() {
        return childSsn;
    }

    /**
     * 자녀 주민등록번호를 설정합니다.
     *
     * @param childSsn 자녀 주민등록번호
     */
    public void setChildSsn(String childSsn) {
        this.childSsn = childSsn;
    }

    /**
     * 자녀 전화번호를 반환합니다.
     *
     * @return 자녀 전화번호
     */
    public String getChildTel() {
        return childTel;
    }

    /**
     * 자녀 전화번호를 설정합니다.
     *
     * @param childTel 자녀 전화번호
     */
    public void setChildTel(String childTel) {
        this.childTel = childTel;
    }
}
