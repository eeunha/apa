package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginAdminDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginAdminDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public LoginAdminDAO() {
        this.conn = DBUtil.open();
    }

    public LoginAdminDTO login(LoginAdminDTO dto) {

        try {

            String sql = "select * from tblAdmin where AdminId = ? and AdminPw = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getAdminId());
            pstat.setString(2, dto.getAdminPw());

            rs = pstat.executeQuery();

            if (rs.next()) {

                LoginAdminDTO result = new LoginAdminDTO();

                result.setAdminId(rs.getString("AdminId"));
                result.setAdminPw(rs.getString("AdminPw"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
