package com.apa.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;

/**
 * @author 최진희
 * 커뮤니티 게시판의 데이터베이스 연동을 처리하는 DAO 클래스입니다.
 */
public class communityDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
		
	public communityDAO() {
		this.conn = DBUtil.open();
	}	
    /**
     * 커뮤니티 게시판의 게시물 목록을 가져오는 메서드입니다.
     *
     * @param map 검색 조건 및 페이징을 위한 정보를 담고 있는 {@code HashMap} 객체
     * @return 게시물 목록을 담은 {@code ArrayList<communityDTO>} 객체
     */	
	
	public ArrayList<communityDTO> list(HashMap<String, String> map) {
			int begin = Integer.parseInt(map.get("begin"));
			int end = Integer.parseInt(map.get("end"));

			try {

				String where = "";

				if (map.get("search").equals("y")) {
					where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
				}

//				String sql = String.format(
//						"select * from(select c.*, rownum as rnum, u.userId from tblCommunity c inner join tblUser u on c.userSeq=u.userSeq left outer join tblreportbox rb on c.communityseq = rb.communityseq where rb.isreportstate is null or rb.isreportstate in ('거절', '접수') %s) where rnum between %s and %s",
//						where, begin, end);
				String sql = String.format("select * from (\r\n"
						+ "    select * from(select c.*, rownum as rnum, u.userId \r\n"
						+ "        from tblCommunity c \r\n"
						+ "            inner join tblUser u \r\n"
						+ "                on c.userSeq=u.userSeq \r\n"
						+ "                    left outer join tblreportbox rb \r\n"
						+ "                        on c.communityseq = rb.communityseq \r\n"
						+ "                            where rb.isreportstate is null or rb.isreportstate in ('거절', '접수')) %s)\r\n"
						+ "where rnum between %s and %s", where, begin, end);
			
				

				stat = conn.createStatement();
				rs = stat.executeQuery(sql);

				ArrayList<communityDTO> list = new ArrayList<communityDTO>();

				while (rs.next()) {

					communityDTO dto = new communityDTO();

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
     * 전체 게시물 수를 반환하는 메서드입니다.
     *
     * @return 전체 게시물 수
     */
	public int getTotalCount() {
		
		try {

			String sql = "select count(*) as cnt from tblCommunity c inner join tblUser u on c.userSeq=u.userSeq left outer join tblreportbox rb\r\n"
					+ "        on c.communityseq = rb.communityseq\r\n"
					+ "    where rb.isreportstate is null or rb.isreportstate in ('거절', '접수')";

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
     * 특정 게시물의 상세 정보를 가져오는 메서드입니다.
     *
     * @param seq 조회할 게시물의 번호
     * @return 게시물의 상세 정보를 담은 {@code communityDTO} 객체
     */
	public communityDTO get(String seq) {
		
		try {
			
			String sql = "select c.*, u.userId from tblCommunity c inner join tblUser u  on c.userSeq = u.userSeq where communitySeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				communityDTO dto = new communityDTO();
				
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
     * 특정 게시물에 대한 댓글 목록을 가져오는 메서드입니다.
     *
     * @param communitySeq 댓글을 가져올 게시물의 번호
     * @return 댓글 목록을 담은 {@code ArrayList<CommentDTO>} 객체
     */
	
	public ArrayList<CommentDTO> listComment(String communitySeq) {
		try {
			
			String sql = "select cc.*, (select userId from tblUser where userSeq = cc.userSeq) as userId from tblCommunityComment cc where communitySeq=? order by communityCommentDate desc";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, communitySeq);
			
			rs = pstat.executeQuery();	
			
			ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
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
     * 주어진 댓글 정보를 이용하여 새로운 댓글을 추가하는 메서드입니다.
     *
     * @param dto 댓글 정보를 담은 {@code CommentDTO} 객체
     * @return 데이터베이스에 추가된 행 수
     */
	
	public int addComment(CommentDTO dto) {
		
		try {
			
			String sql = "insert into tblCommunityComment(communityCommentSeq, communitySeq, userSeq, communityCommentContent, communityCommentDate) values (seqCommunityComment.nextVal, ?, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCommunitySeq());
			pstat.setString(2, dto.getUserSeq());
			pstat.setString(3, dto.getCommentContent());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
     * 주어진 댓글 번호에 해당하는 댓글을 삭제하는 메서드입니다.
     *
     * @param seq 댓글 번호
     * @return 데이터베이스에서 삭제된 행 수
     */
	public int delComment(String seq) {
		
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
     * 주어진 댓글 정보를 이용하여 댓글을 수정하는 메서드입니다.
     *
     * @param dto 댓글 정보를 담은 {@code CommentDTO} 객체
     * @return 데이터베이스에서 수정된 행 수
     */
	public int editComment(CommentDTO dto) {
		
		try {

			String sql = "update tblCommunityComment set communityCommentContent = ? where communityCommentSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCommentContent());
			pstat.setString(2, dto.getCommentSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
     * 주어진 커뮤니티 게시물 정보를 이용하여 새로운 게시물을 추가하는 메서드입니다.
     *
     * @param dto 커뮤니티 게시물 정보를 담은 {@code communityDTO} 객체
     * @return 데이터베이스에 추가된 행 수
     */
	public int add(communityDTO dto) {
		
		try {

			String sql = "insert into tblCommunity (communitySeq, userSeq, communityDate, communityTitle, communityContent, communityLikeCount, communityCommentCount) values (seqCommunity.nextVal, ?, default, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getSubject());
			pstat.setString(3, dto.getContent());
			pstat.setInt(4, dto.getLikeCount());
			pstat.setInt(5, dto.getCct()); //commentCount

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	  /**
     * 주어진 커뮤니티 게시물 정보를 이용하여 게시물을 수정하는 메서드입니다.
     *
     * @param dto 커뮤니티 게시물 정보를 담은 {@code communityDTO} 객체
     * @return 데이터베이스에서 수정된 행 수
     */
	public int edit(communityDTO dto) {
				
		// Edit.java
		
				//queryParamNoReturn
				try {

					String sql = "update tblCommunity set communityTitle = ?, communityContent = ? where communitySeq = ?";

					pstat = conn.prepareStatement(sql);
					pstat.setString(1, dto.getSubject());
					pstat.setString(2, dto.getContent());
					pstat.setString(3, dto.getCommunitySeq());

					return pstat.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
	/**
     * 커뮤니티 데이터를 삭제합니다.
     *
     * @param seq 삭제할 커뮤니티 데이터의 일련번호
     * @return 삭제된 레코드의 수를 반환합니다.
     */
	public int del(String seq) {
				
			// Del.java
		
				//queryParamNoReturn
				try {
					
					String sql = "delete from tblCommunity where communitySeq = ?";
					
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, seq);
					
					return pstat.executeUpdate();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		return 0;
	}
	/**
     * 특정 커뮤니티 글에 대한 모든 댓글을 삭제합니다.
     *
     * @param seq 삭제할 커뮤니티 글의 일련번호
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
