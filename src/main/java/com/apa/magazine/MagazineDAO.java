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

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public MagazineDAO() {
		this.conn = DBUtil.open();
	}

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

	public ArrayList<MagazineDTO> list(HashMap<String, String> map) {

		// queryNoParamListReturn

		try {

			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format(
					"SELECT * FROM (SELECT a.*, ROWNUM AS rnum FROM vwtblMagazine a %s) WHERE rnum BETWEEN %s AND %s",
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
