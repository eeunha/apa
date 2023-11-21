package com.apa.model;

/**
 * 약국 정보를 담는 데이터 모델 클래스입니다.
 */
public class LoginPharmacyDTO {

    private String pharmacyId;             // 약국 ID
    private String pharmacyPw;             // 약국 비밀번호
    private String pharmacyName;           // 약국 이름
    private String pharmacySsn;            // 약국 주민등록번호
    private String pharmacyAddress;        // 약국 주소
    private String pharmacyEmail;          // 약국 이메일
    private String pharmacyTel;            // 약국 전화번호
    private String isDispense;             // 약국 약배달 여부
    private String isPharmacy;             // 약국 여부
    private String regDate;                // 등록 일자
    private String isPharmacyUnRegister;   // 약국 등록 해제 여부

    /**
     * 약국 ID를 반환합니다.
     *
     * @return 약국 ID
     */
    public String getPharmacyId() {
        return pharmacyId;
    }

    /**
     * 약국 ID를 설정합니다.
     *
     * @param pharmacyId 약국 ID
     */
    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    /**
     * 약국 비밀번호를 반환합니다.
     *
     * @return 약국 비밀번호
     */
    public String getPharmacyPw() {
        return pharmacyPw;
    }

    /**
     * 약국 비밀번호를 설정합니다.
     *
     * @param pharmacyPw 약국 비밀번호
     */
    public void setPharmacyPw(String pharmacyPw) {
        this.pharmacyPw = pharmacyPw;
    }

    /**
     * 약국 이름을 반환합니다.
     *
     * @return 약국 이름
     */
    public String getPharmacyName() {
        return pharmacyName;
    }

    /**
     * 약국 이름을 설정합니다.
     *
     * @param pharmacyName 약국 이름
     */
    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    /**
     * 약국 주민등록번호를 반환합니다.
     *
     * @return 약국 주민등록번호
     */
    public String getPharmacySsn() {
        return pharmacySsn;
    }

    /**
     * 약국 주민등록번호를 설정합니다.
     *
     * @param pharmacySsn 약국 주민등록번호
     */
    public void setPharmacySsn(String pharmacySsn) {
        this.pharmacySsn = pharmacySsn;
    }

    /**
     * 약국 주소를 반환합니다.
     *
     * @return 약국 주소
     */
    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    /**
     * 약국 주소를 설정합니다.
     *
     * @param pharmacyAddress 약국 주소
     */
    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    /**
     * 약국 이메일을 반환합니다.
     *
     * @return 약국 이메일
     */
    public String getPharmacyEmail() {
        return pharmacyEmail;
    }

    /**
     * 약국 이메일을 설정합니다.
     *
     * @param pharmacyEmail 약국 이메일
     */
    public void setPharmacyEmail(String pharmacyEmail) {
        this.pharmacyEmail = pharmacyEmail;
    }

    /**
     * 약국 전화번호를 반환합니다.
     *
     * @return 약국 전화번호
     */
    public String getPharmacyTel() {
        return pharmacyTel;
    }

    /**
     * 약국 전화번호를 설정합니다.
     *
     * @param pharmacyTel 약국 전화번호
     */
    public void setPharmacyTel(String pharmacyTel) {
        this.pharmacyTel = pharmacyTel;
    }

    /**
     * 약국 약배달 여부를 반환합니다.
     *
     * @return 약국 약배달 여부
     */
    public String getIsDispense() {
        return isDispense;
    }

    /**
     * 약국 약배달 여부를 설정합니다.
     *
     * @param isDispense 약국 약배달 여부
     */
    public void setIsDispense(String isDispense) {
        this.isDispense = isDispense;
    }

    /**
     * 약국 여부를 반환합니다.
     *
     * @return 약국 여부
     */
    public String getIsPharmacy() {
        return isPharmacy;
    }

    /**
     * 약국 여부를 설정합니다.
     *
     * @param isPharmacy 약국 여부
     */
    public void setIsPharmacy(String isPharmacy) {
        this.isPharmacy = isPharmacy;
    }

    /**
     * 등록 일자를 반환합니다.
     *
     * @return 등록 일자
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * 등록 일자를 설정합니다.
     *
     * @param regDate 등록 일자
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    /**
     * 약국 등록 해제 여부를 반환합니다.
     *
     * @return 약국 등록 해제 여부
     */
    public String getIsPharmacyUnRegister() {
        return isPharmacyUnRegister;
    }

    /**
     * 약국 등록 해제 여부를 설정합니다.
     *
     * @param isPharmacyUnRegister 약국 등록 해제 여부
     */
    public void setIsPharmacyUnRegister(String isPharmacyUnRegister) {
        this.isPharmacyUnRegister = isPharmacyUnRegister;
    }
}
