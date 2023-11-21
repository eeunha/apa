package com.apa.magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.magazine.MagazineDTO;

public class MagazineDAO {

	/**
	 * @author 안대명
	 * 
	 * Magazine 테이블과 관련된 데이터베이스 작업을 담당하는 DAO 클래스입니다.
	 */
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
     * MagazineDAO 클래스 생성자입니다.
     * 데이터베이스 연결을 초기화합니다.
     */
	
	public MagazineDAO() {
		this.conn = DBUtil.open();
	}
	
	/**
     * 새로운 Magazine 데이터를 추가합니다.
     *
     * @param dto 추가할 MagazineDTO 객체
     * @return 데이터베이스에 데이터 추가 결과 (성공 시 1, 실패 시 0)
     */

	public int add(MagazineDTO dto) {

		// queryParamNoReturn
		try {

			String sql = "insert into tblMagazine (magazineSeq, magazineTitle, magazineSubTitle, magazineContent, magazineDate)"
					+ "values (seqMagazine.nextVal, ?, ?, ?, sysdate)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMagazineTitle());
			pstat.setString(2, dto.getMagazineSubTitle());
			pstat.setString(3, dto.getMagazineContent());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
     * Magazine 목록을 조회합니다.
     *
     * @param map 조회 조건이 담긴 HashMap 객체
     * @return 조회된 MagazineDTO 객체들의 ArrayList
     */

	public ArrayList<MagazineDTO> list(HashMap<String, String> map) {

		// queryNoParamListReturn

		try {

			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"SELECT * FROM (SELECT a.*, ROWNUM AS rnum FROM vwMagazine a %s) WHERE rnum BETWEEN %s AND %s",
					where, map.get("begin"), map.get("end"));

			// String sql = "select * from tblMagazine";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<MagazineDTO> list = new ArrayList<MagazineDTO>();

			while (rs.next()) {

				MagazineDTO dto = new MagazineDTO();

				dto.setMagazineSeq(rs.getString("magazineSeq"));
				dto.setMagazineTitle(rs.getString("magazineTitle"));
				dto.setMagazineSubTitle(rs.getString("magazineSubTitle"));
				dto.setMagazineContent(rs.getString("magazineContent"));
				dto.setMagazineDate(rs.getString("magazineDate"));
				dto.setMagazineReadcount(rs.getInt("magazineReadcount"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	 /**
     * Magazine 데이터의 총 개수를 조회합니다.
     *
     * @return Magazine 데이터의 총 개수
     */

	public int getTotalCount() {

		try {

			String sql = "select count(*) as cnt from tblMagazine";

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
	
	
	 /**
     * 특정 Magazine의 정보를 조회합니다.
     *
     * @param magazineSeq 조회할 Magazine의 시퀀스 번호
     * @return 조회된 MagazineDTO 객체
     */

	public MagazineDTO get(String magazineSeq) {

		// queryParamDTOReturn
		try {

			String sql = "select * from tblMagazine where magazineSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, magazineSeq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				MagazineDTO dto = new MagazineDTO();

				dto.setMagazineSeq(rs.getString("magazineSeq"));
				dto.setMagazineTitle(rs.getString("magazineTitle"));
				dto.setMagazineSubTitle(rs.getString("magazineSubTitle"));
				dto.setMagazineContent(rs.getString("magazineContent"));
				dto.setMagazineDate(rs.getString("magazineDate"));
				dto.setMagazineReadcount(rs.getInt("magazineReadcount"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
     * Magazine 데이터를 수정합니다.
     *
     * @param dto 수정할 MagazineDTO 객체
     * @return 데이터베이스에 수정 결과 (성공 시 1, 실패 시 0)
     */

	public int edit(MagazineDTO dto) {

		// queryParamNoReturn
		try {

			String sql = "update tblMagazine set magazineTitle = ?, magazineSubTitle = ?,"
					+ "magazineContent = ? where magazineSeq = ?";
			
			System.out.println(dto.getMagazineSubTitle());

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getMagazineTitle());
			pstat.setString(2, dto.getMagazineSubTitle());
			pstat.setString(3, dto.getMagazineContent());
			pstat.setString(4, dto.getMagazineSeq());

			return pstat.executeUpdate();

		} catch (Exception e ) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
     * 특정 Magazine 데이터를 삭제합니다.
     *
     * @param magazineSeq 삭제할 Magazine의 시퀀스 번호
     * @return 데이터베이스에서 삭제 결과 (성공 시 1, 실패 시 0)
     */

	public int del(String magazineSeq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblMagazine where magazineSeq = ?";
			
			System.out.println(magazineSeq);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, magazineSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
     * Magazine의 조회수를 증가시킵니다.
     *
     * @param magazineSeq 조회수를 증가시킬 Magazine의 시퀀스 번호
     */

	public void updateMagazineReadcount(String magazineSeq) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblMagazine set magazineReadcount = magazineReadcount + 1 where magazineSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, magazineSeq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
