package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apa.DBUtil;
import com.apa.model.ChildrenDTO;
import com.apa.model.HospitalDoctorDTO;
import com.apa.model.HospitalInfoDTO;
import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.OpenTimeDTO;
/**
 * @author 이재용
 * 병원 마이 페이지와 관련된 기능을 처리하는 데이터 액세스 객체 (DAO)입니다.
 */
public class HospitalMyPageDAO {
	private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public HospitalMyPageDAO() {
        this.conn = DBUtil.open();
    }
    /**
     * 병원 관리자 페이지 정보를 가져옵니다.
     *
     * @param 병원 아이디
     * @return 병원 관리자 페이지 정보를 담고 있는 HospitalMyPageInfoDTO 객체
     */
	public HospitalMyPageInfoDTO get(String id) {
		
		try {
			
			String sql = "select * from tblhospital where hospitalid = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,id);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				HospitalMyPageInfoDTO dto = new HospitalMyPageInfoDTO();
				
				dto.setHospitalId(rs.getString("HOSPITALID"));
				dto.setHospitalPw(rs.getString("HOSPITALPW"));
				dto.setHospitalName(rs.getString("HOSPITALNAME"));
				dto.setHospitalSsn(rs.getString("HOSPITALSSN"));
				dto.setHospitalAddress(rs.getString("HOSPITALADDRESS"));
				dto.setHospitalEmail(rs.getString("HOSPITALEMAIL"));
				dto.setHospitalTel(rs.getString("HOSPITALTEL"));
								
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	 /**
     * 병원을 비활성화(탈퇴) 처리합니다.
     *
     * @param 병원 아이디
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
	public int delete(String seq) {
		try {

			String sql = "UPDATE tblhospital SET ISHOSPITALUNREGISTER = 'y' WHERE HOSPITALID = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
     * 병원 정보를 수정합니다.
     *
     * @param 수정할 병원 정보를 담고 있는 HospitalMyPageInfoDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
	public int edit(HospitalMyPageInfoDTO dto) {
		
		try {
			String sql = "UPDATE tblhospital SET HOSPITALPW = ?, HOSPITALNAME = ?, HOSPITALADDRESS = ?, HOSPITALEMAIL = ?, HOSPITALTEL = ? WHERE HOSPITALID = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getHospitalPw());
			pstat.setString(2, dto.getHospitalName());
			pstat.setString(3, dto.getHospitalAddress());
			pstat.setString(4, dto.getHospitalEmail());
			pstat.setString(5, dto.getHospitalTel());
			pstat.setString(6, dto.getHospitalId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}	
		return 0;
	}
	/**
     * 병원의 의사 목록을 가져옵니다.
     *
     * @param 병원 아이디
     * @return 의사 목록의 ArrayList
     */
	public ArrayList<HospitalDoctorDTO> listDoctor(String seq) {
		
		try {
			
			String sql = "select * from vwhospitaldoctor where hospitalid = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<HospitalDoctorDTO> list = new ArrayList<HospitalDoctorDTO>();
			
			while (rs.next()) {
				
				HospitalDoctorDTO dto = new HospitalDoctorDTO();
				
				dto.setSeq(rs.getString("DOCTORSEQ"));
				dto.setName(rs.getString("DOCTORNAME"));
				dto.setDeptname(rs.getString("DEPARTMENTNAME"));
				dto.setImg(rs.getString("DOCTORIMAGE"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 병원에 의사를 추가합니다.
     *
     * @param 추가할 의사 정보를 담고 있는 HospitalDoctorDTO 객체
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
	public int doctorInsert(HospitalDoctorDTO dto) {
		try {

			String sql = "insert into tbldoctor (doctorseq, hospitalid, doctorname, departmentseq, doctorimage) values ((select max(doctorseq) from tbldoctor)+1, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			pstat.setString(2, dto.getName());
			pstat.setString(3, dto.getDeptname());
			pstat.setString(4, dto.getImg());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
     * 병원의 의사를 삭제합니다.
     *
     * @param 삭제할 의사 시퀀스
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
	public int doctordelete(String seq) {
		try {

			String sql = "delete tbldoctor where doctorseq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
     * 병원의 운영 시간을 가져옵니다.
     *
     * @param 병원 아이디
     * @return 병원의 운영 시간 정보를 담고 있는 OpenTimeDTO 객체
     */
	public OpenTimeDTO getOpenTime(String seq) {
		try {
			
			String sql = "select * from tbloperatingtime where hospitalid=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				OpenTimeDTO dto = new OpenTimeDTO();
				
				dto.setOpenTime(rs.getString("opentime"));
				dto.setCloseTime(rs.getString("closetime"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 병원의 휴무일을 가져옵니다.
     *
     * @param 병원 아이디
     * @return 병원의 휴무일 정보
     */
	public String getDayOff(String seq) {
		try {

			String sql = "select hospitaldayoff from tblhospitaldayoff where hospitalid= ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("hospitaldayoff");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 병원의 상세 정보를 가져옵니다.
     *
     * @param 병원 아이디
     * @return 병원의 상세 정보를 담고 있는 HospitalInfoDTO 객체
     */
	public HospitalInfoDTO infoget(String seq) {
try {
			
			String sql = "select * from vwhospitalinfo vw where vw.hospitalid=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				HospitalInfoDTO dto = new HospitalInfoDTO();
				
				dto.setId(rs.getString("HOSPITALID"));
				dto.setName(rs.getString("HOSPITALNAME"));
				dto.setAddress(rs.getString("HOSPITALADDRESS"));
				dto.setEmail(rs.getString("HOSPITALEMAIL"));
				dto.setTel(rs.getString("HOSPITALTEL"));
				dto.setOpen(rs.getString("ISHOSPITAL"));
				dto.setDeptname(rs.getString("DEPARTMENTNAME"));
				dto.setBreakopen(rs.getString("BREAKOPEN"));
				dto.setBreakclose(rs.getString("BREAKCLOSE"));
				dto.setOpentime(rs.getString("OPENTIME"));
				dto.setClosetime(rs.getString("CLOSETIME"));
				dto.setFace(rs.getString("FACE"));
				dto.setUnface(rs.getString("UNFACE"));
				dto.setCall(rs.getString("HOUSECALL"));
				dto.setCheck(rs.getString("ISHEALTHCHECK"));
				dto.setVaccin(rs.getString("VACCINATION"));
				dto.setNight(rs.getString("ISHOSPITALNIGHTCARE"));
				dto.setHoliday(rs.getString("ISHOSPITALHOLIDAY"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 병원 정보를 업데이트합니다.
     *
     * @param 병원 대면 여부
     * @param 병원 비대면 여부
     * @param 병원 외래 진료 가능 여부
     * @param 병원 건강검진 가능 여부
     * @param 병원 예방접종 가능 여부
     * @param 병원 야간 진료 가능 여부
     * @param 병원 휴무일 여부
     * @param 병원 아이디
     * @return 처리 결과 (성공: 1, 실패: 0)
     */
	public int updateHospitalInfo(String info1, String info2, String info3, String info4, String info5, String info6,
			String info7, String seq) {
		try {
			
			String sql = "update tblhospitaloperation set face=?, unface=?, housecall=?, ishealthcheck=?, vaccination=?,  ishospitalnightcare=?, ishospitalholiday=? where hospitalid = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, info1);
			pstat.setString(2, info2);
			pstat.setString(3, info3);
			pstat.setString(4, info4);
			pstat.setString(5, info5);
			pstat.setString(6, info6);
			pstat.setString(7, info7);
			pstat.setString(8, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
