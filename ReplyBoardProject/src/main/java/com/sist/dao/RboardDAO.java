package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RboardDAO {

	private Connection conn;
	private PreparedStatement ps;
	private static RboardDAO dao;
	
	public static RboardDAO newInstance() {
		
		if (dao ==null) {
			dao=new RboardDAO();
		}
		return dao;
		
	}
	
	public void getConnection() {
		
		try {
			Context init = new InitialContext();
			Context c= (Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("kyj");
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		
	}
	
	public void disConnection() {
		try {
			if (ps!=null) {
				ps.close();
			}
			if (conn!=null) {
				conn.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	public List<RboardVO> boardListDate(int page){
		List<RboardVO>list =new ArrayList<RboardVO>();
		
		try {
			getConnection();
			String sql ="SELECT rno,name,subject,TO_CHAR(regdate,'YYYY-MM-dd'),hit,gtap,num "
					+ " FROM (SELECT rno,name,subject,regdate,hit,gtap,rownum as num "
					+"FROM (SELECT rno,name,subject,regdate,hit,gtap FROM r_board ORDER BY gi DESC,gon ASC)) "
					+"WHERE num BETWEEN ? AND ? ";
			int rownum=10;		
			int start =(rownum*page)-(rownum-1);
			int end=rownum*page;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				RboardVO vo=new RboardVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGtap(rs.getInt(6));
				list.add(vo);
				
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
		
	}
	public int totalPage() {
		int totalpage=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM r_board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			totalpage=rs.getInt(1);
			rs.close();
			} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return totalpage;
		
	}
	
	public void insertBoard(RboardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO r_board (rno,name,subject,content,gi) "
					+"VALUES(rboard_seq.nextval,?,?,?,(SELECT NVL((MAX(gi)+1),1) FROM r_board))";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.executeUpdate();
			
			
			
			
			} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
	}
	
	public RboardVO detailBoard(int rno) {
		
		RboardVO vo=new RboardVO();
		try {
			getConnection();
			String sql="UPDATE r_board SET "
						+"hit=hit+1 "
						+"WHERE rno="+rno;
			
			ps=conn.prepareStatement(sql);
			
			ps.executeUpdate();
			
			sql="SELECT name,subject,content FROM r_board "
					+"WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setName(rs.getString(1));
			vo.setSubject(rs.getString(2));
			vo.setContent(rs.getString(3));
			
			rs.close();
			
			
			} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return vo;
	}
	
	
public RboardVO updateDataBoard(int rno) {
		
		RboardVO vo=new RboardVO();
		try {
			getConnection();
			
			
		String	sql="SELECT name,subject,content FROM r_board "
					+"WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setName(rs.getString(1));
			vo.setSubject(rs.getString(2));
			vo.setContent(rs.getString(3));
			
			rs.close();
			
			
			} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return vo;
	}

public void updateBoard(RboardVO vo,int rno) {
	
	try {
		getConnection();
		
		
	String	sql="UPDATE r_board SET "
				+"name=?,subject=?,content=? "
				+"WHERE rno="+rno;
		ps=conn.prepareStatement(sql);
		ps.setString(1, vo.getName());
		ps.setString(2, vo.getSubject());
		ps.setString(3, vo.getContent());
		ps.executeUpdate();
		
		} catch (Exception e) {
		// TODO: handle exception
	e.printStackTrace();
	}
	finally {
		disConnection();
	}
	
	
	
}

public void replyBoardInsert(int rno,RboardVO vo) {
	try {
		getConnection();
		
		int db_gon=0;
		int db_gi=0;
		int db_gtab=0;
	String	sql="SELECT gi,gtap,gon FROM r_board WHERE rno="+rno;
	
				
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		rs.next();
		db_gi=rs.getInt(1);
		db_gtab=rs.getInt(2);
		db_gon=rs.getInt(3);
		rs.close();
		
		sql="UPDATE r_board SET "
			+"gon=gon+1 "
			+"WHERE gi=? AND gon>?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, db_gi);
		ps.setInt(2, db_gon);
		ps.executeUpdate();
		
		
		sql="INSERT INTO r_board (rno,name,subject,content,gi,gon,gtap,root) "
				+"VALUES(rboard_seq.nextval,?,?,?,?,?,?,?)";
		
		ps=conn.prepareStatement(sql);
		ps.setString(1,vo.getName());
		ps.setString(2,vo.getSubject());
		ps.setString(3, vo.getContent());
		ps.setInt(4, db_gi);
		ps.setInt(5, db_gon+1);
		ps.setInt(6, db_gtab+1);
		ps.setInt(7, rno);
		
		ps.executeUpdate();
		
		sql="UPDATE r_board SET "
				+"depth=depth+1 "
				+"WHERE rno="+rno;
		ps=conn.prepareStatement(sql);
		ps.executeUpdate();
		
		} catch (Exception e) {
		// TODO: handle exception
	e.printStackTrace();
	}
	finally {
		disConnection();
	}
	
	
}

public void deleteBoard(int rno) {
	
	String msg="관리자가 삭제한 게시물";
	try {
		getConnection();
		int db_root=0;
		int db_depth=0;
		String sql="select root,depth FROM R_BOARD WHERE rno="+rno;
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		rs.next();
		db_root=rs.getInt(1);
		db_depth=rs.getInt(2);
		rs.close();
		
		if (db_depth==0) {
				sql="DELETE FROM R_BOARD WHERE rno="+rno;
					ps=conn.prepareStatement(sql);
					
					ps.executeUpdate();
					
		}
		
		else {
			
			
			sql="UPDATE r_board SET "
					+"content=? ,"
					+ "subject=? "
				+"WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ps.setString(1,msg);
			ps.setString(2, msg);
			ps.executeUpdate();
		}
		 sql="UPDATE r_board SET "
					+"depth=depth-1 "
					+"WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, db_root);
			ps.executeUpdate();
		
		
		} catch (Exception e) {
		// TODO: handle exception
	e.printStackTrace();
	}
	finally {
		disConnection();
	}
	
}
	
}
