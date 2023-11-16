package com.apa.advice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;

public class adviceListDAO {

		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		private ResultSet rs;
		
		public adviceListDAO() {
			this.conn = DBUtil.open();
		}
	public ArrayList<adviceListDTO> listAdvice(HashMap<String, String> map) {
		
		  int begin = Integer.parseInt(map.get("begin"));
	      int end = Integer.parseInt(map.get("end"));
		//List.java
		try {
			
			
			String sql = String.format(
			        "SELECT * FROM (SELECT * FROM (" +
			        "    SELECT ROWNUM AS rnum, mq.*, d.departmentName, dt.doctorName, h.hospitalName, ma.counselAnswerContent " +
			        "    FROM tblMediCounselingQuestion mq " +
			        "    INNER JOIN tbldepartment d ON mq.departmentSeq = d.departmentSeq " +
			        "    LEFT OUTER JOIN tblmediCounselingAnswer ma ON mq.mediCounselQuestionSeq = ma.mediCounselQuestionSeq " +
			        "    LEFT OUTER JOIN tblDoctor dt ON ma.doctorSeq = dt.doctorSeq " +
			        "    LEFT OUTER JOIN tblHospital h ON dt.hospitalId = h.hospitalId " +
			        "    ORDER BY mq.regdate DESC" +
			        ")) " +
			        "WHERE rnum BETWEEN %d AND %d", begin, end);
			
			System.out.println(sql);
			

			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<adviceListDTO> list = new ArrayList<adviceListDTO>();

			while (rs.next()) {

				adviceListDTO dto = new adviceListDTO();

				dto.setDepartName(rs.getString("departmentName"));
				dto.setAdviceTitle(rs.getString("counselTitle"));
				dto.setAdviceContent(rs.getString("counselContent"));
				dto.setIsAnswer(rs.getString("isCounselAnswer"));
				dto.setAdviceDoctorName(rs.getString("doctorName"));
				dto.setAdviceHospitalName(rs.getString("hospitalName"));
				dto.setAdviceCounselAnswerContent(rs.getString("counselAnswerContent"));;
				

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
	public int add(adviceListDTO dto) {
		
		try {

			String sql = "insert into tblMediCounselingQuestion (mediCounselQuestionSeq, userSeq, departmentSeq, counselTitle, counselContent, isCounselAnswer, regdate) values (seqMediCounselingQuestion.nextVal, ?, ?, ?, ?,default,default)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getDepartSeq());
			pstat.setString(3, dto.getAdviceTitle()); 
			pstat.setString(4, dto.getAdviceContent()); 


			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	
	}
	public adviceListDTO get(String seq) {
		
		try {
			
			String sql = "select mc.*, u.userId from tblMediCounselingQuestion mc\r\n"
					+ "    inner join tblUser u\r\n"
					+ "        on mc.userSeq = u.userSeq\r\n"
					+ "            where mediCounselQuestionSeq = ?;";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				adviceListDTO dto = new adviceListDTO();
				
				dto.setAdviceSeq(rs.getString("MEDICOUNSELQUESTIONSEQ"));	
				dto.setAdviceTitle(rs.getString("COUNSELTITLE"));	
				dto.setAdviceContent(rs.getString("COUNSELCONTENT"));	
				dto.setAdviceRegdate(rs.getString("regdate"));	
				dto.setDepartSeq(rs.getString("DEPARTMENTSEQ"));	
				dto.setIsAnswer(rs.getString("ISCOUNSELANSWER"));	
				
				dto.setUserId(rs.getString("userId"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
