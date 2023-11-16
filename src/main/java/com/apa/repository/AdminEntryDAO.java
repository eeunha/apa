package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminEntryDTO;

public class AdminEntryDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminEntryDAO() {
		this.conn = DBUtil.open();
	}

	
	public ArrayList<AdminEntryDTO> hospitalList(HashMap<String, String> map) {
		
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
	
	public ArrayList<AdminEntryDTO> pharmacyList(HashMap<String, String> map) {
		
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

	public int getHospitalTotalCount() {
		
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
	
	public int getPharmacyTotalCount() {
		
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


//	public AdminEntryDTO hospitalDetail(String hospitalId) {
//		try {
//	         
//	         String sql = "SELECT * FROM vwHospitalEntry WHERE hospitalId = ?";
//	         
//	         pstat = conn.prepareStatement(sql);
//	         pstat.setString(1, hospitalId);
//	         
//	         rs = pstat.executeQuery();
//	         
//	         AdminEntryDTO dto = new AdminEntryDTO();
//	         	         
//	         if(rs.next()) {
//	        	
//	        	dto.setHospitalId(rs.getString("hospitalId"));
//	        	dto.setRegdate(rs.getString("regdate"));
//	            dto.setHospitalName(rs.getString("hospitalName"));
//	            dto.setHospitalSSN(rs.getString("hospitalSSN"));
//	            dto.setHospitalAddress(rs.getString("hospitalAddress"));
//	            dto.setHospitalEmail(rs.getString("hospitalEmail"));
//	            dto.setHospitalTel(rs.getString("hospitalTel"));
//	            dto.setIsHospital(rs.getString("isHospital"));
//	        	 
//	        	 
//	            return dto;
//	         }
//	         
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
//		return null;
//	}
//
//
//	public AdminEntryDTO pharmacyDetail(String pharmacyId) {
//		try {
//	         
//	         String sql = "SELECT * FROM vwPharmacyEntry WHERE hospitalId = ?";
//	         
//	         pstat = conn.prepareStatement(sql);
//	         pstat.setString(1, pharmacyId);
//	         
//	         rs = pstat.executeQuery();
//	         
//	         AdminEntryDTO dto = new AdminEntryDTO();
//	         	         
//	         if(rs.next()) {
//	        	
//	        	dto.setHospitalId(rs.getString("hospitalId"));
//	        	dto.setRegdate(rs.getString("regdate"));
//	            dto.setHospitalName(rs.getString("hospitalName"));
//	            dto.setHospitalSSN(rs.getString("hospitalSSN"));
//	            dto.setHospitalAddress(rs.getString("hospitalAddress"));
//	            dto.setHospitalEmail(rs.getString("hospitalEmail"));
//	            dto.setHospitalTel(rs.getString("hospitalTel"));
//	            dto.setIsHospital(rs.getString("isHospital"));
//	            dto.setTotalReviews(rs.getString("totalReviews"));
//	            dto.setPositiveReviews(rs.getString("positiveReviews"));
//	            dto.setNegativeReviews(rs.getString("negativeReviews"));
//	            dto.setNegativePercentage(rs.getString("negativePercentage"));
//	        	 
//	        	 
//	            return dto;
//	         }
//	         
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
//		return null;
//	}

}
