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
	  result.setPharmacyPw(rs.getString("pharmacyName"));
	  result.setPharmacyPw(rs.getString("pharmacyPw"));
	  
	  return result; 
	  }
	  
	  } 
	  catch (Exception e) {
		  e.printStackTrace();
	  } 
	  return null;
	  
	  }
	  public ArrayList<DispenseDTO> getDispensesPagingList(HashMap<String, String> map) {
		  ArrayList<DispenseDTO> dtoList = new ArrayList<>();
		 
		  try {
	           
	             String sql="SELECT  *                                                           "                 ;
					          sql += "  FROM (                                                            "             ;
					          sql += "        SELECT dispenseSeq                                          "             ;
					          sql += "             , hospitalName                                         "             ;
					          sql += "             , userName                                             "             ;
					          sql += "             , userTel                                              "             ;
					          sql += "             , treatmentDate                                        "             ;
					          sql += "             , regdate                                              "             ;
					          sql += "             , pickupWay                                            "             ;
					          sql += "             , dispenseStatus                                       "             ;
					          sql += "             , ROW_NUMBER() OVER(ORDER BY treatmentdate DESC,dispenseSeq ) AS RN ";
					          sql += "          FROM vwDispenseInfo                                       "             ;
					          sql += "         WHERE 1=1                                                  "             ;
					          if(map.get("pharmacyId")!= null && !"".equals(map.get("pharmacyId")) ) {
					        	  sql += "           AND pharmacyId = ?                                       "             ;
					          }
					          sql += ")                                                                   "             ;
					          sql += "WHERE RN BETWEEN ? AND ?                                            ";
	            
				pstat = conn.prepareStatement(sql);
				if(map.get("pharmacyId")!= null && !"".equals(map.get("pharmacyId")) ) {
					pstat.setString(1, map.get("pharmacyId"));
				}
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
	  
	  public ArrayList<DispenseDTO> getDispensesWaitingPagingList(HashMap<String, String> map) {
		  ArrayList<DispenseDTO> dtoList = new ArrayList<>();
		 
		  try {
	           
	             String sql="SELECT  *                                                           "
					          + "  FROM (                                                            "
					          + "        SELECT dispenseSeq                                          "
					          + "             , hospitalName                                         "
					          + "             , userName                                             "
					          + "             , userTel                                              "
					          + "             , treatmentDate                                        "
					          + "             , pickupWay                                            "
					          + "             , dispenseStatus                                       "
					          + "             , ROW_NUMBER() OVER(ORDER BY treatmentdate DESC,dispenseSeq ) AS RN "
					          + "          FROM vwDispenseInfo                                       "
					          + "         WHERE dispenseStatus='대기'                                                 "
					          + "           AND pharmacyId = ?                                       "
					          + ")                                                                   "
					          + "WHERE RN BETWEEN ? AND ?                                            ";
	            
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
	  
	  
	  public ArrayList<DispenseDTO> getDispensesingPagingList(HashMap<String, String> map) {
		  ArrayList<DispenseDTO> dtoList = new ArrayList<>();
		 
		  try {
	           
	             String sql="SELECT  *                                                           "
					          + "  FROM (                                                            "
					          + "        SELECT dispenseSeq                                          "
					          + "             , hospitalName                                         "
					          + "             , userName                                             "
					          + "             , userTel                                              "
					          + "             , treatmentDate                                        "
					          + "             , pickupWay                                            "
					          + "             , dispenseStatus                                       "
					          + "             , ROW_NUMBER() OVER(ORDER BY treatmentdate DESC,dispenseSeq ) AS RN "
					          + "          FROM vwDispenseInfo                                       "
					          + "         WHERE dispenseStatus='제조중'                                                 "
					          + "           AND pharmacyId = ?                                       "
					          + ")                                                                   "
					          + "WHERE RN BETWEEN ? AND ?                                            ";
	            
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
	  
	  public ArrayList<DispenseDTO> getDispenseCompletePagingList(HashMap<String, String> map) {
		  ArrayList<DispenseDTO> dtoList = new ArrayList<>();
		 
		  try {
	           
	             String sql="SELECT  *                                                           "
					          + "  FROM (                                                            "
					          + "        SELECT dispenseSeq                                          "
					          + "             , hospitalName                                         "
					          + "             , userName                                             "
					          + "             , userTel                                              "
					          + "             , treatmentDate                                        "
					          + "             , pickupWay                                            "
					          + "             , dispenseStatus                                       "
					          + "             , ROW_NUMBER() OVER(ORDER BY treatmentdate DESC,dispenseSeq ) AS RN "
					          + "          FROM vwDispenseInfo                                       "
					          + "         WHERE dispenseStatus='제조완료'                                                 "
					          + "           AND pharmacyId = ?                                       "
					          + ")                                                                   "
					          + "WHERE RN BETWEEN ? AND ?                                            ";
	            
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
	  
	  public ArrayList<DispenseDTO> getDispensePickUpPagingList(HashMap<String, String> map) {
		  ArrayList<DispenseDTO> dtoList = new ArrayList<>();
		 
		  try {
	           
	             String sql="SELECT  *                                                           "
					          + "  FROM (                                                            "
					          + "        SELECT dispenseSeq                                          "
					          + "             , hospitalName                                         "
					          + "             , userName                                             "
					          + "             , userTel                                              "
					          + "             , treatmentDate                                        "
					          + "             , regdate                                      "
					          + "             , pickupWay                                            "
					          + "             , dispenseStatus                                       "
					          + "             , ROW_NUMBER() OVER(ORDER BY treatmentdate DESC,dispenseSeq ) AS RN "
					          + "          FROM vwDispenseInfo                                       "
					          + "         WHERE dispenseStatus='수령완료'                                                 "
					          + "           AND pharmacyId = ?                                       "
					          + ")                                                                   "
					          + "WHERE RN BETWEEN ? AND ?                                            ";
	            
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
	  
	  
	  public ArrayList<DispenseDTO> getDispensesList(String pharmacyId) {
		  
	        ArrayList<DispenseDTO> dto = new ArrayList<>();

	        try {
	           
	            String sql = "select dispenseSeq||'' as dispenseSeq,hospitalName,userName,userTel, treatmentDate, regdate, pickupWay, dispenseStatus from vwDispenseInfo where pharmacyId = ?";
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
	  
	  public ArrayList<DispenseDTO> getDispensesWaitingList(String pharmacyId) {
		  
	        ArrayList<DispenseDTO> dto = new ArrayList<>();

	        try {
	           
	            String sql = "select dispenseSeq||'' as dispenseSeq,hospitalName,userName,userTel, treatmentDate, pickupWay, dispenseStatus from vwDispenseInfo where pharmacyId = ? and dispensestatus='대기'";
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

	public ArrayList<DispenseDTO> getDispensingList(String pharmacyId) {
		 ArrayList<DispenseDTO> dto = new ArrayList<>();

	        try {
	           
	            String sql = "select dispenseSeq||'' as dispenseSeq,hospitalName,userName,userTel, treatmentDate, pickupWay, dispenseStatus from vwDispenseInfo where pharmacyId = ? and dispensestatus='제조중'";
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

	public void updateDispenseFinish(String dispenseSeq) {
	
	        try {
	            String sql = "update tblDispense set dispenseStatus = '제조완료' where dispenseSeq = ?";
	            pstat = conn.prepareStatement(sql);
	            pstat.setString(1, dispenseSeq);

	            pstat.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	
	public ArrayList<DispenseDTO> getDispenseCompleteList(String pharmacyId) {
		 ArrayList<DispenseDTO> dto = new ArrayList<>();

	        try {
	           
	            String sql = "select dispenseSeq||'' as dispenseSeq,hospitalName,userName,userTel, treatmentDate, pickupWay, dispenseStatus from vwDispenseInfo where pharmacyId = ? and dispensestatus='제조완료'";
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

	public void updatecompleteFinish(String dispenseSeq) {
		try {
            String sql = "update tblDispense set dispenseStatus = '수령완료', regdate = SYSDATE where dispenseSeq = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dispenseSeq);

            pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	public ArrayList<DispenseDTO> getDispensePickUpList(String pharmacyId) {
		ArrayList<DispenseDTO> dto = new ArrayList<>();
	
		try {
	           
            String sql = "select dispenseSeq||'' as dispenseSeq,hospitalName,userName,userTel, treatmentDate, regdate, pickupWay, dispenseStatus from vwDispenseInfo where pharmacyId = ? and dispensestatus='수령완료'";
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

	
	 public DispenseCntDTO getCountByStatus(String pharmacyId) {
	        DispenseCntDTO dto = new DispenseCntDTO();
	        String sql = "";
	        
	        sql     ="SELECT SUM(DECODE(dispensestatus,'대기',1,NULL)) AS watingCnt"
	        		+"       , SUM(DECODE(dispensestatus,'제조중',1,NULL)) AS jejoCnt"
	        		+"       , SUM(DECODE(dispensestatus,'제조완료',1,NULL)) AS jejoFinCnt"
	        		+"       , SUM(DECODE(dispensestatus,'수령완료',1,NULL)) AS surungFinCnt"
	        		+"       , count(1) AS totalCnt"
	        		+"  FROM tblDispense "
	        		+" WHERE 1=1 "
	        		+"   AND pharmacyid = ?"
	        		+" group by 1";
	        
	        try {
	        	pstat = conn.prepareStatement(sql);
	        	pstat.setString(1, pharmacyId);
	        	rs = pstat.executeQuery();	   
	        	if (rs.next()) {
	        		dto.setWatingCnt(rs.getString("watingCnt"));
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
	 
		public int getTotalCount() {
			int totalCount = 0;
			
			try {
				String sql = "select count(*) from vwDispense where pharmacyId = ?";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return totalCount;
		}


}	