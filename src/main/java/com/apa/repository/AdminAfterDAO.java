package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminAfterDTO;
import com.apa.model.AdminHospitalDTO;

/**
 * @author 이혜진
 * 관리자 페이지에서 병원 후기 및 정보를 관리하기 위한 DAO 클래스
 */
public class AdminAfterDAO {
		
		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;
		
		public AdminAfterDAO() {
			this.conn = DBUtil.open();
		}

		
		public ArrayList<AdminAfterDTO> list() { //병원 후기 및 정보 목록을 조회하는 메서드
			
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
		
		public AdminAfterDTO detail(String hospitalId) { //병원의 상세 정보를 조회하는 메서드
			
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


		public int edit(AdminAfterDTO dto) { //병원 정보를 수정하여 입점 취소하는 메서드
			
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


		public ArrayList<AdminAfterDTO> detailReviews(String hospitalId) { //병원의 후기 목록을 조회하는 메서드
			ArrayList<AdminAfterDTO> reviewList = new ArrayList<>();

		    try {
		        String sql = "SELECT u.userSeq, u.userId, r.hospitalId, r.mediSeq, rv.reviewContent, r.regdate AS revRegdate\r\n"
				        		+ "FROM tblUser u\r\n"
				        		+ "INNER JOIN tblRegister r ON u.userSeq = r.userSeq\r\n"
				        		+ "LEFT JOIN tblReview rv ON r.mediSeq = rv.mediHistorySeq\r\n"
				        		+ "WHERE r.hospitalId = ? AND rv.reviewContent IS NOT NULL\r\n"
				        		+ "ORDER BY r.hospitalId DESC";

		        pstat = conn.prepareStatement(sql);
		        pstat.setString(1, hospitalId);

		        rs = pstat.executeQuery();

		        while (rs.next()) {
		            AdminAfterDTO dtoReview = new AdminAfterDTO();
		            dtoReview.setUserId(rs.getString("userId"));
		            dtoReview.setReviewContent(rs.getString("reviewContent"));
		            dtoReview.setRevRegdate(rs.getString("revRegdate"));
		            
		            reviewList.add(dtoReview);
		        }

		        return reviewList;

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return null; // 빈 리스트 반환 또는 null 반환, 상황에 맞게 선택
		}

}
