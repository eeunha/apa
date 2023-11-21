package com.apa.model;

/**
 * 관리자 로그인 정보를 담는 데이터 모델 클래스입니다.
 */
public class LoginAdminDTO {

    private String adminId;     // 관리자 아이디
    private String adminPw;     // 관리자 비밀번호
    private String adminEmail;  // 관리자 이메일
    private String adminTel;    // 관리자 전화번호

    /**
     * 관리자 아이디를 반환합니다.
     *
     * @return 관리자 아이디
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * 관리자 아이디를 설정합니다.
     *
     * @param adminId 관리자 아이디
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * 관리자 비밀번호를 반환합니다.
     *
     * @return 관리자 비밀번호
     */
    public String getAdminPw() {
        return adminPw;
    }

    /**
     * 관리자 비밀번호를 설정합니다.
     *
     * @param adminPw 관리자 비밀번호
     */
    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    /**
     * 관리자 이메일을 반환합니다.
     *
     * @return 관리자 이메일
     */
    public String getAdminEmail() {
        return adminEmail;
    }

    /**
     * 관리자 이메일을 설정합니다.
     *
     * @param adminEmail 관리자 이메일
     */
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    /**
     * 관리자 전화번호를 반환합니다.
     *
     * @return 관리자 전화번호
     */
    public String getAdminTel() {
        return adminTel;
    }

    /**
     * 관리자 전화번호를 설정합니다.
     *
     * @param adminTel 관리자 전화번호
     */
    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }
}
