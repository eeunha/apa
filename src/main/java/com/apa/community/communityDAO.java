package com.apa.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.apa.DBUtil;

public class communityDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
		
	public communityDAO() {
		this.conn = DBUtil.open();
	}	
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
