package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.PharmacyInfoDTO;
import com.apa.model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.model.ChildrenDTO;
import com.apa.model.CommunityStorageCommentDTO;
import com.apa.model.CommunityStorageDTO;
import com.apa.model.CommunityStorageViewDTO;
import com.apa.model.FavoriteDTO;
import com.apa.model.MediCheckupReservationDTO;
import com.apa.model.MediCounselQuestionDTO;
import com.apa.model.MediCounselQuestionViewDTO;
import com.apa.model.MediCounselingBoxDTO;
import com.apa.model.MediHistoryDTO;
import com.apa.model.MediHistoryViewDTO;
import com.apa.model.MediTestSaveDTO;
import com.apa.model.MediTestSaveViewDTO;
import com.apa.model.RegisterDTO;
import com.apa.model.ReviewDetailViewDTO;
import com.apa.model.ReviewInsertInfoDTO;
import com.apa.model.ReviewListDTO;
import com.apa.model.TagViewDTO;

public class UserDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public UserDAO() {
        this.conn = DBUtil.open();
    }

    public int userRegister(UserDTO dto) {
        try {
            String sql = "insert into tblUser(userseq, username, userssn, userid, userpw, usertel ,useraddress, useremail, userchild, usercautioncount, registerdate, isuserunregister) values (SEQUSER.nextval,?, ? , ?, ?, ?, ? , ?, ?, default, default, default)";



            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserName());
            pstat.setString(2, dto.getUserSsn());
            pstat.setString(3, dto.getUserId());
            pstat.setString(4, dto.getUserPw());
            pstat.setString(5, dto.getUserTel());
            pstat.setString(6, dto.getUserAddress());
            pstat.setString(7, dto.getUserEmail());
            pstat.setString(8, dto.getUserChild());

            return pstat.executeUpdate();

        } catch (Exception e) {
            System.out.println(" ");
            e.printStackTrace();
        }

        return 0;
    }

    public UserDTO login(UserDTO dto) {
            try {
                System.out.println(dto.getUserId());

                String sql = "select * from tblUser where UserId = ? and USerPw = ? and ISUSERUNREGISTER = 'n'";

                pstat = conn.prepareStatement(sql);
                pstat.setString(1, dto.getUserId());
                pstat.setString(2, dto.getUserPw());

                rs = pstat.executeQuery();

                if (rs.next()) {

                    UserDTO result = new UserDTO();

                    result.setUserId(rs.getString("userId"));
                    result.setUserName(rs.getString("userName"));

                    return result;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

	public ArrayList<PharmacyInfoDTO> pharmacylist() {
		try {
			
			String sql = "select * from vwPharmacyInfo where isdispense = 'y'";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<PharmacyInfoDTO> list = new ArrayList<PharmacyInfoDTO>();
			
			while (rs.next()) {
				
				PharmacyInfoDTO dto = new PharmacyInfoDTO();
				
				dto.setId(rs.getString("PHARMACYID"));
				dto.setName(rs.getString("PHARMACYNAME"));
				dto.setAddress(rs.getString("PHARMACYADDRESS"));
				dto.setEmail(rs.getString("PHARMACYEMAIL"));
				dto.setTel(rs.getString("PHARMACYTEL"));
				dto.setDispense(rs.getString("ISDISPENSE"));
				dto.setOpen(rs.getString("ISPHARMACY"));
				dto.setNight(rs.getString("ISPHARMARCYNIGHTCARE"));
				dto.setHoliday(rs.getString("ISPHARMARCYHOLIDAY"));
				dto.setDayoff(rs.getString("PHARMACYDAYOFF"));
				dto.setOpentime(rs.getString("OPENTIME"));
				dto.setClosetime(rs.getString("CLOSETIME"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

public UserDTO get(String seq) {
		
		try {
			
			String sql = "SELECT userSeq ,userName, userId, userPw, userSSN, userTel, userEmail, userAddress FROM tblUser WHERE userSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserSeq(rs.getInt("userSeq"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserPw(rs.getString("userPw"));
				dto.setUserSsn(rs.getString("userSSN"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserAddress(rs.getString("userAddress"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int edit(UserDTO dto) {

		try {
			String sql = "UPDATE tblUser SET userName = ?, userId = ?, userPw = ?, userSSN = ?, userTel = ?, userEmail = ?, userAddress = ? WHERE userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserName());
			pstat.setString(2, dto.getUserId());
			pstat.setString(3, dto.getUserPw());
			pstat.setString(4, dto.getUserSsn());
			pstat.setString(5, dto.getUserTel());
			pstat.setString(6, dto.getUserEmail());
			pstat.setString(7, dto.getUserAddress());
			pstat.setInt(8, dto.getUserSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return 0;
	}

	public int delete(String seq) {

		try {

			String sql = "UPDATE tblUser SET isUserUnRegister = 'y' WHERE userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<ChildrenDTO> listChildren(String seq) {

		try {
			
			String sql = "SELECT C.childSeq, C.userSeq, C.childName, C.childSSN, C.childTel, U.userChild FROM tblChild C\r\n"
					+ "INNER JOIN tblUser U \r\n"
					+ "	ON C.userSeq = U.userSeq \r\n"
					+ "		WHERE C.userSeq = ? AND C.isChildUnregister = 'n'";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ChildrenDTO> list = new ArrayList<ChildrenDTO>();
			
			while (rs.next()) {
				
				ChildrenDTO dto = new ChildrenDTO();
				
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setChildName(rs.getString("childName"));
				
				String cSSN[] = rs.getString("childSSN").split("-");
				
				dto.setChildSSNs(cSSN[0]);
				dto.setChildSSNe(cSSN[1].charAt(0) + "******");
				
				dto.setChildSSN(rs.getString("childSSN"));
				dto.setChildTel(rs.getString("childTel"));
				dto.setUserChild(rs.getString("userChild"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int editChild(HashMap<String, String> map) {

		try {
			String sql = "UPDATE tblChild SET childName = ?, childSSN = ?, childTel = ? WHERE childSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("name"));
			pstat.setString(2, map.get("ssn"));
			pstat.setString(3, map.get("tel"));
			pstat.setString(4, map.get("childSeq"));
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int deleteChild(String seq) {

		try {

			String sql = "UPDATE tblChild SET isChildUnregister = 'y' WHERE childSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int childInsert(ChildrenDTO dto) {

		try {

			String sql = "INSERT INTO tblChild (childSeq, userSeq, childName, childSSN, childTel) VALUES (seqChild.nextVal, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getChildName());
			pstat.setString(3, dto.getChildSSN());
			pstat.setString(4, dto.getChildTel());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<RegisterDTO> getRegister(String seq) {

		try {
			
			String sql = "SELECT H.hospitalName, D.doctorName, R.treatmentDate, R.symptom, R.mediWay FROM tblRegister R\r\n"
					+ "	INNER JOIN tblHospital H\r\n"
					+ "		ON R.hospitalId = H.hospitalId\r\n"
					+ "			INNER JOIN tblDoctor D\r\n"
					+ "				ON R.doctorSeq = D.doctorSeq\r\n"
					+ "					WHERE R.userseq = ? AND sysdate < R.treatmentdate";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<RegisterDTO> list = new ArrayList<RegisterDTO>();
			
			while (rs.next()) {
				
				RegisterDTO dto = new RegisterDTO();
				
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setSymptom(rs.getString("symptom"));
				dto.setMediWay(rs.getString("mediWay"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MediHistoryDTO> getMediHistory(String seq) {

		try {
			
			String sql = "SELECT DISTINCT\r\n"
					+ "MH.mediHistorySeq,\r\n"
					+ "CASE\r\n"
					+ "	WHEN R.childSeq IS NOT NULL THEN R.childSeq\r\n"
					+ "	ELSE -1\r\n"
					+ "END AS childSeq, \r\n"
					+ "H.hospitalName, D.doctorName, R.treatmentDate, MH.mediName, RV.reviewSeq FROM tblMediHistory MH\r\n"
					+ "INNER JOIN tblRegister R\r\n"
					+ "	ON MH.mediSeq = R.mediSeq \r\n"
					+ "INNER JOIN tblHospital H\r\n"
					+ "	ON H.hospitalId = R.hospitalId\r\n"
					+ "INNER JOIN tblDoctor D\r\n"
					+ "	ON D.doctorSeq = R.doctorSeq\r\n"
					+ "INNER JOIN tblChild C\r\n"
					+ "	ON C.userSeq = R.userSeq\r\n"
					+ "LEFT OUTER JOIN tblReview RV\r\n"
					+ "	ON RV.mediHistorySeq = mh.mediHistorySeq\r\n"
					+ "		WHERE R.userSeq = ? AND sysdate > R.treatmentDate\r\n"
					+ "			ORDER BY R.treatmentDate DESC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MediHistoryDTO> list = new ArrayList<MediHistoryDTO>();
			
			while (rs.next()) {
				
				MediHistoryDTO dto = new MediHistoryDTO();
				
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setMediHistorySeq(rs.getString("mediHistorySeq"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setChildSeq(rs.getString("childSeq"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setMediName(rs.getString("mediName"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public MediHistoryViewDTO getMediHistoryView(String seq) {

		try {
			
			String sql = "SELECT MH.mediHistorySeq, R.treatmentDate, U.userName, MH.mediName, MH.mediCode, H.hospitalName FROM tblMediHistory MH\r\n"
					+ "	INNER JOIN tblRegister R\r\n"
					+ "		ON MH.mediSeq = R.mediSeq \r\n"
					+ "			INNER JOIN tblHospital H\r\n"
					+ "				ON H.hospitalId = R.hospitalId\r\n"
					+ "					INNER JOIN tblDoctor D\r\n"
					+ "						ON D.doctorSeq = R.doctorSeq\r\n"
					+ "							INNER JOIN tblUser U\r\n"
					+ "								ON U.userSeq = R.userSeq\r\n"
					+ "									WHERE MH.mediHistorySeq = ? AND sysdate > R.treatmentDate";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MediHistoryViewDTO dto = new MediHistoryViewDTO();
				
				dto.setMediHistorySeq(rs.getString("mediHistorySeq"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				dto.setUserName(rs.getString("userName"));
				dto.setMediName(rs.getString("mediName"));
				dto.setMediCode(rs.getString("mediCode"));
				dto.setHospitalName(rs.getString("hospitalName"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getChildName(String cseq) {

		try {

			String sql = "SELECT childName FROM tblChild WHERE childSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("childName");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MediCheckupReservationDTO> getMediCheckupReservation(String seq) {

		try {
			
			String sql = "SELECT W.mediCheckupSeq, H.hospitalName, R.reservationDate, W.isCheckup FROM tblMediCheckupReservation R\r\n"
					+ "INNER JOIN tblCheckupWaiting W\r\n"
					+ "	ON R.mediCheckupSeq = W.mediCheckupSeq\r\n"
					+ "INNER JOIN tblHospital H\r\n"
					+ "	ON R.hospitalId = H.hospitalId\r\n"
					+ "WHERE R.userSeq = ?\r\n"
					+ "	ORDER BY R.reservationDate ASC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MediCheckupReservationDTO> list = new ArrayList<MediCheckupReservationDTO>();
			
			while (rs.next()) {
				
				MediCheckupReservationDTO dto = new MediCheckupReservationDTO();
				
				dto.setMediCheckupSeq(rs.getString("mediCheckupSeq"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setReservationDate(rs.getString("reservationDate"));
				dto.setIsCheckup(rs.getString("isCheckup"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		return null;
	}

	public ArrayList<FavoriteDTO> getFavorite(String seq) {

		try {
			
			String sql = "SELECT userSeq,\r\n"
					+ "	   H.hospitalName,\r\n"
					+ "	   D.departmentName,\r\n"
					+ "	   TO_CHAR(T.openTime, 'HH24:MI') AS openTime,\r\n"
					+ "	   TO_CHAR(T.closeTime, 'HH24:MI') AS closeTime,\r\n"
					+ "	   H.hospitalAddress,\r\n"
					+ "	   B.bookmarkSeq,\r\n"
					+ "	   H.hospitalid,\r\n"
					+ "CASE\r\n"
					+ "	WHEN TO_DATE(TO_CHAR(T.openTime, 'HH24:MI'), 'HH24:MI') > TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') THEN 0  \r\n"
					+ "	WHEN TO_DATE(TO_CHAR(T.openTime, 'HH24:MI'), 'HH24:MI') < TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') AND TO_DATE(TO_CHAR(T.closeTime, 'HH24:MI'), 'HH24:MI') > TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') THEN 1  \r\n"
					+ "	WHEN TO_DATE(TO_CHAR(T.closeTime, 'HH24:MI'), 'HH24:MI') < TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') THEN 2\r\n"
					+ "	WHEN TO_DATE(TO_CHAR(T.openTime, 'HH24:MI'), 'HH24:MI') = TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') THEN 1\r\n"
					+ "	WHEN TO_DATE(TO_CHAR(T.closeTime, 'HH24:MI'), 'HH24:MI') = TO_DATE(TO_CHAR(SYSDATE, 'HH24:MI'), 'HH24:MI') THEN 2\r\n"
					+ "END AS openCheck\r\n"
					+ "FROM tblBookmark B\r\n"
					+ "	INNER JOIN tblHospital H\r\n"
					+ "		ON H.hospitalId = B.hospitalId\r\n"
					+ "			INNER JOIN tblDepartment D\r\n"
					+ "				ON D.departmentSeq = H.departmentSeq\r\n"
					+ "					INNER JOIN tblOperatingTime T\r\n"
					+ "						ON T.hospitalId = H.hospitalId\r\n"
					+ "							WHERE userSeq = ?\r\n"
					+ "								ORDER BY H.hospitalAddress DESC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<FavoriteDTO> list = new ArrayList<FavoriteDTO>();
			
			while (rs.next()) {
				
				FavoriteDTO dto = new FavoriteDTO();
				
				dto.setHospitalId(rs.getString("hospitalid"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setOpenTime(rs.getString("openTime"));
				dto.setCloseTime(rs.getString("closeTime"));
				dto.setHospitalAddress(rs.getString("hospitalAddress"));
				dto.setOpenCheck(rs.getString("openCheck"));
				dto.setBookmarkSeq(rs.getString("bookmarkSeq"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int delFavor(String seq) {

		try {

			String sql = "DELETE FROM tblBookmark WHERE bookmarkSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public String getUserChild(String seq) {

		try {

			String sql = "SELECT userChild FROM tblUser WHERE userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getString("userChild");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int userChildUpdate(ChildrenDTO dto) {

		try {

			String sql = "UPDATE tblUser SET userChild = ? WHERE userSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserChild());
			pstat.setString(2, dto.getUserSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<MediTestSaveDTO> getMediTestSave(String seq) {

		try {
			
			String sql = "SELECT S.mediTestSaveSeq, T.mediTestName, TO_CHAR(S.testDate, 'YYYY-MM-DD HH24:MI') AS testTime FROM tblMediTestSave S\r\n"
					+ "	INNER JOIN tblMediTest T\r\n"
					+ "		ON S.mediTestSeq = T.mediTestSeq\r\n"
					+ "			WHERE S.userSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MediTestSaveDTO> list = new ArrayList<MediTestSaveDTO>();
			
			while (rs.next()) {
				
				MediTestSaveDTO dto = new MediTestSaveDTO();
				
				dto.setMediTestSaveSeq(rs.getString("mediTestSaveSeq"));
				dto.setMediTestName(rs.getString("mediTestName"));
				dto.setTestTime(rs.getString("testTime"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public MediTestSaveViewDTO getMediTestSaveView(String seq) {

		try {
			
			String sql = "SELECT S.testDate, T.mediTestName, R.mediTestResultContent, U.userName, S.mediTestTotalScore FROM tblMediTestSave S\r\n"
					+ "INNER JOIN tblMediTestResult R\r\n"
					+ "	ON S.mediTestSeq = R.mediTestSeq\r\n"
					+ "INNER JOIN tblMediTest T\r\n"
					+ "	ON T.mediTestSeq = S.mediTestSeq\r\n"
					+ "INNER JOIN tblUser U\r\n"
					+ "	ON S.userSeq = U.userSeq\r\n"
					+ "WHERE S.mediTestSaveSeq = ? AND (S.mediTestTotalScore >= R.minScore AND S.meditesttotalscore <= R.maxScore)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MediTestSaveViewDTO dto = new MediTestSaveViewDTO();
				
				dto.setMediTestName(rs.getString("mediTestName"));
				dto.setTestDate(rs.getString("testDate"));
				dto.setUserName(rs.getString("userName"));
				dto.setMediTestResultContent(rs.getString("mediTestResultContent"));
				dto.setMediTestTotalScore(rs.getString("mediTestTotalScore"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<MediCounselQuestionDTO> getMediCounselQuestion(String seq) {

		try {
			
			String sql = "SELECT Q.mediCounselQuestionSeq, A.mediCounselAnswerSeq, D.departmentName, Q.counselTitle, TO_CHAR(Q.regdate, 'YYYY-MM-DD HH24:MI') AS regdate FROM tblMediCounselingQuestion Q\r\n"
					+ "INNER JOIN tblDepartment D\r\n"
					+ "	ON Q.departmentSeq = D.departmentSeq\r\n"
					+ "LEFT OUTER JOIN tblMediCounselingAnswer A\r\n"
					+ "	ON A.mediCounselQuestionSeq = Q.mediCounselQuestionSeq\r\n"
					+ "WHERE Q.userSeq = ?\r\n"
					+ "	ORDER BY Q.regdate DESC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MediCounselQuestionDTO> list = new ArrayList<MediCounselQuestionDTO>();
			
			while (rs.next()) {
				
				MediCounselQuestionDTO dto = new MediCounselQuestionDTO();
				
				dto.setMediCounselQuestionSeq(rs.getString("mediCounselQuestionSeq"));
				dto.setMediCounselAnswerSeq(rs.getString("mediCounselAnswerSeq"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setCounselTitle(rs.getString("counselTitle"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public MediCounselQuestionViewDTO getMediCounselQuestionView(String seq) {

		try {
			
			String sql = "SELECT Q.mediCounselQuestionSeq,\r\n"
					+ "	   U.userId,\r\n"
					+ "	   D.departmentName,\r\n"
					+ "	   Q.counselTitle,\r\n"
					+ "	   Q.counselContent,\r\n"
					+ "	   A.counselAnswerContent,\r\n"
					+ "	   DO.doctorName,\r\n"
					+ "	   H.hospitalName,\r\n"
					+ "	   TO_CHAR(Q.regdate, 'YYYY-MM-DD HH24:MI') AS regdate,\r\n"
					+ "	   TO_CHAR(A.answerTime, 'YYYY-MM-DD HH24:MI') AS answerTime\r\n"
					+ "FROM tblMediCounselingQuestion Q\r\n"
					+ "INNER JOIN tblDepartment D\r\n"
					+ "	ON Q.departmentSeq = D.departmentSeq\r\n"
					+ "INNER JOIN tblUser U\r\n"
					+ "	ON U.userSeq = Q.userSeq\r\n"
					+ "LEFT OUTER JOIN tblMediCounselingAnswer A\r\n"
					+ "	ON A.mediCounselQuestionSeq = Q.mediCounselQuestionSeq\r\n"
					+ "LEFT OUTER JOIN tblDoctor DO\r\n"
					+ "	ON A.doctorSeq = DO.doctorseq\r\n"
					+ "LEFT OUTER JOIN tblHospital H\r\n"
					+ "	ON DO.hospitalId = H.hospitalId\r\n"
					+ "WHERE Q.mediCounselQuestionSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MediCounselQuestionViewDTO dto = new MediCounselQuestionViewDTO();
				
				dto.setMediCounselQuestionSeq(rs.getString("mediCounselQuestionSeq"));
				dto.setUserId(rs.getString("userId"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setCounselTitle(rs.getString("counselTitle"));
				dto.setCounselContent(rs.getString("counselContent"));
				dto.setCounselAnswerContent(rs.getString("counselAnswerContent"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setAnswerTime(rs.getString("answerTime"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int deleteAdvice(String seq) {

		try {

			String sql = "DELETE FROM tblMediCounselingQuestion WHERE mediCounselQuestionSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public void deleteAdviceAnswer(String seq) {

		try {

			String sql = "DELETE FROM tblMediCounselingAnswer WHERE mediCounselQuestionSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteStorageAdvice(String aseq) {

		try {

			String sql = "DELETE FROM tblMediCounselingBox WHERE mediCounselAnswerSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, aseq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<MediCounselingBoxDTO> getMediCounselBox(String seq) {

		try {
			
			String sql = "SELECT B.mediCounselBoxSeq, Q.mediCounselQuestionSeq, D.departmentName, Q.counseltitle, TO_CHAR(Q.regdate, 'YYYY-MM-DD HH24:MI') AS regdate FROM tblMediCounselingBox B\r\n"
					+ "INNER JOIN tblMediCounselingAnswer A\r\n"
					+ "	ON B.mediCounselAnswerSeq = A.mediCounselAnswerSeq\r\n"
					+ "INNER JOIN tblMediCounselingQuestion Q\r\n"
					+ "	ON A.mediCounselQuestionSeq = Q.mediCounselQuestionSeq\r\n"
					+ "INNER JOIN tblDepartment D\r\n"
					+ "	ON Q.departmentSeq = D.departmentSeq\r\n"
					+ "WHERE B.userSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MediCounselingBoxDTO> list = new ArrayList<MediCounselingBoxDTO>();
			
			while (rs.next()) {
				
				MediCounselingBoxDTO dto = new MediCounselingBoxDTO();
				
				dto.setMediCounselBoxSeq(rs.getString("mediCounselBoxSeq"));
				dto.setMediCounselQuestionSeq(rs.getString("mediCounselQuestionSeq"));
				dto.setDepartmentName(rs.getString("departmentName"));
				dto.setCounselTitle(rs.getString("counselTitle"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int deleteAdviceBox(String bseq) {

		try {

			String sql = "DELETE FROM tblMediCounselingBox WHERE mediCounselBoxSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bseq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<CommunityStorageDTO> getCommnuityStorage(String seq) {

		try {
			
			String sql = "SELECT C.communitySeq, C.communityTitle, C.communityCommentCount, C.communityLikeCount, TO_CHAR(C.communityDate, 'YYYY-MM-DD HH24:MI') AS communityDate FROM tblCommunity C\r\n"
					+ "INNER JOIN tblUser U\r\n"
					+ "	ON C.userSeq = U.userSeq\r\n"
					+ "WHERE C.userSeq = ?\r\n"
					+ "	ORDER BY c.communityDate DESC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<CommunityStorageDTO> list = new ArrayList<CommunityStorageDTO>();
			
			while (rs.next()) {
				
				CommunityStorageDTO dto = new CommunityStorageDTO();
				
				dto.setCommunitySeq(rs.getString("communitySeq"));
				dto.setCommunityTitle(rs.getString("communityTitle"));
				dto.setCommunityCommentCount(rs.getString("communityCommentCount"));
				dto.setCommunityLikeCount(rs.getString("communityLikeCount"));
				dto.setCommunityDate(rs.getString("communityDate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public CommunityStorageViewDTO getCommunityStorageView(String seq) {

		try {
			
			String sql = "SELECT U.userSeq, C.communitySeq, C.communityTitle, C.communityContent, U.userID, TO_CHAR(C.communityDate, 'YYYY-MM-DD HH24:MI') AS communityDate, C.communityCommentCount, C.communityLikeCount FROM tblCommunity C\r\n"
					+ "INNER JOIN tblUser U\r\n"
					+ "	ON C.userSeq = U.userSeq\r\n"
					+ "WHERE C.communitySeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				CommunityStorageViewDTO dto = new CommunityStorageViewDTO();
				
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setCommunitySeq(rs.getString("communitySeq"));
				dto.setCommunityTitle(rs.getString("communityTitle"));
				dto.setCommunityContent(rs.getString("communityContent"));
				dto.setUserID(rs.getString("userID"));
				dto.setCommunityDate(rs.getString("communityDate"));
				dto.setCommunityCommentCount(rs.getString("communityCommentCount"));
				dto.setCommunityLikeCount(rs.getString("communityLikeCount"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<CommunityStorageCommentDTO> getCommunityStorageComment(String seq) {

		try {
			
			String sql = "SELECT CC.communityCommentContent, TO_CHAR(CC.communityCommentDate, 'YYYY-MM-DD HH24:MI') AS communityCommentDate, U.userId FROM tblCommunityComment CC\r\n"
					+ "INNER JOIN tblCommunity C\r\n"
					+ "	ON C.communitySeq = CC.communitySeq\r\n"
					+ "INNER JOIN tblUser U \r\n"
					+ "	ON CC.userSeq = U.userSeq\r\n"
					+ "WHERE C.communitySeq = ?\r\n"
					+ "	ORDER BY communityCommentDate DESC";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<CommunityStorageCommentDTO> list = new ArrayList<CommunityStorageCommentDTO>();
			
			while (rs.next()) {
				
				CommunityStorageCommentDTO dto = new CommunityStorageCommentDTO();
				
				dto.setCommunityCommentContent(rs.getString("communityCommentContent"));
				dto.setCommunityCommentDate(rs.getString("communityCommentDate"));
				dto.setUserId(rs.getString("userId"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int updateCommunity(CommunityStorageViewDTO dto) {

		try {

			String sql = "UPDATE tblCommunity SET communityTitle = ?, communityContent = ? WHERE communitySeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCommunityTitle());
			pstat.setString(2, dto.getCommunityContent());
			pstat.setString(3, dto.getCommunitySeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public void deleteComment(String cseq) {

		try {

			String sql = "DELETE FROM tblCommunityComment WHERE communitySeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int deleteCommunity(String cseq) {

		try {

			String sql = "DELETE FROM tblCommunity WHERE communitySeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ReviewInsertInfoDTO getReviewInsertInfo(String seq) {

		try {
			
			String sql = "SELECT H.hospitalName, DO.doctorName, R.mediWay, TO_CHAR(R.treatmentDate, 'YYYY-MM-DD HH24:MI') AS treatmentDate FROM tblMediHistory MH\r\n"
					+ "INNER JOIN tblRegister R\r\n"
					+ "	ON R.mediSeq = MH.mediSeq\r\n"
					+ "INNER JOIN tblHospital H\r\n"
					+ "	ON H.hospitalId = R.hospitalId\r\n"
					+ "INNER JOIN tblDoctor DO\r\n"
					+ "	ON DO.doctorSeq = R.doctorSeq\r\n"
					+ "		WHERE mediHistorySeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ReviewInsertInfoDTO dto = new ReviewInsertInfoDTO();
				
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int insertReview(String seq, String content) {

		try {

			String sql = "INSERT INTO tblReview (reviewSeq, mediHistorySeq, reviewContent) VALUES (seqReview.nextVal, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, content);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public String selectLastReview() {

		try {

			String sql = "SELECT max(reviewSeq) AS reviewSeq FROM tblReview";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {

				return rs.getString("reviewSeq");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int insertTag(String rseq, String tag) {

		try {

			String sql = "INSERT INTO tblReviewTag (reviewTagSeq, reviewSeq, tagSeq) VALUES (seqReviewTag.nextVal, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, rseq);
			pstat.setString(2, tag);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<ReviewListDTO> getReviewList(String seq) {

		try {
			
			String sql = "SELECT RV.reviewSeq, H.hospitalName, RV.reviewContent, TO_CHAR(RG.treatmentDate, 'YYYY-MM-DD HH24:MI') AS treatmentDate, RV.isReviewDelete FROM tblReview RV\r\n"
					+ "INNER JOIN tblMediHistory MH\r\n"
					+ "	ON RV.mediHistorySeq = MH.mediHistorySeq\r\n"
					+ "INNER JOIN tblRegister RG\r\n"
					+ "	ON RG.mediSeq = MH.mediSeq\r\n"
					+ "INNER JOIN tblHospital H\r\n"
					+ "	ON RG.hospitalId = H.hospitalId\r\n"
					+ "INNER JOIN tblDoctor DO\r\n"
					+ "	ON DO.doctorSeq = RG.doctorSeq\r\n"
					+ "WHERE RG.userSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ReviewListDTO> list = new ArrayList<ReviewListDTO>();
			
			while (rs.next()) {
				
				ReviewListDTO dto = new ReviewListDTO();
				
				dto.setIsReviewDelete(rs.getString("isReviewDelete"));
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setReviewContent(rs.getString("reviewContent"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public ReviewDetailViewDTO getReviewDetailView(String seq) {

		try {
			
			String sql = "SELECT RV.reviewSeq, H.hospitalName, RV.reviewContent, DO.doctorName, RG.mediWay,TO_CHAR(RG.treatmentDate, 'YYYY-MM-DD HH24:MI') AS treatmentDate FROM tblReview RV\r\n"
					+ "INNER JOIN tblMediHistory MH\r\n"
					+ "	ON RV.mediHistorySeq = MH.mediHistorySeq\r\n"
					+ "INNER JOIN tblRegister RG\r\n"
					+ "	ON RG.mediSeq = MH.mediSeq\r\n"
					+ "INNER JOIN tblHospital H\r\n"
					+ "	ON RG.hospitalId = H.hospitalId\r\n"
					+ "INNER JOIN tblDoctor DO\r\n"
					+ "	ON DO.doctorSeq = RG.doctorSeq\r\n"
					+ "WHERE RV.reviewSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ReviewDetailViewDTO dto = new ReviewDetailViewDTO();
				
				dto.setReviewSeq(rs.getString("reviewSeq"));
				dto.setHospitalName(rs.getString("hospitalName"));
				dto.setReviewContent(rs.getString("reviewContent"));
				dto.setDoctorName(rs.getString("doctorName"));
				dto.setMediWay(rs.getString("mediWay"));
				dto.setTreatmentDate(rs.getString("treatmentDate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<TagViewDTO> getTagView(String seq) {

		try {
			
			String sql = "SELECT T.tag, T.tagType FROM tblReviewTag RT\r\n"
					+ "INNER JOIN tblTag T\r\n"
					+ "	ON	RT.tagSeq = T.tagSeq\r\n"
					+ "WHERE reviewSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<TagViewDTO> list = new ArrayList<TagViewDTO>();
			
			while (rs.next()) {
				
				TagViewDTO dto = new TagViewDTO();
				
				dto.setTag(rs.getString("tag"));
				dto.setTagType(rs.getString("tagType"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public void delReviewTag(String seq) {

		try {

			String sql = "DELETE FROM tblReviewTag WHERE reviewSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int deleteReview(String seq) {
		
		try {

			String sql = "UPDATE tblReview SET isReviewDelete = 'y' WHERE reviewSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int insertDocument(String seq, String content) {

		try {

			String sql = "INSERT INTO tblRequestDocument (reqDocumentSeq, mediHistorySeq, reqDocumentContent, status) VALUES (seqRequestDocument.nextVal, ?, ?, DEFAULT)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, content);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}