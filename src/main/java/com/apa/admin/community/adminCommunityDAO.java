package com.apa.admin.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;


/**
 * @author 최진희
 * 관리자가 커뮤니티 관리를 위해 데이터베이스에 액세스하는 DAO 클래스입니다.
 */
public class adminCommunityDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	/**
     * 생성자: 데이터베이스 연결을 초기화합니다.
     */

	public adminCommunityDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 커뮤니티 목록을 조회하는 메서드입니다.
     *
     * @param map 페이지 범위 및 검색 조건을 담은 HashMap
     * @return 커뮤니티 목록을 담은 ArrayList
     */
	public ArrayList<adminCommunityDTO> list(HashMap<String, String> map) {
		int begin = Integer.parseInt(map.get("begin"));
		int end = Integer.parseInt(map.get("end"));
		try {

			String where = "";

			if (map.get("search").equals("y")) {
				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			String sql = String.format("select * from (\r\n"
					+ "    select * from(select c.*, rownum as rnum, u.userId \r\n" + "        from tblCommunity c \r\n"
					+ "            inner join tblUser u \r\n" + "                on c.userSeq=u.userSeq \r\n"
					+ "                    left outer join tblreportbox rb \r\n"
					+ "                        on c.communityseq = rb.communityseq \r\n"
					+ "                            where rb.isreportstate is null or rb.isreportstate in ('거절', '접수')) %s)\r\n"
					+ "where rnum between %s and %s", where, begin, end);

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<adminCommunityDTO> list = new ArrayList<adminCommunityDTO>();

			while (rs.next()) {

				adminCommunityDTO dto = new adminCommunityDTO();

				dto.setCommunitySeq(rs.getString("communitySeq"));
				dto.setSubject(rs.getString("communityTitle"));
				dto.setId(rs.getString("userId"));
				dto.setRegdate(rs.getString("communityDate"));
				dto.setCct(rs.getInt("communityCommentCount"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 총 커뮤니티 목록의 수를 조회하는 메서드입니다.
     *
     * @return 총 커뮤니티 목록의 수
     */
	public int getTotalCount() {
		try {

			String sql = "select count(*) as cnt from tblCommunity c inner join tblUser u on c.userSeq=u.userSeq left outer join tblreportbox rb\r\n"
					+ "        on c.communityseq = rb.communityseq";

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
     * 특정 커뮤니티를 조회하는 메서드입니다.
     *
     * @param seq 조회할 커뮤니티의 일련번호
     * @return 조회된 커뮤니티의 데이터를 담은 DTO 객체
     */
	public adminCommunityDTO get(String seq) {

		try {

			String sql = "select c.*, u.userId from tblCommunity c inner join tblUser u  on c.userSeq = u.userSeq where communitySeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				adminCommunityDTO dto = new adminCommunityDTO();

				dto.setCommunitySeq(rs.getString("communitySeq"));
				dto.setSubject(rs.getString("communityTitle"));
				dto.setContent(rs.getString("COMMUNITYCONTENT"));
				dto.setRegdate(rs.getString("communityDate"));
				dto.setId(rs.getString("userId"));
				dto.setLikeCount(rs.getInt("communityLikeCount"));

				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
     * 특정 커뮤니티에 대한 댓글 목록을 조회하는 메서드입니다.
     *
     * @param communitySeq 조회할 커뮤니티의 일련번호
     * @return 댓글 목록을 담은 ArrayList
     */
	public ArrayList<adminCommentDTO> listComment(String communitySeq) {
		try {

			String sql = "select cc.*, (select userId from tblUser where userSeq = cc.userSeq) as userId from tblCommunityComment cc where communitySeq=? order by communityCommentDate desc";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, communitySeq);

			rs = pstat.executeQuery();

			ArrayList<adminCommentDTO> list = new ArrayList<adminCommentDTO>();

			while (rs.next()) {

				adminCommentDTO dto = new adminCommentDTO();

				dto.setCommentSeq(rs.getString("communityCommentSeq"));
				dto.setCommentContent(rs.getString("communityCommentContent"));
				dto.setCommentId(rs.getString("userId"));
				dto.setCommentRegdate(rs.getString("communityCommentDate"));
				dto.setCommunitySeq(rs.getString("communityCommentSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
     * 특정 댓글을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 댓글의 일련번호
     * @return 삭제된 레코드의 수
     */
	public int del(String seq) {
		try {

			String sql = "delete from tblCommunityComment where communityCommentSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	/**
     * 특정 커뮤니티에 대한 모든 댓글을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 커뮤니티의 일련번호
     */
	public void delCommentAll(String seq) {
		//queryParamNoReturn
		try {
			
			String sql = "delete from tblCommunityComment where communitySeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
