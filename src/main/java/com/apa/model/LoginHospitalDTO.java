package com.apa.model;

import lombok.Data;

@Data
public class LoginHospitalDTO {

    private String hospitalId;
    private String hospitalPw;
    private String hospitalName;
    private String hospitalSsn;
    private String hospitalAddress;
    private String hospitalEmail;
    private String hospitalTel;
    private String departmentSeq;
    private String isHospital;
    private String regDate;
    private String isHospitalUnRegister;
}
