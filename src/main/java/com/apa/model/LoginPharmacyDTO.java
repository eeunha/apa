package com.apa.model;

import lombok.Data;

@Data
public class LoginPharmacyDTO {
    private String pharmacyId;
    private String pharmacyPw;
    private String pharmacyName;
    private String pharmacySsn;
    private String pharmacyAddress;
    private String pharmacyEmail;
    private String pharmacyTel;
    private String isDispense;
    private String isPharmacy;
    private String regDate;
    private String isPharmacyUnRegister;

}
