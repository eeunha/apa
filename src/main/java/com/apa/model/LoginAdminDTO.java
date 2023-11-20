package com.apa.model;

import lombok.Data;

@Data
public class LoginAdminDTO {

    private String adminId;
    private String adminPw;
    private String adminEmail;
    private String adminTel;
}
