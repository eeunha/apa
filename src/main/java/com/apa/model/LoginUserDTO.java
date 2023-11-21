package com.apa.model;

/**
 * 사용자 정보를 담는 데이터 모델 클래스입니다.
 */
public class LoginUserDTO {
    private int userSeq;                  // 사용자 일련번호
    private String userName;              // 사용자 이름
    private String userSsn;               // 사용자 주민등록번호
    private String userId;                // 사용자 ID
    private String userPw;                // 사용자 비밀번호
    private String userTel;               // 사용자 전화번호
    private String userAddress;           // 사용자 주소
    private String userEmail;             // 사용자 이메일
    private String userChild;             // 사용자 자녀 정보
    private String userCautionCount;      // 사용자 주의사항 횟수
    private String registerDate;          // 등록 일자
    private String isUserUnregister;      // 사용자 등록 해제 여부

    /**
     * 사용자 일련번호를 반환합니다.
     */
    public int getUserSeq() {
        return userSeq;
    }

    /**
     * 사용자 일련번호를 설정합니다.
     * @param userSeq 사용자 일련번호
     */
    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * 사용자 이름을 반환합니다.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 사용자 이름을 설정합니다.
     * @param userName 사용자 이름
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 사용자 주민등록번호를 반환합니다.
     */
    public String getUserSsn() {
        return userSsn;
    }

    /**
     * 사용자 주민등록번호를 설정합니다.
     * @param userSsn 사용자 주민등록번호
     */
    public void setUserSsn(String userSsn) {
        this.userSsn = userSsn;
    }

    /**
     * 사용자 ID를 반환합니다.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 사용자 ID를 설정합니다.
     * @param userId 사용자 ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 사용자 비밀번호를 반환합니다.
     */
    public String getUserPw() {
        return userPw;
    }

    /**
     * 사용자 비밀번호를 설정합니다.
     * @param userPw 사용자 비밀번호
     */
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    /**
     * 사용자 전화번호를 반환합니다.
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * 사용자 전화번호를 설정합니다.
     * @param userTel 사용자 전화번호
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    /**
     * 사용자 주소를 반환합니다.
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 사용자 주소를 설정합니다.
     * @param userAddress 사용자 주소
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * 사용자 이메일을 반환합니다.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 사용자 이메일을 설정합니다.
     * @param userEmail 사용자 이메일
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 사용자 자녀 정보를 반환합니다.
     */
    public String getUserChild() {
        return userChild;
    }

    /**
     * 사용자 자녀 정보를 설정합니다.
     * @param userChild 사용자 자녀 정보
     */
    public void setUserChild(String userChild) {
        this.userChild = userChild;
    }

    /**
     * 사용자 주의사항 횟수를 반환합니다.
     */
    public String getUserCautionCount() {
        return userCautionCount;
    }

    /**
     * 사용자 주의사항 횟수를 설정합니다.
     * @param userCautionCount 사용자 주의사항 횟수
     */
    public void setUserCautionCount(String userCautionCount) {
        this.userCautionCount = userCautionCount;
    }

    /**
     * 등록 일자를 반환합니다.
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * 등록 일자를 설정합니다.
     * @param registerDate 등록 일자
     */
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 사용자 등록 해제 여부를 반환합니다.
     */
    public String getIsUserUnregister() {
        return isUserUnregister;
    }

    /**
     * 사용자 등록 해제 여부를 설정합니다.
     * @param isUserUnregister 사용자 등록 해제 여부
     */
    public void setIsUserUnregister(String isUserUnregister) {
        this.isUserUnregister = isUserUnregister;
    }
}
