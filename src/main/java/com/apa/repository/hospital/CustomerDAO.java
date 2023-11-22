package com.apa.repository.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.model.hospital.PatientDTO;
/*import com.apa.repository.DBUtil;*/

/**
 * 병원 - 내 진료에서 고객관리를 담당하는 DAO 클래스입니다.
 * @author Eunha
 *
 */
public class CustomerDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CustomerDAO() {
		conn = DBUtil.open(); 

		/* conn = DBUtil.open("localhost", "apa_test_2", "java1234"); */
	}

	/**
	 * 해당 병원의 내원환자 목록을 가져오는 메소드입니다.
	 * @param hospitalId
	 * @return 환자DTO가 담긴 ArrayList
	 */
	public ArrayList<PatientDTO> getPatientList(String hospitalId) {
		try {

//			String sql = "select * from (select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo from tblUser u left outer join tblPatientMemo m on u.userSeq = m.userSeq and m.childSeq is null where u.userSeq in (select distinct userSeq from tblRegister where hospitalId = ? and childSeq is null) order by patientSeq) union (select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus, m.memo as memo from tblChild c inner join tblUser u on c.userSeq = u.userSeq left outer join tblPatientMemo m on c.childSeq = m.childSeq and m.userSeq is null where c.childSeq in (select distinct childSeq from tblRegister where hospitalId = ? and childSeq is not null))";

			String sql = "select * from ((select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo from tblUser u left outer join tblPatientMemo m on u.userSeq = m.userSeq and m.childSeq is null where u.userSeq in (select distinct userSeq from tblRegister where hospitalId = ? and childSeq is null)) union (select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, '자' as patientStatus, m.memo as memo from tblChild c inner join tblUser u on c.userSeq = u.userSeq left outer join tblPatientMemo m on c.childSeq = m.childSeq and m.userSeq is null where c.childSeq in (select distinct childSeq from tblRegister where hospitalId = ? and childSeq is not null))) order by patientSeq";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);
			pstat.setString(2, hospitalId);

			rs = pstat.executeQuery();

			ArrayList<PatientDTO> list = new ArrayList<>();

			while (rs.next()) {
				PatientDTO dto = new PatientDTO();

				dto.setPatientSeq(rs.getString("patientSeq"));
				dto.setPatientName(rs.getString("patientName"));
				dto.setPatientSSN(rs.getString("patientSSN"));
				dto.setPatientTel(rs.getString("patientTel"));
				dto.setPatientAddress(rs.getString("patientAddress"));
				dto.setPatientEmail(rs.getString("patientEmail"));
				dto.setMemo(rs.getString("memo"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 해당 병원의 내원환자 목록을 가져오는 메소드입니다.
	 * begin부터 end번까지 가져옵니다.
	 * @param map
	 * @return 환자DTO가 담긴 ArrayList
	 */
	public ArrayList<PatientDTO> getPatientList(HashMap<String, String> map) {
		try {

			String sql = String.format(
					"select * from (select a.*, rownum as rnum from ((select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo from tblUser u left outer join tblPatientMemo m on u.userSeq = m.userSeq and m.childSeq is null where u.userSeq in (select distinct userSeq from tblRegister where hospitalId = '%s' and childSeq is null)) union (select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, '자' as patientStatus, m.memo as memo from tblChild c inner join tblUser u on c.userSeq = u.userSeq left outer join tblPatientMemo m on c.childSeq = m.childSeq and m.userSeq is null where c.childSeq in (select distinct childSeq from tblRegister where hospitalId = '%s' and childSeq is not null))) a order by patientSeq) where rnum between %s and %s",
					map.get("hospitalId"), map.get("hospitalId"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<PatientDTO> list = new ArrayList<>();

			while (rs.next()) {
				PatientDTO dto = new PatientDTO();

				dto.setRnum(rs.getString("rnum"));
				dto.setPatientSeq(rs.getString("patientSeq"));
				dto.setPatientName(rs.getString("patientName"));
				dto.setPatientSSN(rs.getString("patientSSN"));
				dto.setPatientTel(rs.getString("patientTel"));
				dto.setPatientAddress(rs.getString("patientAddress"));
				dto.setPatientEmail(rs.getString("patientEmail"));
				dto.setPatientStatus(rs.getString("patientStatus"));
				dto.setMemo(rs.getString("memo"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 총 내원 환자의 수를 반환하는 메소드입니다.
	 * @param hospitalId
	 * @return 총 내원환자 수
	 */
	public int getPatientListCount(String hospitalId) {
		try {

			String sql = "select count(*) as cnt from ((select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo from tblUser u left outer join tblPatientMemo m on u.userSeq = m.userSeq and m.childSeq is null where u.userSeq in (select distinct userSeq from tblRegister where hospitalId = ? and childSeq is null)) union (select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, u.userAddress as patientAddress, u.userEmail as patientEmail, '자' as patientStatus, m.memo as memo from tblChild c inner join tblUser u on c.userSeq = u.userSeq left outer join tblPatientMemo m on c.childSeq = m.childSeq and m.userSeq is null where c.childSeq in (select distinct childSeq from tblRegister where hospitalId = ? and childSeq is not null)))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, hospitalId);
			pstat.setString(2, hospitalId);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
