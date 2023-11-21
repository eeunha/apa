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
/**
 * @author 이재용
 * 예약 관련 기능을 처리하는 데이터 액세스 객체 (DAO)입니다.
 */
public class ReservationDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ReservationDAO() {
		this.conn = DBUtil.open();
	}
	 /**
     * 선택한 병원과 진료과에 해당하는 의사 목록을 가져옵니다.
     *
     * @param 병원의 시퀀스
     * @param 진료과 이름
     * @return 의사 목록의 ArrayList
     */
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
	  /**
     * 유저 정보를 가져옵니다.
     *
     * @param 유저 시퀀스
     * @return 유저 정보를 담고 있는 UserDTO 객체
     */
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
	 /**
     * 유저의 자녀 목록을 가져옵니다.
     *
     * @param 사용자 시퀀스
     * @return 자녀 목록의 ArrayList
     */
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
	 /**
     * 병원 운영 시간을 가져옵니다.
     *
     * @param 병원의 시퀀스
     * @return 병원 운영 시간 정보를 담고 있는 OpenTimeDTO 객체
     */
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
	/**
     * 약국 운영 시간을 가져옵니다.
     *
     * @param 약국의 시퀀스
     * @return 약국 운영 시간 정보를 담고 있는 OpenTimeDTO 객체
     */
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
	 /**
     * 약국에 약 처방 등록을 요청합니다.
     *
     * @param 약 처방 유형
     * @param 약 처방 시간
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
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
	 /**
     * 진료 등록을 처리합니다.
     *
     * @param 진료 등록 정보를 담고 있는 InsertRegisterDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
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
	 /**
     * 약 없이 진료 등록을 처리합니다.
     *
     * @param 진료 등록 정보를 담고 있는 InsertRegisterDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
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
	/**
     * 건강검진 예약을 처리합니다.
     *
     * @param 건강검진 예약 정보를 담고 있는 InsertRegisterDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
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
	/**
     * 약 처방 등록을 처리합니다.
     *
     * @param 약 처방 등록 정보를 담고 있는 InsertRegisterDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
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
