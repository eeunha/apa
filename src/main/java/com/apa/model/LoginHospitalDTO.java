package com.apa.model;

/**
 * 병원 정보를 담는 데이터 모델 클래스입니다.
 */
public class LoginHospitalDTO {

    private String hospitalId;            // 병원 ID
    private String hospitalPw;            // 병원 비밀번호
    private String hospitalName;          // 병원 이름
    private String hospitalSsn;           // 병원 주민등록번호
    private String hospitalAddress;       // 병원 주소
    private String hospitalEmail;         // 병원 이메일
    private String hospitalTel;           // 병원 전화번호
    private String departmentSeq;         // 부서 일련번호
    private String isHospital;            // 병원 여부
    private String regDate;               // 등록 일자
    private String isHospitalUnRegister;  // 병원 등록 해제 여부

    /**
     * 병원 ID를 반환합니다.
     *
     * @return 병원 ID
     */
    public String getHospitalId() {
        return hospitalId;
    }

    /**
     * 병원 ID를 설정합니다.
     *
     * @param hospitalId 병원 ID
     */
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 병원 비밀번호를 반환합니다.
     *
     * @return 병원 비밀번호
     */
    public String getHospitalPw() {
        return hospitalPw;
    }

    /**
     * 병원 비밀번호를 설정합니다.
     *
     * @param hospitalPw 병원 비밀번호
     */
    public void setHospitalPw(String hospitalPw) {
        this.hospitalPw = hospitalPw;
    }

    /**
     * 병원 이름을 반환합니다.
     *
     * @return 병원 이름
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * 병원 이름을 설정합니다.
     *
     * @param hospitalName 병원 이름
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * 병원 주민등록번호를 반환합니다.
     *
     * @return 병원 주민등록번호
     */
    public String getHospitalSsn() {
        return hospitalSsn;
    }

    /**
     * 병원 주민등록번호를 설정합니다.
     *
     * @param hospitalSsn 병원 주민등록번호
     */
    public void setHospitalSsn(String hospitalSsn) {
        this.hospitalSsn = hospitalSsn;
    }

    /**
     * 병원 주소를 반환합니다.
     *
     * @return 병원 주소
     */
    public String getHospitalAddress() {
        return hospitalAddress;
    }

    /**
     * 병원 주소를 설정합니다.
     *
     * @param hospitalAddress 병원 주소
     */
    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    /**
     * 병원 이메일을 반환합니다.
     *
     * @return 병원 이메일
     */
    public String getHospitalEmail() {
        return hospitalEmail;
    }

    /**
     * 병원 이메일을 설정합니다.
     *
     * @param hospitalEmail 병원 이메일
     */
    public void setHospitalEmail(String hospitalEmail) {
        this.hospitalEmail = hospitalEmail;
    }

    /**
     * 병원 전화번호를 반환합니다.
     *
     * @return 병원 전화번호
     */
    public String getHospitalTel() {
        return hospitalTel;
    }

    /**
     * 병원 전화번호를 설정합니다.
     *
     * @param hospitalTel 병원 전화번호
     */
    public void setHospitalTel(String hospitalTel) {
        this.hospitalTel = hospitalTel;
    }

    /**
     * 부서 일련번호를 반환합니다.
     *
     * @return 부서 일련번호
     */
    public String getDepartmentSeq() {
        return departmentSeq;
    }

    /**
     * 부서 일련번호를 설정합니다.
     *
     * @param departmentSeq 부서 일련번호
     */
    public void setDepartmentSeq(String departmentSeq) {
        this.departmentSeq = departmentSeq;
    }

    /**
     * 병원 여부를 반환합니다.
     *
     * @return 병원 여부
     */
    public String getIsHospital() {
        return isHospital;
    }

    /**
     * 병원 여부를 설정합니다.
     *
     * @param isHospital 병원 여부
     */
    public void setIsHospital(String isHospital) {
        this.isHospital = isHospital;
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
     * 병원 등록 해제 여부를 반환합니다.
     *
     * @return 병원 등록 해제 여부
     */
    public String getIsHospitalUnRegister() {
        return isHospitalUnRegister;
    }

    /**
     * 병원 등록 해제 여부를 설정합니다.
     *
     * @param isHospitalUnRegister 병원 등록 해제 여부
     */
    public void setIsHospitalUnRegister(String isHospitalUnRegister) {
        this.isHospitalUnRegister = isHospitalUnRegister;
    }
}
