package com.apa.pharmacy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.pharmacy.model.DispenseCntDTO;
import com.apa.pharmacy.model.DispenseDTO;
import com.apa.pharmacy.model.PharmacyDTO;

public class DispenseDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public DispenseDAO() {
		this.conn = DBUtil.open();
	}
	
	/**
	 * @author 김민정 
     * 여러 제조 상태를 '거부'로 업데이트하는 메서드
     * 
     * @param dispenseIds 처리할 처방전 ID 배열
     * @return 업데이트 성공 여부
     */
    public boolean updateDispenseDecline(String[] dispenseIds) {
        try {
            if (dispenseIds != null && dispenseIds.length > 0) {
                String sql = "UPDATE tblDispense SET dispenseStatus = '거부' WHERE dispenseSeq = ?";
                pstat = conn.prepareStatement(sql);

                for (String dispenseId : dispenseIds) {
                    pstat.setString(1, dispenseId);
                    pstat.executeUpdate();
                }

                return true;
            } else {
                System.out.println("No dispenseIds provided for updateDispenseDecline.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 특정 제조 상태를 '제조중'으로 업데이트하는 메서드
     * 
     * @param dispenseSeq 처리할 처방전 시퀀스
     */
    public void updateDispensing(String dispenseSeq) {
        try {
            String sql = "update tblDispense set dispenseStatus = '제조중' where dispenseSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dispenseSeq);

            pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 약국 로그인 메서드
     * 
     * @param dto 약국 정보 DTO(데이터 전송 객체)
     * @return 로그인 성공 시 약국 정보 DTO 반환, 실패 시 null 반환
     */
    public PharmacyDTO login(PharmacyDTO dto) { 
        try {
            String sql = "select * from tblPharmacy where pharmacyId = ? and pharmacyPw = ? and IsPharmacyUnregister = 'n'";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyId());
            pstat.setString(2, dto.getPharmacyPw());
          
            rs = pstat.executeQuery();
          
            if (rs.next()) {
                PharmacyDTO result = new PharmacyDTO();
                result.setPharmacyId(rs.getString("pharmacyId"));
                result.setPharmacyName(rs.getString("pharmacyName")); // 여기는 "pharmacyName"으로 변경되어야 할 것 같습니다.
                result.setPharmacyPw(rs.getString("pharmacyPw"));
              
                return result; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 리소스 정리 등...
        }
        return null;
    }
    
    /**
     * 페이지별로 제조목록 리스트를 조회하는 메서드
     * 
     * @param map 검색 조건을 담은 HashMap(pharmacyId, begin, end를 사용)
     * @return DispenseDTO 객체를 담은 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensesPagingList(HashMap<String, String> map) {
        ArrayList<DispenseDTO> dtoList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ("
                       + "        SELECT dispenseSeq, hospitalName, userName, userTel, treatmentDate, regdate, pickupWay, dispenseStatus,"
                       + "             ROW_NUMBER() OVER(ORDER BY treatmentdate DESC, dispenseSeq) AS RN"
                       + "          FROM vwDispenseInfo"
                       + "         WHERE 1=1";

            if (map.get("pharmacyId") != null && !"".equals(map.get("pharmacyId"))) {
                sql += " AND pharmacyId = ?";
            }
            
            sql += ") WHERE RN BETWEEN ? AND ?";
            
            pstat = conn.prepareStatement(sql);

            int parameterIndex = 1;
            if (map.get("pharmacyId") != null && !"".equals(map.get("pharmacyId"))) {
                pstat.setString(parameterIndex++, map.get("pharmacyId"));
            }
            pstat.setString(parameterIndex++, map.get("begin"));
            pstat.setString(parameterIndex, map.get("end"));
            
            rs = pstat.executeQuery();

            while (rs.next()) {
                DispenseDTO dto = new DispenseDTO();
                dto.setDispenseSeq(rs.getString("dispenseSeq"));
                dto.setHospitalName(rs.getString("hospitalName"));
                dto.setUserName(rs.getString("userName"));
                dto.setUserTel(rs.getString("userTel"));
                dto.setTreatmentDate(rs.getString("treatmentDate"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setPickupWay(rs.getNString("pickupWay"));
                dto.setDispenseStatus(rs.getString("dispenseStatus"));
                dtoList.add(dto);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dtoList;
    }

	  
    /**
     * 대기 상태의 처방전을 페이지별로 조회하는 메서드
     * 
     * @param map 검색 조건을 담은 HashMap(여기서는 pharmacyId, begin, end를 사용)
     * @return 대기 상태의 DispenseDTO 객체를 담은 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensesWaitingPagingList(HashMap<String, String> map) {
        ArrayList<DispenseDTO> dtoList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ("
                       + "        SELECT dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus,"
                       + "             ROW_NUMBER() OVER(ORDER BY treatmentdate DESC, dispenseSeq) AS RN"
                       + "          FROM vwDispenseInfo"
                       + "         WHERE dispenseStatus='대기' AND pharmacyId = ?"
                       + ") WHERE RN BETWEEN ? AND ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, map.get("pharmacyId"));
            pstat.setString(2, map.get("begin"));
            pstat.setString(3, map.get("end"));
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dto = new DispenseDTO();
                dto.setDispenseSeq(rs.getString("dispenseSeq"));
                dto.setHospitalName(rs.getString("hospitalName"));
                dto.setUserName(rs.getString("userName"));
                dto.setUserTel(rs.getString("userTel"));
                dto.setTreatmentDate(rs.getString("treatmentDate"));
                dto.setPickupWay(rs.getNString("pickupWay"));
                dto.setDispenseStatus(rs.getString("dispenseStatus"));
                dtoList.add(dto);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dtoList;
    }

	  
	  
    /**
     * 제조 중인 상태의 처방전을 페이지별로 조회하는 메서드
     * 
     * @param map 검색 조건을 담은 HashMap( pharmacyId, begin, end를 사용)
     * @return 제조 중인 상태의 DispenseDTO 객체를 담은 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensesingPagingList(HashMap<String, String> map) {
        ArrayList<DispenseDTO> dtoList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ("
                       + "        SELECT dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus,"
                       + "             ROW_NUMBER() OVER(ORDER BY treatmentdate DESC, dispenseSeq) AS RN"
                       + "          FROM vwDispenseInfo"
                       + "         WHERE dispenseStatus='제조중' AND pharmacyId = ?"
                       + ") WHERE RN BETWEEN ? AND ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, map.get("pharmacyId"));
            pstat.setString(2, map.get("begin"));
            pstat.setString(3, map.get("end"));
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dto = new DispenseDTO();
                dto.setDispenseSeq(rs.getString("dispenseSeq"));
                dto.setHospitalName(rs.getString("hospitalName"));
                dto.setUserName(rs.getString("userName"));
                dto.setUserTel(rs.getString("userTel"));
                dto.setTreatmentDate(rs.getString("treatmentDate"));
                dto.setPickupWay(rs.getNString("pickupWay"));
                dto.setDispenseStatus(rs.getString("dispenseStatus"));
                dtoList.add(dto);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dtoList;
    }

	  
    /**
     * 제조 완료된 상태의 처방전을 페이지별로 조회하는 메서드
     * 
     * @param map 검색 조건을 담은 HashMap(pharmacyId, begin, end를 사용)
     * @return 제조 완료된 상태의 DispenseDTO 객체를 담은 ArrayList
     */
    public ArrayList<DispenseDTO> getDispenseCompletePagingList(HashMap<String, String> map) {
        ArrayList<DispenseDTO> dtoList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ("
                       + "        SELECT dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus,"
                       + "             ROW_NUMBER() OVER(ORDER BY treatmentdate DESC, dispenseSeq) AS RN"
                       + "          FROM vwDispenseInfo"
                       + "         WHERE dispenseStatus='제조완료' AND pharmacyId = ?"
                       + ") WHERE RN BETWEEN ? AND ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, map.get("pharmacyId"));
            pstat.setString(2, map.get("begin"));
            pstat.setString(3, map.get("end"));
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dto = new DispenseDTO();
                dto.setDispenseSeq(rs.getString("dispenseSeq"));
                dto.setHospitalName(rs.getString("hospitalName"));
                dto.setUserName(rs.getString("userName"));
                dto.setUserTel(rs.getString("userTel"));
                dto.setTreatmentDate(rs.getString("treatmentDate"));
                dto.setPickupWay(rs.getNString("pickupWay"));
                dto.setDispenseStatus(rs.getString("dispenseStatus"));
                dtoList.add(dto);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dtoList;
    }

	  
    /**
     * 수령 완료된 상태의 처방전을 페이지별로 조회하는 메서드
     * 
     * @param map 검색 조건을 담은 HashMap(여기서는 pharmacyId, begin, end를 사용)
     * @return 수령 완료된 상태의 DispenseDTO 객체를 담은 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensePickUpPagingList(HashMap<String, String> map) {
        ArrayList<DispenseDTO> dtoList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ("
                       + "        SELECT dispenseSeq, hospitalName, userName, userTel, treatmentDate, regdate, pickupWay, dispenseStatus,"
                       + "             ROW_NUMBER() OVER(ORDER BY treatmentdate DESC, dispenseSeq) AS RN"
                       + "          FROM vwDispenseInfo"
                       + "         WHERE dispenseStatus='수령완료' AND pharmacyId = ?"
                       + ") WHERE RN BETWEEN ? AND ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, map.get("pharmacyId"));
            pstat.setString(2, map.get("begin"));
            pstat.setString(3, map.get("end"));
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dto = new DispenseDTO();
                dto.setDispenseSeq(rs.getString("dispenseSeq"));
                dto.setHospitalName(rs.getString("hospitalName"));
                dto.setUserName(rs.getString("userName"));
                dto.setUserTel(rs.getString("userTel"));
                dto.setTreatmentDate(rs.getString("treatmentDate"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setPickupWay(rs.getNString("pickupWay"));
                dto.setDispenseStatus(rs.getString("dispenseStatus"));
                dtoList.add(dto);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dtoList;
    }

	  
    /**
     * 특정 약국에서 발급된 모든 처방전을 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 해당 약국에서 발급된 모든 처방전을 담은 DispenseDTO 객체를 포함한 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensesList(String pharmacyId) {
        ArrayList<DispenseDTO> dto = new ArrayList<>();

        try {
            String sql = "SELECT dispenseSeq || '' AS dispenseSeq, hospitalName, userName, userTel, treatmentDate, regdate, pickupWay, dispenseStatus "
                       + "FROM vwDispenseInfo WHERE pharmacyId = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dispense = new DispenseDTO();
                dispense.setDispenseSeq(rs.getString("dispenseSeq"));
                dispense.setHospitalName(rs.getString("hospitalName"));
                dispense.setUserName(rs.getString("userName"));
                dispense.setUserTel(rs.getString("userTel"));
                dispense.setTreatmentDate(rs.getString("treatmentDate"));
                dispense.setRegdate(rs.getString("regdate"));
                dispense.setPickupWay(rs.getNString("pickupWay"));
                dispense.setDispenseStatus(rs.getString("dispenseStatus"));
                dto.add(dispense);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dto;
    }

	  
    /**
     * 대기 중인 상태의 처방전을 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 해당 약국에서 대기 중인 처방전을 담은 DispenseDTO 객체를 포함한 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensesWaitingList(String pharmacyId) {
        ArrayList<DispenseDTO> dto = new ArrayList<>();

        try {
            String sql = "SELECT dispenseSeq || '' AS dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus "
                       + "FROM vwDispenseInfo WHERE pharmacyId = ? AND dispensestatus = '대기'";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
            
            while (rs.next()) {
                DispenseDTO dispense = new DispenseDTO();
                dispense.setDispenseSeq(rs.getString("dispenseSeq"));
                dispense.setHospitalName(rs.getString("hospitalName"));
                dispense.setUserName(rs.getString("userName"));
                dispense.setUserTel(rs.getString("userTel"));
                dispense.setTreatmentDate(rs.getString("treatmentDate"));
                dispense.setPickupWay(rs.getNString("pickupWay"));
                dispense.setDispenseStatus(rs.getString("dispenseStatus"));
                dto.add(dispense);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dto;
    }


//	public void updateDeclining(String dispenseSeq) {
//        try {
//            String sql = "update tblDispense set dispenseStatus = '거부' where dispenseSeq = ?";
//            pstat = conn.prepareStatement(sql);
//            pstat.setString(1, dispenseSeq);
//
//            pstat.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 제조 중인 상태의 처방전을 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 해당 약국에서 제조 중인 처방전을 담은 DispenseDTO 객체를 포함한 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensingList(String pharmacyId) {
        ArrayList<DispenseDTO> dto = new ArrayList<>();

        try {
            String sql = "SELECT dispenseSeq || '' AS dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus "
                       + "FROM vwDispenseInfo WHERE pharmacyId = ? AND dispensestatus = '제조중'";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
           
            while (rs.next()) {
                DispenseDTO dispense = new DispenseDTO();
                dispense.setDispenseSeq(rs.getString("dispenseSeq"));
                dispense.setHospitalName(rs.getString("hospitalName"));
                dispense.setUserName(rs.getString("userName"));
                dispense.setUserTel(rs.getString("userTel"));
                dispense.setTreatmentDate(rs.getString("treatmentDate"));
                dispense.setPickupWay(rs.getNString("pickupWay"));
                dispense.setDispenseStatus(rs.getString("dispenseStatus"));
                dto.add(dispense);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 특정 처방전의 상태를 '제조완료'로 업데이트하는 메서드
     * 
     * @param dispenseSeq 처방전 번호
     */
    public void updateDispenseFinish(String dispenseSeq) {
        try {
            String sql = "UPDATE tblDispense SET dispenseStatus = '제조완료' WHERE dispenseSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dispenseSeq);

            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
    /**
     * 제조가 완료된 처방전을 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 해당 약국에서 제조가 완료된 처방전을 담은 DispenseDTO 객체를 포함한 ArrayList
     */
    public ArrayList<DispenseDTO> getDispenseCompleteList(String pharmacyId) {
        ArrayList<DispenseDTO> dto = new ArrayList<>();

        try {
            String sql = "SELECT dispenseSeq || '' AS dispenseSeq, hospitalName, userName, userTel, treatmentDate, pickupWay, dispenseStatus "
                       + "FROM vwDispenseInfo WHERE pharmacyId = ? AND dispensestatus = '제조완료'";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
           
            while (rs.next()) {
                DispenseDTO dispense = new DispenseDTO();
                dispense.setDispenseSeq(rs.getString("dispenseSeq"));
                dispense.setHospitalName(rs.getString("hospitalName"));
                dispense.setUserName(rs.getString("userName"));
                dispense.setUserTel(rs.getString("userTel"));
                dispense.setTreatmentDate(rs.getString("treatmentDate"));
                dispense.setPickupWay(rs.getNString("pickupWay"));
                dispense.setDispenseStatus(rs.getString("dispenseStatus"));
                dto.add(dispense);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 특정 처방전의 상태를 '수령완료'로 업데이트하는 메서드
     * 동시에 해당 처방전의 수령일자를 현재 날짜로 갱신합니다.
     * 
     * @param dispenseSeq 처방전 번호
     */
    public void updateCompleteFinish(String dispenseSeq) {
        try {
            String sql = "UPDATE tblDispense SET dispenseStatus = '수령완료', regdate = SYSDATE WHERE dispenseSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dispenseSeq);

            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 약국에서 수령 완료된 처방전을 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 수령 완료된 처방전을 담은 DispenseDTO 객체를 포함한 ArrayList
     */
    public ArrayList<DispenseDTO> getDispensePickUpList(String pharmacyId) {
        ArrayList<DispenseDTO> dto = new ArrayList<>();
        
        try {
            String sql = "SELECT dispenseSeq || '' AS dispenseSeq, hospitalName, userName, userTel, treatmentDate, regdate, pickupWay, dispenseStatus "
                       + "FROM vwDispenseInfo WHERE pharmacyId = ? AND dispensestatus = '수령완료'";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
           
            while (rs.next()) {
                DispenseDTO dispense = new DispenseDTO();
                dispense.setDispenseSeq(rs.getString("dispenseSeq"));
                dispense.setHospitalName(rs.getString("hospitalName"));
                dispense.setUserName(rs.getString("userName"));
                dispense.setUserTel(rs.getString("userTel"));
                dispense.setTreatmentDate(rs.getString("treatmentDate"));
                dispense.setRegdate(rs.getString("regdate"));
                dispense.setPickupWay(rs.getNString("pickupWay"));
                dispense.setDispenseStatus(rs.getString("dispenseStatus"));
                dto.add(dispense);
            }
            // 리소스 정리
            rs.close();
            pstat.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dto;
    }

    /**
     * 약국의 각 상태에 따른 처방전 건수를 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return DispenseCntDTO 객체에 각 상태에 따른 처방전 건수를 설정하여 반환
     */
    public DispenseCntDTO getCountByStatus(String pharmacyId) {
        DispenseCntDTO dto = new DispenseCntDTO();
        String sql = "";
        
        sql = "SELECT SUM(DECODE(dispensestatus,'대기',1,NULL)) AS waitingCnt, "
            + "       SUM(DECODE(dispensestatus,'제조중',1,NULL)) AS jejoCnt, "
            + "       SUM(DECODE(dispensestatus,'제조완료',1,NULL)) AS jejoFinCnt, "
            + "       SUM(DECODE(dispensestatus,'수령완료',1,NULL)) AS surungFinCnt, "
            + "       COUNT(1) AS totalCnt "
            + "  FROM tblDispense "
            + " WHERE 1=1 "
            + "   AND pharmacyid = ?"
            + " GROUP BY 1";

        try {
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();	   
            if (rs.next()) {
                dto.setWatingCnt(rs.getString("waitingCnt"));
                dto.setJejoCnt(rs.getString("jejoCnt"));
                dto.setJejoFinCnt(rs.getString("jejoFinCnt"));
                dto.setSurungFinCnt(rs.getString("surungFinCnt"));
                dto.setTotalCnt(rs.getString("totalCnt"));
            }
            
            // 리소스 정리
            rs.close();
            pstat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dto;
    }

    /**
     * 전체 처방전 건수를 조회하는 메서드
     * 
     * @param pharmacyId 약국 식별자
     * @return 전체 처방전 건수
     */
    public int getTotalCount(String pharmacyId) {
        int totalCount = 0;
        
        try {
            String sql = "SELECT COUNT(*) FROM vwDispense WHERE pharmacyId = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, pharmacyId);
            rs = pstat.executeQuery();
            
            // 결과가 하나의 행만 있을 것이므로 if문 사용
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            
            // 리소스 정리
            rs.close();
            pstat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalCount;
    }


}	