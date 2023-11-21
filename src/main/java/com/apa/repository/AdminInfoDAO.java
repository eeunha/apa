package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.apa.DBUtil;
import com.apa.model.AdminInfoDTO;
import com.apa.model.AdminUserDTO;

/**
 * @author 이혜진
 * 관리자 정보를 처리하는 DAO 클래스
 */
public class AdminInfoDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminInfoDAO() {
		this.conn = DBUtil.open();
	}

    public AdminInfoDTO get(String adminId) { //관리자의 정보를 조회하는 메서드
        try {
            String sql = "SELECT * FROM tblAdmin WHERE adminId = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, adminId);
            rs = pstat.executeQuery();

            if (rs.next()) {
                AdminInfoDTO dto = new AdminInfoDTO();
                dto.setAdminId(rs.getString("adminId"));
                dto.setAdminPw(rs.getString("adminPw"));
                dto.setAdminEmail(rs.getString("adminEmail"));
                dto.setAdminTel(rs.getString("adminTel"));
                
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

	public int edit(AdminInfoDTO dto) { //관리자 정보를 수정하는 메서드
		
		try {

			String sql = "update tblAdmin set adminPw = ?, adminEmail = ?, adminTel = ? where adminId = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getAdminPw());
			pstat.setString(2, dto.getAdminEmail());
			pstat.setString(3, dto.getAdminTel());
			pstat.setString(4, dto.getAdminId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
