package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminReportDTO;
import com.apa.model.AdminUserDTO;

/**
 * @author 이혜진
 * 사용자 정보를 관리하는 DAO 클래스
 */
public class AdminUserDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminUserDAO() {
		this.conn = DBUtil.open();
	}
	
	public ArrayList<AdminUserDTO> userList() { //전체 일반 회원 목록을 조회하는 메서드
		
		//회원 정보
	    try {
	        String sql = "SELECT * FROM tblUser WHERE userSeq BETWEEN 1 AND 10";
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminUserDTO> userList = new ArrayList<AdminUserDTO>();
	        
	        while (rs.next()) {
	        	
	            AdminUserDTO dto = new AdminUserDTO();
	            
	            dto.setUserId(rs.getString("userId"));
	            dto.setUserName(rs.getString("userName"));
	            dto.setUserSSN(rs.getString("userSSN"));
	            dto.setUserTel(rs.getString("userTel"));
	            dto.setUserAddress(rs.getString("userAddress"));
	            
	            userList.add(dto);
	        }
	        
	        return userList;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
		return null;
	}
	

	public ArrayList<AdminUserDTO> blackList() { //전체 블랙리스트 목록을 조회하는 메서드

	    
	    //블랙리스트
	    try {
	    	String sql = "SELECT * FROM vwPreviewBlack WHERE blacklistSeq BETWEEN 1 AND 10";
	    	
	    	stat = conn.createStatement();
	    	rs = stat.executeQuery(sql);
	    	
	    	ArrayList<AdminUserDTO> list = new ArrayList<AdminUserDTO>();
	    	
	    	while (rs.next()) {
	    		
	    		AdminUserDTO dto = new AdminUserDTO();
	    		
	        	dto.setUserId(rs.getString("userId"));
	        	dto.setUserName(rs.getString("userName"));
	            dto.setBlacklistDate(rs.getString("blacklistDate"));
	            dto.setBlacklistReason(rs.getString("blacklistReason"));
	    		
	    		list.add(dto);
	    	}
	    	
	    	return list;
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
		return null;
	}

	public ArrayList<AdminUserDTO> reporList() { //전체 신고 목록을 조회하는 메서드

	    
	    //신고현황
	    try {
	    	String sql = "SELECT * FROM vwPreviewreport WHERE ROWNUM <= 10";
	    	
	    	stat = conn.createStatement();
	    	rs = stat.executeQuery(sql);
	    	
	    	ArrayList<AdminUserDTO> reportList = new ArrayList<AdminUserDTO>();
	    	
	    	while (rs.next()) {
	    		
	    		AdminUserDTO dto = new AdminUserDTO();
	    		
	        	dto.setUserId(rs.getString("userId"));
	        	dto.setUserName(rs.getString("userName"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setReportType(rs.getString("reportType"));
	            dto.setIsReportState(rs.getString("isReportState"));
	    		
	            reportList.add(dto);
	    	}
	    	
	    	return reportList;
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
		return null;
	}

	public ArrayList<AdminUserDTO> list(HashMap<String, String> map) { //전체 일반 회원 목록을 조회하는 메서드
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	    		    	
			String sql = String.format("SELECT * FROM (SELECT u.*, rownum as rnum FROM tblUser u) where rnum between %s and %s"
								, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminUserDTO> list = new ArrayList<AdminUserDTO>();
	        
	        while (rs.next()) {
	        	
	            AdminUserDTO dto = new AdminUserDTO();
	            
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setUserName(rs.getString("userName"));
	            dto.setUserSSN(rs.getString("userSSN"));
	            dto.setUserId(rs.getString("userId"));
	            dto.setUserTel(rs.getString("userTel"));
	            dto.setUserAddress(rs.getString("userAddress"));
	            dto.setUserEmail(rs.getString("userEmail"));
	            dto.setUserChild(rs.getString("userChild"));
	            dto.setRegisterDate(rs.getString("registerDate"));
	            dto.setIsUserUnregister(rs.getString("isUserUnregister"));
	            
	            list.add(dto);
	        }
	        
	        return list;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return null;
	}
	
	public int getTotalCount() { //전체 일반 회원 수를 조회하는 메서드
		
		try {
			
			String sql = "SELECT count(*) as cnt FROM tblUser";
			
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

	public AdminUserDTO detail(String userSeq) { //일반 회원의 상세 정보를 조회하는 메서드
		
		try {
	         
	         String sql = "SELECT * FROM tblUser WHERE userSeq = ?";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, userSeq);
	         
	         rs = pstat.executeQuery();
	         
	         AdminUserDTO dto = new AdminUserDTO();
	         	         
	         if(rs.next()) {
	        	
	        	dto.setUserSeq(rs.getString("userSeq"));
	        	dto.setUserId(rs.getString("userId"));
	        	dto.setUserPw(rs.getString("userPw"));
	            dto.setUserName(rs.getString("userName"));
	            dto.setUserSSN(rs.getString("userSSN"));
	            dto.setUserTel(rs.getString("userTel"));
	            dto.setUserAddress(rs.getString("userAddress"));
	            dto.setUserEmail(rs.getString("userEmail"));
	            dto.setUserChild(rs.getString("userChild"));
	            dto.setRegisterDate(rs.getString("registerDate"));
	            dto.setUserCautionCount(rs.getString("userCautionCount"));
	            dto.setIsUserUnregister(rs.getString("IsuserUnregister"));
	        	 
	        	 
	            return dto;
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return null;
	}

	public int edit(AdminUserDTO dto) { //회원 정보를 수정하는 메서드
		
		try {

			String sql = "update tblUser set userName = ?, userPw = ?, isUserUnregister = ? where userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserName());
			pstat.setString(2, dto.getUserPw());
			pstat.setString(3, dto.getIsUserUnregister());
			pstat.setString(4, dto.getUserSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int del(String userSeq) { //회원을 삭제하는 메서드
		
		try {

			String sql = "delete from tblUser where userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
