package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminPharmacyDTO;

/**
 * @author 이혜진
 * 약국 정보를 처리하는 DAO 클래스
 */
public class AdminPharmacyDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminPharmacyDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<AdminPharmacyDTO> list(HashMap<String, String> map) { //약국 목록을 조회하는 메서드
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	        String sql = String.format("SELECT * FROM (SELECT p.*, rownum as rnum FROM tblPharmacy p) where rnum between %s and %s"
								, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminPharmacyDTO> list = new ArrayList<AdminPharmacyDTO>();
	        
	        while (rs.next()) {
	        	
	        	AdminPharmacyDTO dto = new AdminPharmacyDTO();
	            
	            dto.setPharmacyId(rs.getString("pharmacyId"));
	            dto.setPharmacyName(rs.getString("pharmacyName"));
	            dto.setPharmacySSN(rs.getString("pharmacySSN"));
	            dto.setPharmacyTel(rs.getString("pharmacyTel"));
	            dto.setPharmacyAddress(rs.getString("pharmacyAddress"));
	            dto.setPharmacyEmail(rs.getString("pharmacyEmail"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setIsDispense(rs.getString("isDispense"));
	            dto.setIsPharmacy(rs.getString("isPharmacy"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return null;
	}
	
	public int getTotalCount() { //약국 총 개수를 조회하는 메서드
		
		try {
			
			String sql = "SELECT count(*) as cnt FROM tblPharmacy";
			
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
	
	public AdminPharmacyDTO detail(String pharmacyId) { //약국 상세 정보를 조회하는 메서드
		
		try {
	         
	         String sql = "SELECT * FROM tblPharmacy WHERE pharmacyId = ?";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, pharmacyId);
	         
	         rs = pstat.executeQuery();
	         
	         AdminPharmacyDTO dto = new AdminPharmacyDTO();
	         	         
	         if(rs.next()) {
	        	 
	            dto.setPharmacyId(rs.getString("pharmacyId"));
	            dto.setPharmacyPw(rs.getString("pharmacyPw"));
	            dto.setPharmacyName(rs.getString("pharmacyName"));
	            dto.setPharmacySSN(rs.getString("pharmacySSN"));
	            dto.setPharmacyTel(rs.getString("pharmacyTel"));
	            dto.setPharmacyAddress(rs.getString("pharmacyAddress"));
	            dto.setPharmacyEmail(rs.getString("pharmacyEmail"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setIsDispense(rs.getString("isDispense"));
	            dto.setIsPharmacy(rs.getString("isPharmacy"));
	        	 
	            return dto;
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return null;
	}
	
	public int edit(AdminPharmacyDTO dto) { //약국 정보를 수정하는 메서드
		
		try {

			String sql = "update tblPharmacy set pharmacyName = ?, pharmacyPw = ?, isPharmacy = ? where pharmacyId = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getPharmacyName());
			pstat.setString(2, dto.getPharmacyPw());
			pstat.setString(3, dto.getIsPharmacy());
			pstat.setString(4, dto.getPharmacyId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	public int del(String pharmacyId) { //약국 정보를 삭제하는 메서드
		
		try {

			String sql = "delete from tblPharmacy where pharmacyId = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pharmacyId);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
