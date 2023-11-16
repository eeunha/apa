package com.apa.model;

import lombok.Data;

@Data
public class LoginUserDTO {
    private int userSeq;
    private String userName;
    private String userSsn;
    private String userId;
    private String userPw;
    private String userTel;
    private String userAddress;
    private String userEmail;
    private String userChild;
    private String userCautionCount;
    private String registerDate;
    private String isUserUnregister;



}
