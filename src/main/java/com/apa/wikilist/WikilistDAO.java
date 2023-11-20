package com.apa.wikilist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.wikilist.WikilistDTO;

public class WikilistDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public WikilistDAO() {
		this.conn = DBUtil.open();
	}

	public int add(WikilistDTO dto) {
	
		//queryParamNoReturn
		try {

			String sql = "insert into tblWikilist (wikiSeq, diseaseName, diseaseExplanation, DiseaseCause, wikiSymptom, diagnosis, care)"
					+ "values (seqWiki.nextVal, ?, ?, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getDiseaseName());
			pstat.setString(2, dto.getDiseaseExplanation());
			pstat.setString(3, dto.getDiseaseCause());
			pstat.setString(4, dto.getWikiSymptom());
			pstat.setString(5, dto.getDiagnosis());
			pstat.setString(6, dto.getCare());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<WikilistDTO> list(HashMap<String, String> map) {
		
		//queryNoParamListReturn
		try {
			
			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}
			
			String sql = String.format(
					"SELECT * FROM (SELECT a.*, ROWNUM AS rnum FROM vwtblWikilist a %s) WHERE rnum BETWEEN %s AND %s",
					where, map.get("begin"), map.get("end"));

			// String sql = "select * from tblWikilist";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<WikilistDTO> list = new ArrayList<WikilistDTO>();
			
			while (rs.next()) {
				
				WikilistDTO dto = new WikilistDTO();
				
				dto.setWikiSeq(rs.getString("wikiSeq"));
				dto.setDiseaseName(rs.getString("diseaseName"));
				dto.setDiseaseExplanation(rs.getString("diseaseExplanation"));
				dto.setDiseaseCause(rs.getString("Cause"));
				dto.setWikiSymptom(rs.getString("wikiSymptom"));
				dto.setDiagnosis(rs.getString("diagnosis"));
				dto.setCare(rs.getString("care"));

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

			String sql = "select count(*) as cnt from tblWikilist";

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

	public WikilistDTO get(String wikiSeq) {
		
		//queryParamDTOReturn
		
		try {
			
			String sql = "select * from tblWikilist where wikiSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, wikiSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				WikilistDTO dto = new WikilistDTO();
				
				dto.setWikiSeq(rs.getString("wikiSeq"));
				dto.setDiseaseName(rs.getString("diseaseName"));
				dto.setDiseaseExplanation(rs.getString("diseaseExplanation"));
				dto.setDiseaseCause(rs.getString("Cause"));
				dto.setWikiSymptom(rs.getString("wikiSymptom"));
				dto.setDiagnosis(rs.getString("diagnosis"));
				dto.setCare(rs.getString("care"));
				
				return dto;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int edit(WikilistDTO dto) {
		
		return 0;
	}

	public int del(String wikiSeq) {
		
		try {

			String sql = "delete from tblWikilist where wikiSeq = ?";
			
			System.out.println(wikiSeq);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, wikiSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

}
