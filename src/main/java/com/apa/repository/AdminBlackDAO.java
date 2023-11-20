package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.AdminBlackDTO;

public class AdminBlackDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public AdminBlackDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<AdminBlackDTO> list(HashMap<String, String> map) {
		
        int begin = Integer.parseInt(map.get("begin"));
        int end = Integer.parseInt(map.get("end"));
		
	    try {
	    	
			String where = "";
			
			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'"
										, map.get("column")
										, map.get("word"));
			}
	    	
	        String sql = String.format("SELECT * FROM (SELECT b.*, rownum as rnum FROM vwBlackList b %s) where rnum between %s and %s"
								, where
	        					, map.get("begin")
								, map.get("end"));
	        
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql);
	        
	        ArrayList<AdminBlackDTO> list = new ArrayList<AdminBlackDTO>();
	        
	        while (rs.next()) {
	        	
	        	AdminBlackDTO dto = new AdminBlackDTO();
	            
	        	dto.setUserSeq(rs.getInt("userSeq"));
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

	public int getTotalCount() {
		
		try {
			
			String sql = "SELECT count(*) as cnt FROM vwBlackList";
			
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


	public String getUserSeq(String id) {
	    try {
	        String sql = "SELECT userSeq FROM tblUser WHERE userId = ?";

	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, id);

	        rs = pstat.executeQuery();

	        if (rs.next()) {
	            return rs.getString("userSeq");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public int InsertBlackUser(String seq, String content) {

		try {

			String sql = "INSERT INTO tblBlacklist VALUES ((select max(blacklistseq) from tblBlacklist)+1, ?, DEFAULT, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, content);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int deleteBlackUser(String userSeq) {
		try {

			String sql = "delete from tblBlacklist where userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
