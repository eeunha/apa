package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apa.DBUtil;
import com.apa.model.HospitalDoctorDTO;
import com.apa.model.InsertRegisterDTO;
import com.apa.model.OpenTimeDTO;
import com.apa.model.UserChildDTO;
import com.apa.model.UserDTO;

public class ReservationDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ReservationDAO() {
		this.conn = DBUtil.open();
	}
	
	public ArrayList<HospitalDoctorDTO> doctorlist(String seq, String deptname) {
		try {

			String sql = "select d.doctorseq, d.doctorname, d.doctorimage, dept.departmentname from tbldoctor d inner join tbldepartment dept on d.departmentseq = dept.departmentseq where d.hospitalid = ? and dept.departmentname = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1,seq);
			pstat.setString(2,deptname);
			
			rs = pstat.executeQuery();
			
			ArrayList<HospitalDoctorDTO> list = new ArrayList<HospitalDoctorDTO>();
			
			while (rs.next()) {
				
				HospitalDoctorDTO dto = new HospitalDoctorDTO();
				
				dto.setSeq(rs.getString("doctorseq"));
				dto.setName(rs.getString("doctorname"));
				dto.setImg(rs.getString("doctorimage"));
				dto.setDeptname(rs.getString("departmentname"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserDTO userinfo(String seq) {
		try {
			
			String sql = "select * from tbluser where userseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserSeq(rs.getInt("USERSEQ"));
				dto.setUserName(rs.getString("USERNAME"));
				dto.setUserSsn(rs.getString("USERSSN"));
				dto.setUserTel(rs.getString("USERTEL"));
				dto.setUserEmail(rs.getString("USEREMAIL"));
				dto.setUserAddress(rs.getString("USERADDRESS"));
				dto.setUserChild(rs.getString("USERCHILD"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<UserChildDTO> userchild(String seq) {
		try {

			String sql = "select * from vwuserchild where userseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<UserChildDTO> list = new ArrayList<UserChildDTO>();
			
			while (rs.next()) {
				
				UserChildDTO dto = new UserChildDTO();
				
				dto.setUserChild(rs.getString("USERCHILD"));
				dto.setChildSeq(rs.getString("CHILDSEQ"));
				dto.setChildName(rs.getString("CHILDNAME"));
				dto.setChildSsn(rs.getString("CHILDSSN"));
				dto.setChildTel(rs.getString("CHILDTEL"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public OpenTimeDTO time(String seq) {
try {
			
			String sql = "select tbloperatingtime.opentime, tbloperatingtime.closetime from tbloperatingtime where tbloperatingtime.hospitalid = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				OpenTimeDTO dto = new OpenTimeDTO();
				
				dto.setCloseTime(rs.getString("closetime"));
				dto.setOpenTime(rs.getString("opentime"));
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertDispense(String rezdrugtype, String rezdrugtime) {
		try {

			String sql = "insert into tbldispense (dispenseseq, pharmacyid, pickupway, regdate, dispensestatus) values ((select max(dispenseseq) from tbldispense)+1, 'sla0623', ?, TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'), '예약대기중')";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, rezdrugtype);
			pstat.setString(2, rezdrugtime);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int inserRegister(InsertRegisterDTO dto) {
		try {

			String sql = "INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES ((select max(tblregister.mediseq) from tblregister)+1, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'), ?, (select max(tbldispense.dispenseseq) from tbldispense))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getHospitalId());
			pstat.setString(2, dto.getUserSeq());
			pstat.setString(3, dto.getChildSeq());
			pstat.setString(4, dto.getMediWay());
			pstat.setString(5, dto.getDoctorSeq());
			pstat.setString(6, dto.getTreatementDate());
			pstat.setString(7, dto.getRegdate());
			pstat.setString(8, dto.getSymptom());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int inserRegisterNoDrug(InsertRegisterDTO dto) {
		try {

			String sql = "INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES ((select max(tblregister.mediseq) from tblregister)+1, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'), ?, null)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getHospitalId());
			pstat.setString(2, dto.getUserSeq());
			pstat.setString(3, dto.getChildSeq());
			pstat.setString(4, dto.getMediWay());
			pstat.setString(5, dto.getDoctorSeq());
			pstat.setString(6, dto.getTreatementDate());
			pstat.setString(7, dto.getRegdate());
			pstat.setString(8, dto.getSymptom());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int insercheck(InsertRegisterDTO dto) {
		try {

			String sql = "insert into tblmedicheckupreservation (MEDICHECKUPSEQ, USERSEQ, HOSPITALID, RESERVATIONDATE, REGDATE) values ((select max(tblmedicheckupreservation.medicheckupseq) from tblmedicheckupreservation)+1,?,?,TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'),TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getHospitalId());
			pstat.setString(3, dto.getTreatementDate());
			pstat.setString(4, dto.getRegdate());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public OpenTimeDTO time2(String seq) {
		try {
		String sql = "select tbloperatingtime.opentime, tbloperatingtime.closetime, tbloperatingtime.pharmacyid from tbloperatingtime where tbloperatingtime.pharmacyid=?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1,seq);
		
		rs = pstat.executeQuery();
		
		if (rs.next()) {
			
			OpenTimeDTO dto = new OpenTimeDTO();
			
			dto.setCloseTime(rs.getString("closetime"));
			dto.setOpenTime(rs.getString("opentime"));
			return dto;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertdurg(InsertRegisterDTO dto) {
		try {

			String sql = "insert into tbldispense (dispenseseq, pharmacyid, pickupway, regdate, dispensestatus) values ((select max(dispenseseq) from tbldispense)+1, ?,'방문수령',TO_DATE(?, 'YYYY-MM-DD:HH24:MI:SS'),'대기')";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getHospitalId());
			pstat.setString(2, dto.getTreatementDate());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
