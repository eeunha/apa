package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginAdminDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 관리자 로그인과 관련된 데이터베이스 액세스 객체입니다.
 */
public class LoginAdminDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
     * LoginAdminDAO 생성자입니다. 데이터베이스 연결을 수행합니다.
     */
    public LoginAdminDAO() {
        this.conn = DBUtil.open();
    }

    /**
     * 관리자 로그인을 수행합니다.
     *
     * @param dto 로그인 시도하는 관리자의 정보를 담은 DTO
     * @return 로그인 성공 시 해당 관리자의 정보를 담은 DTO, 실패 시 null
     */
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
