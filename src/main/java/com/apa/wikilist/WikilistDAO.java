package com.apa.wikilist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;
import com.apa.wikilist.WikilistDTO;

/**
 * @author 안대명
 * 
 * 위키 리스트 데이터에 대한 데이터 접근 객체 클래스입니다.
 */
public class WikilistDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
     * 기본 생성자 - DB 연결을 위해 DBUtil 클래스의 open 메서드를 호출하여 연결합니다.
     */
    public WikilistDAO() {
        this.conn = DBUtil.open();
    }

    /**
     * 새로운 위키 정보를 추가합니다.
     *
     * @param dto 추가할 위키 정보를 담은 WikilistDTO 객체
     * @return 데이터베이스에 삽입된 행의 수
     */
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

    /**
     * 지정된 조건에 따라 위키 리스트를 조회합니다.
     *
     * @param map 조회 조건을 담은 HashMap 객체
     * @return 조회된 위키 리스트를 담은 ArrayList 객체
     */
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

    /**
     * 위키 리스트의 총 개수를 반환합니다.
     *
     * @return 위키 리스트의 총 개수
     */
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

    /**
     * 주어진 위키 시퀀스에 해당하는 위키 정보를 반환합니다.
     *
     * @param wikiSeq 위키 시퀀스
     * @return 위키 시퀀스에 해당하는 위키 정보를 담은 WikilistDTO 객체
     */
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
    /**
     * 위키 정보를 수정합니다.
     *
     * @param dto 수정할 위키 정보를 담은 WikilistDTO 객체
     * @return 데이터베이스에 적용된 행의 수
     */
public int edit(WikilistDTO dto) {
	
	return 0;
}

    /**
     * 주어진 위키 시퀀스에 해당하는 위키 정보를 삭제합니다.
     *
     * @param wikiSeq 위키 시퀀스
     * @return 데이터베이스에 적용된 행의 수
     */
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