package com.apa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.MagazineDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.model.PharmacyInfoDTO;
import com.apa.model.SelfsymtomDTO;
import com.apa.DBUtil;

public class MainDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MainDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<MediQuestionDTO> list() {
		ArrayList<MediQuestionDTO> list = new ArrayList<MediQuestionDTO>();
		
		try {
			String sql = "select * from (select mcq.medicounselquestionseq, mcq.counseltitle, mcq.counselcontent, mcq.iscounselanswer, mcq.regdate from tblmedicounselingquestion mcq  order by mcq.regdate desc) where ROWNUM <= 3";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			//rs == 메모 목록
			
			//rs > list
			while (rs.next()) {
				
				//레코드 1줄 > MemoDTO 1개
				MediQuestionDTO dto = new MediQuestionDTO();
				
				dto.setSeq(rs.getString("medicounselquestionseq"));
				dto.setTitle(rs.getString("counseltitle"));
				dto.setContent(rs.getString("counselcontent"));
				dto.setAnswer(rs.getString("iscounselanswer"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<MagazineDTO> magazinelist() {
		ArrayList<MagazineDTO> list = new ArrayList<MagazineDTO>();
		
		try {
			String sql = "select * from (select * from tblmagazine order by tblmagazine.magazinedate desc) WHERE ROWNUM <= 3";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			//rs == 메모 목록
			
			//rs > list
			while (rs.next()) {
				
				//레코드 1줄 > MemoDTO 1개
				MagazineDTO dto = new MagazineDTO();
				
				dto.setSeq(rs.getString("MAGAZINESEQ"));
				dto.setTitle(rs.getString("MAGAZINETITLE"));
				dto.setSubtitle(rs.getString("MAGAZINESUBTITLE"));
				dto.setContent(rs.getString("MAGAZINECONTENT"));
				dto.setRegdate(rs.getString("MAGAZINEDATE"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<SelfsymtomDTO> symtomlist() {
		try {
			
			String sql = "select * from tblselfsymtom";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<SelfsymtomDTO> list = new ArrayList<SelfsymtomDTO>();
			
			while (rs.next()) {
				
				SelfsymtomDTO dto = new SelfsymtomDTO();
				
				dto.setSeq(rs.getInt("selfsymtomseq"));
				dto.setName(rs.getString("selfsymtomname"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<HospitalInfoDTO> hospitallist(String seq1, String seq2, String seq3, String seq4, String seq5, String seq6, String seq7, String seq8) {
		try {
			//System.out.println(seq1+","+seq2+","+seq3+","+seq4+","+seq5+","+seq6+","+seq7+","+seq8);
			String sql = "select * from vwhospitalinfo vhi where vhi.departmentname = (select * from (select d.departmentname from tblselfsymtom ss inner join tblsymtomdisease sd on ss.selfsymtomseq = sd.selfsymtomseq inner join tbldiagnosisdisease dd on sd.diseaseseq = dd.diseaseseq inner join tbldepartment d on dd.departmentseq = d.departmentseq where ss.selfsymtomseq in (?, ?, ?, ?, ?, ?, ?, ?) group by d.departmentname order by count(dd.diseasename) desc) where rownum <= 1 and vhi.ishospital = 'y')";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq1);
			pstat.setString(2,seq2);
			pstat.setString(3,seq3);
			pstat.setString(4,seq4);
			pstat.setString(5,seq5);
			pstat.setString(6,seq6);
			pstat.setString(7,seq7);
			pstat.setString(8,seq8);
			
			rs = pstat.executeQuery();
			
			ArrayList<HospitalInfoDTO> infolist = new ArrayList<HospitalInfoDTO>();
			
			while (rs.next()) {
				
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
				
				
				infolist.add(dto);
				
			}
			
			return infolist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HospitalInfoDTO hospitalinfo(String seq) {
		
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
	

	public ArrayList<HospitalInfoDTO> deptnameslist(String seq) {
		try {
			String sql = "select dept.departmentname, d.hospitalid from tbldoctor d inner join tbldepartment dept on d.departmentseq = dept.departmentseq where d.hospitalid = ? group by dept.departmentname, d.hospitalid";
				
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);	
				
			rs = pstat.executeQuery();
			
			ArrayList<HospitalInfoDTO> list = new ArrayList<HospitalInfoDTO>();
			
			while (rs.next()) {
				
				HospitalInfoDTO dto = new HospitalInfoDTO();
					
					dto.setId(rs.getString("hospitalid"));
					dto.setDeptnames(rs.getString("departmentname"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PharmacyInfoDTO pharmacyInfo(String seq) {
try {
			
			String sql = "select * from vwPharmacyInfo where vwpharmacyinfo.pharmacyid = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
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
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<HospitalInfoDTO> hospitallistmain() {
		try {
			
			String sql = "select * from vwhospitalinfo where ishospital = 'y'";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<HospitalInfoDTO> list = new ArrayList<HospitalInfoDTO>();
			
			while (rs.next()) {
				
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
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
