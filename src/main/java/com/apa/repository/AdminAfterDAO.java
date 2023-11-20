package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminAfterDTO;

public class AdminAfterDAO {
		
		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;
		
		public AdminAfterDAO() {
			this.conn = DBUtil.open();
		}

		
		public ArrayList<AdminAfterDTO> list() {
			
		    try {
		        String sql = "SELECT * FROM vwASHospitalinfo";
		        
		        stat = conn.createStatement();
		        rs = stat.executeQuery(sql);
		        
		        ArrayList<AdminAfterDTO> list = new ArrayList<AdminAfterDTO>();
		        
		        while (rs.next()) {
		        	
		        	AdminAfterDTO dto = new AdminAfterDTO();
		            
		        	dto.setHospitalId(rs.getString("hospitalId"));
		        	dto.setRegdate(rs.getString("regdate"));
		            dto.setHospitalName(rs.getString("hospitalName"));
		            dto.setHospitalSSN(rs.getString("hospitalSSN"));
		            dto.setHospitalAddress(rs.getString("hospitalAddress"));
		            dto.setHospitalEmail(rs.getString("hospitalEmail"));
		            dto.setHospitalTel(rs.getString("hospitalTel"));
		            dto.setIsHospital(rs.getString("isHospital"));
		            dto.setTotalReviews(rs.getString("totalReviews"));
		            dto.setPositiveReviews(rs.getString("positiveReviews"));
		            dto.setNegativeReviews(rs.getString("negativeReviews"));
		            dto.setNegativePercentage(rs.getString("negativePercentage"));
		            
		            list.add(dto);
		        }
		        
		        return list;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
			return null;
		}
		
		public AdminAfterDTO detail(String hospitalId) {
			
			try {
		         
		         String sql = "SELECT * FROM vwASHospitalinfo WHERE hospitalId = ?";
		         
		         pstat = conn.prepareStatement(sql);
		         pstat.setString(1, hospitalId);
		         
		         rs = pstat.executeQuery();
		         
		         AdminAfterDTO dto = new AdminAfterDTO();
		         	         
		         if(rs.next()) {
		        	
		        	dto.setHospitalId(rs.getString("hospitalId"));
		        	dto.setRegdate(rs.getString("regdate"));
		            dto.setHospitalName(rs.getString("hospitalName"));
		            dto.setHospitalSSN(rs.getString("hospitalSSN"));
		            dto.setHospitalAddress(rs.getString("hospitalAddress"));
		            dto.setHospitalEmail(rs.getString("hospitalEmail"));
		            dto.setHospitalTel(rs.getString("hospitalTel"));
		            dto.setIsHospital(rs.getString("isHospital"));
		            dto.setTotalReviews(rs.getString("totalReviews"));
		            dto.setPositiveReviews(rs.getString("positiveReviews"));
		            dto.setNegativeReviews(rs.getString("negativeReviews"));
		            dto.setNegativePercentage(rs.getString("negativePercentage"));
		        	 
		        	 
		            return dto;
		         }
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
			
			return null;
		}


		public int edit(AdminAfterDTO dto) {
			
			try {

				String sql = "update tblHospital set isHospital = 'n' where hospitalId = ?";

				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, dto.getHospitalId());

				return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
		}

}
