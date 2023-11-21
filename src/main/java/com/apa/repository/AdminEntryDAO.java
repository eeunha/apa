package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminEntryDTO;


/**
 * @author 이혜진
 * 관리자 페이지에서 업체(병원 및 약국) 등록 및 목록 조회를 위한 DAO 클래스
 */
public class AdminEntryDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminEntryDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<AdminEntryDTO> hospitalList(HashMap<String, String> map) { //병원 목록을 조회하는 메서드
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	        String sql = String.format("SELECT * FROM (SELECT h.*, rownum as rnum FROM vwHospitalEntry h) where rnum between %s and %s"
								, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminEntryDTO> list = new ArrayList<AdminEntryDTO>();
	        
	        while (rs.next()) {
	        	
	        	AdminEntryDTO dto = new AdminEntryDTO();
	            
	        	dto.setEntrySeq(rs.getString("entrySeq"));
	        	dto.setEntryType(rs.getString("entryType"));
	            dto.setHospitalId(rs.getString("hospitalId"));
	            dto.setHospitalName(rs.getString("hospitalName"));
	            dto.setHospitalregdate(rs.getString("hospitalregdate"));
	            dto.setEntryRegdate(rs.getString("entryRegdate"));
	            dto.setStatus(rs.getString("status"));
	            dto.setEntryDate(rs.getString("entryDate"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return null;
	}
	
	public ArrayList<AdminEntryDTO> pharmacyList(HashMap<String, String> map) { //약국 목록을 조회하는 메서드
		
		int begin = Integer.parseInt(map.get("begin"));
		int end = Integer.parseInt(map.get("end"));
		
		try {
			String sql = String.format("SELECT * FROM (SELECT p.*, rownum as rnum FROM vwPharmacyEntry p) where rnum between %s and %s"
					, map.get("begin")
					, map.get("end"));
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<AdminEntryDTO> list = new ArrayList<AdminEntryDTO>();
			
			while (rs.next()) {
				
				AdminEntryDTO dto = new AdminEntryDTO();
				
	        	dto.setEntrySeq(rs.getString("entrySeq"));
	        	dto.setEntryType(rs.getString("entryType"));
	        	dto.setPharmacyId(rs.getString("pharmacyId"));
	        	dto.setPharmacyName(rs.getString("pharmacyName"));
	            dto.setPharmacyRegdate(rs.getString("pharmacyRegdate"));
	            dto.setEntryRegdate(rs.getString("entryRegdate"));
	            dto.setStatus(rs.getString("status"));
	            dto.setEntryDate(rs.getString("entryDate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int getHospitalTotalCount() { //병원의 총 게시물 수를 조회하는 메서드
		
		try {
		
			String sql = "SELECT count(*) as cnt FROM tblEntry where entryType ='병원'";
			
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
	
	public int getPharmacyTotalCount() { //약국의 총 게시물 수를 조회하는 메서드
		
		try {
			
			String sql = "SELECT count(*) as cnt FROM tblEntry where entryType ='약국'";
			
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

}
