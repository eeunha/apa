package com.apa.admin.advice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.community.communityDTO;

public class adminAdviceDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;	
	
	
	public adminAdviceDAO() {
		this.conn = DBUtil.open();
	}


	public ArrayList<adminAdviceDTO> adminAdvice(HashMap<String, String> map) {
		 int begin = Integer.parseInt(map.get("begin"));
	      int end = Integer.parseInt(map.get("end"));
		//List.java
		try {
			String sql = String.format(
			        "SELECT * FROM (SELECT * FROM (" +
			        "    SELECT ROWNUM AS rnum, mq.*, d.departmentName, dt.doctorName, h.hospitalName, ma.counselAnswerContent, u.userId " +
			        "    FROM tblMediCounselingQuestion mq " +
			        "    INNER JOIN tblUser u on mq.userSeq = u.userSeq INNER JOIN tbldepartment d ON mq.departmentSeq = d.departmentSeq " +
			        "    LEFT OUTER JOIN tblmediCounselingAnswer ma ON mq.mediCounselQuestionSeq = ma.mediCounselQuestionSeq " +
			        "    LEFT OUTER JOIN tblDoctor dt ON ma.doctorSeq = dt.doctorSeq " +
			        "    LEFT OUTER JOIN tblHospital h ON dt.hospitalId = h.hospitalId " +
			        "    ORDER BY mq.regdate DESC" +
			        ")) " +
			        "WHERE rnum BETWEEN %d AND %d", begin, end);
			

			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<adminAdviceDTO> list = new ArrayList<adminAdviceDTO>();

			while (rs.next()) {

				adminAdviceDTO dto = new adminAdviceDTO();

				dto.setDepartName(rs.getString("departmentName"));
				dto.setAdviceTitle(rs.getString("counselTitle"));
				dto.setAdviceContent(rs.getString("counselContent"));
				dto.setIsAnswer(rs.getString("isCounselAnswer"));
				dto.setAdviceDoctorName(rs.getString("doctorName"));
				dto.setAdviceHospitalName(rs.getString("hospitalName"));
				dto.setAdviceCounselAnswerContent(rs.getString("counselAnswerContent"));
				dto.setAdviceSeq(rs.getString("MEDICOUNSELQUESTIONSEQ"));
				dto.setUserId(rs.getString("userId"));
				dto.setAdviceRegdate(rs.getString("regdate"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
	
		return null;
	}


	public int getTotalCount() {
		try {

			String sql = "select count(*) as cnt from tblMediCounselingQuestion  mq inner join tbldepartment d on mq.departmentseq = d.departmentseq left outer join tblmedicounselinganswer ma on mq.medicounselquestionseq = ma.medicounselquestionseq left outer join tblDoctor dt on ma.doctorseq = dt.doctorseq left outer join tblhospital h on dt.hospitalid = h.hospitalid order by mq.regdate desc";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	public adminAdviceDTO get(String seq) {
		try {

			String sql = "SELECT mq.*, d.departmentName, dt.doctorName, h.hospitalName, ma.counselAnswerContent, u.userId  FROM tblMediCounselingQuestion mq INNER JOIN tblUser u on mq.userSeq = u.userSeq INNER JOIN tbldepartment d ON mq.departmentSeq = d.departmentSeq  LEFT OUTER JOIN tblmediCounselingAnswer ma ON mq.mediCounselQuestionSeq = ma.mediCounselQuestionSeq LEFT OUTER JOIN tblDoctor dt ON ma.doctorSeq = dt.doctorSeq LEFT OUTER JOIN tblHospital h ON dt.hospitalId = h.hospitalId  where mq.medicounselquestionseq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				adminAdviceDTO dto = new adminAdviceDTO();

				dto.setAdviceSeq(rs.getString("mediCounselQuestionSeq"));
				dto.setAdviceTitle(rs.getString("COUNSELTITLE"));
				dto.setAdviceContent(rs.getString("COUNSELCONTENT"));
				dto.setDepartName(rs.getString("COUNSELCONTENT"));
				dto.setUserId(rs.getString("userId"));
				dto.setAdviceRegdate(rs.getString("REGDATE"));
				dto.setAdviceHospitalName(rs.getString("HOSPITALNAME"));
				dto.setAdviceDoctorName(rs.getString("DOCTORNAME"));
				dto.setAdviceCounselAnswerContent(rs.getString("COUNSELANSWERCONTENT"));
				dto.setDoctorDepartment(rs.getString("DEPARTMENTNAME"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
