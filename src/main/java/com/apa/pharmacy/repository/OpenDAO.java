package com.apa.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.apa.DBUtil;
import com.apa.pharmacy.model.OpenDTO;

public class OpenDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public OpenDAO() {
		this.conn = DBUtil.open();
	}

	public OpenDTO getOpenInfo(String pharmacyId) {		

	    try {
	        String sql = "SELECT openTime, closeTime, isPharmarcyNightCare, pharmacyDayOff, isPharmarcyHoliday FROM vwOpenInfo WHERE pharmacyId = ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId);
	        rs = pstat.executeQuery();

	        if (rs.next()) {
	        	
	            OpenDTO dto = new OpenDTO();
	            dto.setOpenTime(rs.getString("openTime"));
	            dto.setCloseTime(rs.getString("closeTime"));
	            dto.setIsPharmarcyNightCare(rs.getString("isPharmarcyNightCare"));
	            dto.setPharmacyDayOff(rs.getString("pharmacyDayOff"));
	            dto.setIsPharmarcyHoliday(rs.getString("isPharmarcyHoliday"));
	            return dto;
	        }

	        // 리소스 정리
	        rs.close();
	        pstat.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public int updateTimeInfo(OpenDTO dto) {
		
		try {
			String sql = "UPDATE tblOperatingTime SET openTime = TO_DATE(?, 'HH24:MI'), closeTime = TO_DATE(?, 'HH24:MI') WHERE pharmacyId = ?";;
			pstat  = conn.prepareStatement(sql);
			pstat.setString(1, dto.getOpenTime());
			pstat.setString(2, dto.getCloseTime());
			pstat.setString(3, dto.getPharmacyId());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int updateIsPharmarcy(OpenDTO dto) {
		 try {
	            String sql = "UPDATE tblPharmacyOperation SET isPharmarcyNightCare = ?, isPharmarcyHoliday = ? WHERE pharmacyId = ?";
	            
	            pstat = conn.prepareStatement(sql);
	            pstat.setString(1, dto.getIsPharmarcyNightCare());
	            pstat.setString(2, dto.getIsPharmarcyHoliday());
	            pstat.setString(3, dto.getPharmacyId());
	            
	            return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return 0;
	}

	public int updateDayOff(OpenDTO dto) {
		 try {
	            String sql = "UPDATE tblPharmacyDayOff SET pharmacyDayOff = ? WHERE pharmacyId = ?";
	            
	            pstat  = conn.prepareStatement(sql);
	            
	            pstat.setString(1, dto.getPharmacyDayOff());
	            pstat.setString(2, dto.getPharmacyId());
	            
	            return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return 0;
	}

	public int addTimeInfo(OpenDTO dto) {
		 try {

			 String sql = "Insert into tblOperatingTime(operatingtimeSeq,type,hospitalId,pharmacyId,openTime,closeTime)\r\n"
			 		+ "values(seqOperatingTime.nextVal,'약국', null, ?,?,?)";
				

				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, dto.getOpenTime());
				pstat.setString(2, dto.getCloseTime());
				pstat.setString(3, dto.getPharmacyId());

				return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
		}


	
	public int addIsPharmarcy(OpenDTO dto) {
		try {
            String sql = "insert into tblPharmacyOperation(pharmacyId, isPharmarcyNightCare, isPharmarcyHoliday)\r\n"
            		+ "values(?, ?, ?);";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getIsPharmarcyNightCare());
            pstat.setString(2, dto.getIsPharmarcyHoliday());
            pstat.setString(3, dto.getPharmacyId());
            
            return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
}
	
	public int addDayOff(OpenDTO dto) {
		 try {
	            String sql = "Insert into tblPharmacyDayOff(pharmacyDayOffSeq, pharmacyId, pharmacyDayOff)\r\n"
	            		+ "values (seqPharmacyDayOff.nextVal, ?, ?)";
	            
	            pstat  = conn.prepareStatement(sql);
	            
	            pstat.setString(1, dto.getPharmacyDayOff());
	            pstat.setString(2, dto.getPharmacyId());
	            
	            return pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return 0;
	}



	
	
	
	
	
	
	
}
