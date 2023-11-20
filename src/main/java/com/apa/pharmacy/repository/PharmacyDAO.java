package com.apa.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.apa.DBUtil;
import com.apa.pharmacy.model.PharmacistDTO;
import com.apa.pharmacy.model.PharmacyDTO;

public class PharmacyDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public PharmacyDAO() {
		this.conn = DBUtil.open();
	}

	public PharmacyDTO get(String pharmacyId) { 
		
		try {
			
			String sql = "SELECT pharmacyName, pharmacyId, pharmacyPw, pharmacySSN, pharmacyTel, pharmacyEmail, pharmacyAddress FROM tblPharmacy WHERE PharmacyId = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pharmacyId);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				PharmacyDTO dto = new PharmacyDTO();
				dto.setPharmacyId(rs.getString("pharmacyId"));
				dto.setPharmacyName(rs.getString("pharmacyName"));
				dto.setPharmacyPw(rs.getString("pharmacyPw"));
				dto.setPharmacySSN(rs.getString("pharmacySSN"));
				dto.setPharmacyTel(rs.getString("pharmacyTel"));
				dto.setPharmacyEmail(rs.getString("pharmacyEmail"));
				dto.setPharmacyAddress(rs.getString("pharmacyAddress"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int delete(String pharmacyId) {
		try {

			String sql = "UPDATE tblPharmacy SET isPharmacyUnRegister = 'y' WHERE pharmacyId = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pharmacyId);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int edit(PharmacyDTO dto) {
		try {
			String sql = "UPDATE tblPharmacy SET pharmacyName = ?, pharmacyPw = ?, pharmacySSN = ?, pharmacyTel = ?, pharmacyEmail = ?, pharmacyAddress = ? WHERE pharmacyId= ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getPharmacyName());
			pstat.setString(2, dto.getPharmacyPw());
			pstat.setString(3, dto.getPharmacySSN());
			pstat.setString(4, dto.getPharmacyTel());
			pstat.setString(5, dto.getPharmacyEmail());
			pstat.setString(6, dto.getPharmacyAddress());
			pstat.setString(7, dto.getPharmacyId());
			

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return 0;
	}

	public PharmacistDTO pharmacistInfo(String pharmacyId) {
		try {
			
			String sql = "SELECT pharmacistSeq, pharmacyName FROM tblPharmacy WHERE pharmachId = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pharmacyId);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				PharmacistDTO dto = new PharmacistDTO();
				
				
				dto.setPharmacistSeq(rs.getString("pharmacistSeq"));
				dto.setPharmacistName(rs.getString("pharmacistName"));
				dto.setPharmacyId(rs.getString("pharmacyId"));
				
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<PharmacistDTO> PharmacistInfoList(String pharmacyId) {
	try {
			
			String sql = "SELECT pharmacistSeq, pharmacyName FROM tblPharmacy WHERE pharmachId = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pharmacyId);
			
			rs = pstat.executeQuery();
			
			ArrayList<PharmacistDTO> list = new ArrayList<PharmacistDTO>();
			
			while (rs.next()) {
				
				PharmacistDTO dto = new PharmacistDTO();
				
				dto.setPharmacistSeq(rs.getString("pharmacistSeq"));
				dto.setPharmacistName(rs.getString("pharmacistName"));
				dto.setPharmacyId(rs.getString("pharmacyId"));
				
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public PharmacyDTO getEntryInfo(String pharmacyId) {
		  try {
		        String sql = "select pharmacyid, pharmacyname, pharmacyaddress, pharmacyemail, pharmacytel,pharmacistName, isDispense, openTime,closeTime,\r\n"
		        		+ "ispharmarcynightcare,ispharmarcyholiday,pharmacydayoff, status, regdate from vwEntryAllInfo where pharmacyId=?";
		        pstat = conn.prepareStatement(sql);
		        pstat.setString(1, pharmacyId);
		        rs = pstat.executeQuery();

		        if (rs.next()) {
		        	
		            PharmacyDTO dto = new PharmacyDTO();
		            dto.setPharmacyId(rs.getString("pharmacyId"));
		            dto.setPharmacyName(rs.getString("pharmacyName"));
		            dto.setPharmacyAddress(rs.getString("pharmacyAddress"));
		            dto.setPharmacyEmail(rs.getString("pharmacyEmail"));
		            dto.setPharmacyTel(rs.getString("pharmacyTel"));
		            dto.setPharmacistName(rs.getString("pharmacistName"));
		            dto.setIsDispense(rs.getString("isDispense"));
		            dto.setIsPharmarcyNightCare(rs.getString("isPharmarcyNightCare"));
		            dto.setIsPharmarcyHoliday(rs.getString("isPharmarcyHoliday"));
		            dto.setPharmacyDayOff(rs.getString("pharmacyDayOff"));
		            dto.setOpenTime(rs.getString("openTime"));
		            dto.setCloseTime(rs.getString("closeTime"));
		            dto.setStatus(rs.getString("status"));
		            dto.setRegdate(rs.getString("regdate"));
		            
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
	
	public int entryRegister(PharmacyDTO dto) {
	    int generatedEntrySeq = -1;

	    try {
	        String sql = "insert into tblEntry(entrySeq, entryType, hospitalId, pharmacyId, status) values(seqEntry.nextVal, '약국', null, ?, default)";

	       
	        pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        pstat.setString(1, dto.getPharmacyId());

	        int rowsAffected = pstat.executeUpdate();

	        if (rowsAffected > 0) {
	            ResultSet rs = pstat.getGeneratedKeys();
	            if (rs.next()) {
	                generatedEntrySeq = rs.getInt(1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return generatedEntrySeq;
	}

	
	public int entryRegisterDate(PharmacyDTO dto, String entrySeq) {
	    try {
	        String sql = "insert into tblEntryDate(entryDateSeq, entrySeq, regdate, entryDate) values(seqEntryDate.nextVal, ?, sysdate, null)";

	        pstat = conn.prepareStatement(sql);

	        pstat.setString(1, entrySeq);

	        return pstat.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0;
	}

}	

