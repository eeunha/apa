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

public class HospitalMyPageDAO {
	private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public HospitalMyPageDAO() {
        this.conn = DBUtil.open();
    }

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
