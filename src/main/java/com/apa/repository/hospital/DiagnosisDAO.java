package com.apa.repository.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.hospital.DgnsMediHistoryDTO;
import com.apa.model.hospital.DiagnosisHistoryDTO;
import com.apa.model.hospital.DiagnosisRgstDTO;
//import com.apa.repository.DBUtil;

public class DiagnosisDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public DiagnosisDAO() {
		this.conn = DBUtil.open(); 

		/* conn = DBUtil.open("localhost", "apa_test_2", "java1234"); */
	}

	public ArrayList<DiagnosisRgstDTO> getRegisterList(String hospitalId) {
		try {
			String sql = "select * from vwRegisterList where to_date(regdate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and mediSeq not in (select mediSeq from tblWatingPatientList) and hospitalId = ? order by regdate";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);

			rs = pstat.executeQuery();

			ArrayList<DiagnosisRgstDTO> list = new ArrayList<>();

			while (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setMediSeq(rs.getString("mediSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setHospitalId(hospitalId);
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setRegdate(rs.getString("regdate"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<DiagnosisHistoryDTO> getHistoryList(HashMap<String, String> map) {
		try {

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = '%s' and waitingStatus not like '예약거부' order by treatmentDate, mediSeq) a) where rnum between %s and %s",
					map.get("hospitalId"), map.get("begin"), map.get("end"));

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			// System.out.println(sql);

			ArrayList<DiagnosisHistoryDTO> list = new ArrayList<>();

			while (rs.next()) {
				DiagnosisHistoryDTO dto = new DiagnosisHistoryDTO();

				dto.setRnum(rs.getString("rnum"));
				dto.setMediSeq(rs.getString("mediSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setChildName(rs.getString("childName"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int approvalRegister(String mediSeq) {
		try {
			// System.out.println("approval- mediSeq: " + mediSeq);
			String sql = "insert into tblWatingPatientList (waitingPatientSeq, mediSeq, waitingStatus) values (seqWatingPatientList.nextVal, ?, default)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int declineRegister(String mediSeq) {
		try {

			String sql = "insert into tblWatingPatientList (waitingPatientSeq, mediSeq, waitingStatus) values (seqWatingPatientList.nextVal, ?, '예약거부')";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public DiagnosisRgstDTO getRegisterDetail(String mediSeq) {
		try {

			String sql = "select * from vwRegisterDetail where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setMediSeq(mediSeq);
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setDoctorSeq(rs.getString("doctorSeq"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDispenseSeq(rs.getString("dispenseSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserSSN(rs.getString("userSSN"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserChild(rs.getString("userChild"));
				dto.setChildName(rs.getString("childName"));
				dto.setChildSSN(rs.getString("childSSN"));
				dto.setChildTel(rs.getString("childTel"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setDoctorImage(rs.getString("doctorImage"));
				dto.setDepartmentName(rs.getString("departmentName"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<DiagnosisRgstDTO> getAllRegisterList(HashMap<String, String> map) {
		try {

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select r.mediSeq, userSeq, userName, treatmentDate, doctorName, symptom, regdate, waitingPatientSeq, case when waitingStatus = '예약거부' then '거부'  when waitingStatus is null then '대기' else '승인' end as waitingStatus from  vwRegisterList r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where hospitalId = '%s' order by regdate desc, treatmentDate desc) a) where rnum between %s and %s",
					map.get("hospitalId"), map.get("begin"), map.get("end"));

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<DiagnosisRgstDTO> list = new ArrayList<>();

			while (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setRnum(rs.getString("rnum"));
				dto.setMediSeq(rs.getString("mediSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setHospitalId(map.get("hospitalId"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				list.add(dto);
			}
			// System.out.println("dao: " + list.size());

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DiagnosisRgstDTO getAllRegisterDetail(String mediSeq) {
		try {

			String sql = "select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, userAddress, userEmail, userChild, childName, childSSN, childTel, doctorName, doctorImage, departmentName, waitingPatientSeq, case when waitingStatus is null then '대기' when waitingStatus = '예약거부' then '거부' else '승인' end as waitingStatus from vwRegisterDetail r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where r.mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setMediSeq(mediSeq);
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setDoctorSeq(rs.getString("doctorSeq"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDispenseSeq(rs.getString("dispenseSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserSSN(rs.getString("userSSN"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserChild(rs.getString("userChild"));
				dto.setChildName(rs.getString("childName"));
				dto.setChildSSN(rs.getString("childSSN"));
				dto.setChildTel(rs.getString("childTel"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setDoctorImage(rs.getString("doctorImage"));
				dto.setDepartmentName(rs.getString("departmentName"));

				dto.setWaitingPatientSeq(rs.getString("waitingPatientSeq"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getAllRegisterCount(String hospitalId) {
		try {

			String sql = "select count(*) as cnt from vwRegisterList r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where hospitalId = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int getHistoryListCount(String hospitalId) {
		try {

			String sql = "select count(*) as cnt from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = ? and waitingStatus not like '예약거부'";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int callPatient(String mediSeq) {
		try {

			String sql = "update tblWatingPatientList set waitingStatus = '진료중' where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public DiagnosisRgstDTO getOrderDetail(String mediSeq) {
		try {

			String sql = "select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, userAddress, userEmail, userChild, childName, childSSN, childTel, doctorName, doctorImage, departmentName, waitingPatientSeq, waitingStatus from vwRegisterDetail r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where r.mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setMediSeq(mediSeq);
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setDoctorSeq(rs.getString("doctorSeq"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDispenseSeq(rs.getString("dispenseSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserSSN(rs.getString("userSSN"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserChild(rs.getString("userChild"));
				dto.setChildName(rs.getString("childName"));
				dto.setChildSSN(rs.getString("childSSN"));
				dto.setChildTel(rs.getString("childTel"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setDoctorImage(rs.getString("doctorImage"));
				dto.setDepartmentName(rs.getString("departmentName"));

				dto.setWaitingPatientSeq(rs.getString("waitingPatientSeq"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getDoctorName(String mediSeq) {
		try {

			String sql = "select doctorName from vwRegisterList where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getString("doctorName");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int addMediHistory(DgnsMediHistoryDTO dto) {
		try {

			String sql = "insert into tblMediHistory (mediHistorySeq, mediSeq, mediName, mediCode, mediContent) values (seqMediHistory.nextVal, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMediSeq());
			pstat.setString(2, dto.getMediName());
			pstat.setString(3, dto.getMediCode());
			pstat.setString(4, dto.getMediContent());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int updateWaitingStatus(String mediSeq) {
		try {

			String sql = "update tblWatingPatientList set waitingStatus = '진료완료' where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public ArrayList<DiagnosisHistoryDTO> getAllHistoryList(HashMap<String, String> map) {
		try {

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where hospitalId = '%s' and waitingStatus not like '예약거부' order by treatmentDate desc, mediSeq desc) a) where rnum between %s and %s",
					map.get("hospitalId"), map.get("begin"), map.get("end"));

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			// System.out.println(sql);

			ArrayList<DiagnosisHistoryDTO> list = new ArrayList<>();

			while (rs.next()) {
				DiagnosisHistoryDTO dto = new DiagnosisHistoryDTO();

				dto.setRnum(rs.getString("rnum"));
				dto.setMediSeq(rs.getString("mediSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setChildName(rs.getString("childName"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getAllHistoryListCount(String hospitalId) {
		try {

			String sql = "select count(*) as cnt from vwDiagnosisHistory where hospitalId = ? and waitingStatus not like '예약거부'";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public DiagnosisRgstDTO getHistoryDetail(String mediSeq) {
		try {

			String sql = "select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, userAddress, userEmail, userChild, childName, childSSN, childTel, doctorName, doctorImage, departmentName, waitingPatientSeq, waitingStatus from vwRegisterDetail r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where r.mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				DiagnosisRgstDTO dto = new DiagnosisRgstDTO();

				dto.setMediSeq(mediSeq);
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setDoctorSeq(rs.getString("doctorSeq"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setDispenseSeq(rs.getString("dispenseSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserSSN(rs.getString("userSSN"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserChild(rs.getString("userChild"));
				dto.setChildName(rs.getString("childName"));
				dto.setChildSSN(rs.getString("childSSN"));
				dto.setChildTel(rs.getString("childTel"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setDoctorImage(rs.getString("doctorImage"));
				dto.setDepartmentName(rs.getString("departmentName"));

				dto.setWaitingPatientSeq(rs.getString("waitingPatientSeq"));
				dto.setWaitingStatus(rs.getString("waitingStatus"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public DgnsMediHistoryDTO getWriteDiagnosis(String mediSeq) {
		try {

			String sql = "select * from tblMediHistory where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				DgnsMediHistoryDTO dto = new DgnsMediHistoryDTO();

				dto.setMediHistorySeq(rs.getString("mediHistorySeq"));
				dto.setMediSeq(rs.getString("mediSeq"));
				dto.setMediName(rs.getString("mediName"));
				dto.setMediCode(rs.getString("mediCode"));
				dto.setMediContent(rs.getString("mediContent"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDispenseSeq(String mediSeq) {
		try {

			String sql = "select dispenseSeq from tblRegister where mediSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mediSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getString("dispenseSeq");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int updateDispenseStatus(String dispenseSeq) {
		try {

			String sql = "update tblDispense set dispenseStatus = '대기' where dispenseSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dispenseSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
