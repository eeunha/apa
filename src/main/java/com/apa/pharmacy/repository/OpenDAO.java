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

	/**
	 * @author 김민정 
	 * 약국의 운영 정보를 가져오는 메서드
	 * 
	 * @param pharmacyId 약국 식별자
	 * @return OpenDTO 객체에 약국의 영업 정보를 설정하여 반환. 해당 약국의 정보가 없는 경우 null 반환
	 */
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

	/**
	 * 약국의 운영 시간 정보를 업데이트하는 메서드
	 * 
	 * @param dto 약국의 운영 시간 정보를 가지고 있는 OpenDTO 객체
	 * @return 업데이트가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
	public int updateTimeInfo(OpenDTO dto) {
	    try {
	        String sql = "UPDATE tblOperatingTime SET openTime = TO_DATE(?, 'HH24:MI'), closeTime = TO_DATE(?, 'HH24:MI') WHERE pharmacyId = ?";
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


	/**
	 * 약국의 야간 운영 여부와 휴일 여부를 업데이트하는 메서드
	 * 
	 * @param dto OpenDTO 객체에 담긴 야간 운영 여부와 휴일 여부 정보
	 * @return 업데이트가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
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

	/**
	 * 약국의 휴무일을 업데이트하는 메서드
	 * 
	 * @param dto OpenDTO 객체에 담긴 휴무일 정보
	 * @return 업데이트가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
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

	/**
	 * 약국의 영업 시간 정보를 추가하는 메서드
	 * 
	 * @param dto OpenDTO 객체에 담긴 영업 시간 정보
	 * @return 추가가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
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


	
	/**
	 * 약국의 야간 운영 여부와 휴일 여부를 추가하는 메서드
	 * 
	 * @param dto OpenDTO 객체에 담긴 야간 운영 여부와 휴일 여부 정보
	 * @return 추가가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
	public int addIsPharmarcy(OpenDTO dto) {
	    try {
	        String sql = "insert into tblPharmacyOperation(pharmacyId, isPharmarcyNightCare, isPharmarcyHoliday)\r\n"
	                + "values(?, ?, ?);";
	        
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getPharmacyId());
	        pstat.setString(2, dto.getIsPharmarcyNightCare());
	        pstat.setString(3, dto.getIsPharmarcyHoliday());
	        
	        return pstat.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return 0;
	}

	/**
	 * 약국의 휴무일을 추가하는 메서드
	 * 
	 * @param dto OpenDTO 객체에 담긴 휴무일 정보
	 * @return 추가가 성공하면 영향을 받는 행의 수를 반환. 실패하면 0을 반환
	 */
	public int addDayOff(OpenDTO dto) {
	    try {
	        String sql = "Insert into tblPharmacyDayOff(pharmacyDayOffSeq, pharmacyId, pharmacyDayOff)\r\n"
	                + "values (seqPharmacyDayOff.nextVal, ?, ?)";
	        
	        pstat  = conn.prepareStatement(sql);
	        
	        pstat.setString(1, dto.getPharmacyId());
	        pstat.setString(2, dto.getPharmacyDayOff());
	        
	        return pstat.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return 0;
	}




	
	
	
	
	
	
	
}
