package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminUserDTO;

/**
 * @author 이혜진
 * 관리자 페이지에서 병원 정보를 관리하는 DAO 클래스
 */
public class AdminHospitalDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminHospitalDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<AdminHospitalDTO> list(HashMap<String, String> map) { //병원 목록을 조회하는 메서드
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	        String sql = String.format("SELECT * FROM (SELECT h.*, rownum as rnum FROM tblHospital h) where rnum between %s and %s"
								, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminHospitalDTO> list = new ArrayList<AdminHospitalDTO>();
	        
	        while (rs.next()) {
	        	
	        	AdminHospitalDTO dto = new AdminHospitalDTO();
	            
	            dto.setHospitalId(rs.getString("hospitalId"));
	            dto.setHospitalName(rs.getString("hospitalName"));
	            dto.setHospitalSSN(rs.getString("hospitalSSN"));
	            dto.setHospitalTel(rs.getString("hospitalTel"));
	            dto.setHospitalAddress(rs.getString("hospitalAddress"));
	            dto.setHospitalEmail(rs.getString("hospitalEmail"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setIsHospital(rs.getString("isHospital"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return null;
	}

	public int getTotalCount() { //병원의 총 게시물 수를 조회하는 메서드
		
		try {
		
			String sql = "SELECT count(*) as cnt FROM tblHospital";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public AdminHospitalDTO detail(String hospitalId) { //병원 상세 정보를 조회하는 메서드
		
		try {
	         
	         String sql = "SELECT * FROM tblHospital WHERE hospitalId = ?";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, hospitalId);
	         
	         rs = pstat.executeQuery();
	         
	         AdminHospitalDTO dto = new AdminHospitalDTO();
	         	         
	         if(rs.next()) {
	        	 
	            dto.setHospitalId(rs.getString("hospitalId"));
	            dto.setHospitalName(rs.getString("hospitalName"));
	            dto.setHospitalPw(rs.getString("hospitalPw"));
	            dto.setHospitalSSN(rs.getString("hospitalSSN"));
	            dto.setHospitalTel(rs.getString("hospitalTel"));
	            dto.setHospitalAddress(rs.getString("hospitalAddress"));
	            dto.setHospitalEmail(rs.getString("hospitalEmail"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setIsHospital(rs.getString("isHospital"));
	        	 
	            return dto;
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return null;
	}
	
	public int edit(AdminHospitalDTO dto) { //병원 정보를 수정하는 메서드
		
		try {

			String sql = "update tblHospital set hospitalName = ?, hospitalPw = ?, isHospital = ? where hospitalId = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getHospitalName());
			pstat.setString(2, dto.getHospitalPw());
			pstat.setString(3, dto.getIsHospital());
			pstat.setString(4, dto.getHospitalId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	public int del(String hospitalId) { //병원 정보를 삭제하는 메서드
		
		try {

			String sql = "delete from tblHospital where hospitalId = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}



}
