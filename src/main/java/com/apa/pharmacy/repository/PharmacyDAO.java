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

	/**
	 * @author 김민정 
	 * 지정된 약국 ID를 사용하여 해당 약국 정보를 검색하는 메서드입니다.
	 * @param pharmacyId 검색할 약국의 ID
	 * @return PharmacyDTO 객체에 검색된 약국 정보를 담아 반환합니다. 검색 결과가 없으면 null을 반환합니다.
	 */
	public PharmacyDTO get(String pharmacyId) {
	    try {
	        // 약국 정보를 가져오는 SQL 쿼리 작성
	        String sql = "SELECT pharmacyName, pharmacyId, pharmacyPw, pharmacySSN, pharmacyTel, pharmacyEmail, pharmacyAddress FROM tblPharmacy WHERE PharmacyId = ?";
	        
	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId); // 매개변수로 받은 pharmacyId 설정
	        
	        // SQL 쿼리 실행 결과를 ResultSet에 저장
	        rs = pstat.executeQuery();

	        if (rs.next()) {
	            // 검색된 결과가 있으면 PharmacyDTO 객체에 해당 정보를 매핑하여 반환
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

	        // 리소스 정리
	        rs.close();
	        pstat.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; // 검색된 결과가 없으면 null 반환
	}

	/**
	 * 지정된 약국 ID를 사용하여 해당 약국을 'unregistered' 상태로 변경하는 메서드입니다.
	 * @param pharmacyId 변경할 약국의 ID
	 * @return 작업이 성공적으로 수행되면 영향을 받은 행의 수를 반환합니다. 실패 시 0을 반환합니다.
	 */
	public int delete(String pharmacyId) {
	    try {
	        // 약국을 'unregistered' 상태로 변경하는 SQL 쿼리 작성
	        String sql = "UPDATE tblPharmacy SET isPharmacyUnRegister = 'y' WHERE pharmacyId = ?";

	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId); // 매개변수로 받은 pharmacyId 설정

	        // SQL 업데이트 문을 실행하고 영향을 받은 행의 수를 반환
	        return pstat.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return 0; // 작업 실패 시 0 반환
	}


	/**
	 * 약국 정보를 업데이트하는 메서드입니다.
	 * @param dto PharmacyDTO 객체에 담긴 약국 정보를 사용합니다.
	 * @return 약국 정보를 업데이트한 결과를 반환합니다. 성공 시 영향을 받은 행의 수를, 실패 시 0을 반환합니다.
	 */
	public int edit(PharmacyDTO dto) {
	    try {
	        // 약국 정보를 업데이트하는 SQL 쿼리 작성
	        String sql = "UPDATE tblPharmacy SET pharmacyName = ?, pharmacyPw = ?, pharmacySSN = ?, pharmacyTel = ?, pharmacyEmail = ?, pharmacyAddress = ? WHERE pharmacyId= ?";
	        
	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getPharmacyName());
	        pstat.setString(2, dto.getPharmacyPw());
	        pstat.setString(3, dto.getPharmacySSN());
	        pstat.setString(4, dto.getPharmacyTel());
	        pstat.setString(5, dto.getPharmacyEmail());
	        pstat.setString(6, dto.getPharmacyAddress());
	        pstat.setString(7, dto.getPharmacyId());

	        // SQL 업데이트 문을 실행하고 영향을 받은 행의 수를 반환
	        return pstat.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	    
	    return 0; // 작업 실패 시 0 반환
	}

	/**
	 * 약사의 정보를 가져오는 메서드입니다.
	 * @param pharmacyId 검색할 약사의 약국 ID
	 * @return PharmacistDTO 객체에 검색된 약사 정보를 담아 반환합니다. 검색 결과가 없으면 null을 반환합니다.
	 */
	public PharmacistDTO pharmacistInfo(String pharmacyId) {
	    try {
	        // 약사 정보를 가져오는 SQL 쿼리 작성
	        String sql = "SELECT pharmacistSeq, pharmacistName, pharmacyId FROM tblPharmacy WHERE pharmacyId = ?";
	        
	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId);
	        
	        // SQL 쿼리 실행 결과를 ResultSet에 저장
	        rs = pstat.executeQuery();
	        
	        if (rs.next()) {
	            // 검색된 결과가 있으면 PharmacistDTO 객체에 해당 정보를 매핑하여 반환
	            PharmacistDTO dto = new PharmacistDTO();
	            dto.setPharmacistSeq(rs.getString("pharmacistSeq"));
	            dto.setPharmacistName(rs.getString("pharmacistName"));
	            dto.setPharmacyId(rs.getString("pharmacyId"));
	            
	            return dto;
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null; // 검색된 결과가 없으면 null 반환
	}

	
	/**
	 * 해당 약국의 약사 정보 목록을 가져오는 메서드입니다.
	 * @param pharmacyId 검색할 약국 ID
	 * @return PharmacistDTO 객체에 검색된 약사 정보 목록을 담아 반환합니다. 검색 결과가 없으면 null을 반환합니다.
	 */
	public ArrayList<PharmacistDTO> pharmacistInfoList(String pharmacyId) {
	    try {
	        // 약사 정보를 가져오는 SQL 쿼리 작성
	        String sql = "SELECT pharmacistSeq, pharmacyName FROM tblPharmacy WHERE pharmacyId = ?";
	        
	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId);
	        
	        // SQL 쿼리 실행 결과를 ResultSet에 저장
	        rs = pstat.executeQuery();
	        
	        ArrayList<PharmacistDTO> list = new ArrayList<PharmacistDTO>(); // 약사 정보 목록을 담을 ArrayList 객체 생성
	        
	        while (rs.next()) {
	            // 검색된 결과를 PharmacistDTO 객체에 매핑하여 ArrayList에 추가
	            PharmacistDTO dto = new PharmacistDTO();
	            dto.setPharmacistSeq(rs.getString("pharmacistSeq"));
	            dto.setPharmacistName(rs.getString("pharmacistName"));
	            dto.setPharmacyId(rs.getString("pharmacyId"));
	            
	            list.add(dto);
	        }
	        
	        return list; // 약사 정보 목록 반환
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null; // 검색된 결과가 없으면 null 반환
	}

	/**
	 * 해당 약국의 상세 정보를 가져오는 메서드입니다.
	 * @param pharmacyId 검색할 약국 ID
	 * @return PharmacyDTO 객체에 검색된 약국 상세 정보를 담아 반환합니다. 검색 결과가 없으면 null을 반환합니다.
	 */
	public PharmacyDTO getEntryInfo(String pharmacyId) {
	    try {
	        // 약국의 상세 정보를 가져오는 SQL 쿼리 작성
	        String sql = "SELECT pharmacyid, pharmacyname, pharmacyaddress, pharmacyemail, pharmacytel, pharmacistName, isDispense, openTime, closeTime, "
	                + "ispharmarcynightcare, ispharmarcyholiday, pharmacydayoff, status, regdate FROM vwEntryAllInfo WHERE pharmacyId=?";
	        
	        // SQL 쿼리를 실행하기 위해 PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, pharmacyId);
	        
	        // SQL 쿼리 실행 결과를 ResultSet에 저장
	        rs = pstat.executeQuery();
	        
	        if (rs.next()) {
	            // 검색된 결과를 PharmacyDTO 객체에 매핑하여 반환
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

	    return null; // 검색된 결과가 없으면 null 반환
	}

	/**
	 * 약국 등록 정보를 테이블에 추가하는 메서드입니다.
	 * @param dto 약국 정보를 담고 있는 PharmacyDTO 객체
	 * @return 새로 생성된 Entry 시퀀스 번호를 반환합니다. 실패 시 -1을 반환합니다.
	 */
	public int entryRegister(PharmacyDTO dto) {
	    int generatedEntrySeq = -1; // 생성된 Entry 시퀀스 번호를 저장할 변수

	    try {
	        // 약국 등록 정보를 테이블에 추가하는 SQL 쿼리 작성
	        String sql = "INSERT INTO tblEntry(entrySeq, entryType, hospitalId, pharmacyId, status) VALUES(seqEntry.nextVal, '약국', null, ?, DEFAULT)";

	        // PreparedStatement 객체 생성 시 자동 생성된 키를 받기 위해 Statement.RETURN_GENERATED_KEYS 사용
	        pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        // 쿼리에 파라미터 설정
	        pstat.setString(1, dto.getPharmacyId());

	        // 쿼리 실행 및 영향을 받은 행 수 반환
	        int rowsAffected = pstat.executeUpdate();

	        if (rowsAffected > 0) {
	            // 생성된 키(Entry 시퀀스 번호) 얻기
	            ResultSet rs = pstat.getGeneratedKeys();
	            if (rs.next()) {
	                generatedEntrySeq = rs.getInt(1); // 생성된 Entry 시퀀스 번호 저장
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return generatedEntrySeq; // 생성된 Entry 시퀀스 번호 반환
	}

	/**
	 * 약국 등록 날짜 정보를 테이블에 추가하는 메서드입니다.
	 * @param dto 약국 정보를 담고 있는 PharmacyDTO 객체
	 * @param entrySeq Entry 시퀀스 번호
	 * @return 쿼리 수행 결과를 반환합니다. 성공 시 1, 실패 시 0을 반환합니다.
	 */
	public int entryRegisterDate(PharmacyDTO dto, String entrySeq) {
	    try {
	        // 약국 등록 날짜 정보를 테이블에 추가하는 SQL 쿼리 작성
	        String sql = "INSERT INTO tblEntryDate(entryDateSeq, entrySeq, regdate, entryDate) VALUES(seqEntryDate.nextVal, ?, SYSDATE, NULL)";

	        // PreparedStatement 객체 생성
	        pstat = conn.prepareStatement(sql);

	        // 쿼리에 파라미터 설정
	        pstat.setString(1, entrySeq);

	        // 쿼리 실행 및 영향을 받은 행 수 반환
	        return pstat.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0; // 쿼리 수행 실패 시 0 반환
	}


}	

