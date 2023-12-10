package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDAO {
	private PreparedStatement ps;
	private Connection conn;
	private static ReplyDAO dao;


	public void getConnection() {
	try {
		//탐색기
		Context init = new InitialContext();
		
		Context c = (Context)init.lookup("java://comp/env");
		DataSource ds=(DataSource)c.lookup("kyj");
		conn=ds.getConnection();
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	}

	public void disConnection() {
		try {
			if (ps!=null) {ps.close();
				
			}
			if (ps!=null) {conn.close();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ReplyDAO newInstance() {
		
		if (dao==null) {
			dao =new ReplyDAO();
		}
		
		return dao;	
		
		
	}
	
	public List<ReplyVO> replyListData(int pno){
		List<ReplyVO>list=new ArrayList<ReplyVO>();
		
		try {
			getConnection();
			String sql="SELECT ID,NAME,MSG,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:ss'),rno,pno "
						+"FROM pro_reply "
						+"WHERE pno="+pno
						+" ORDER BY RNO DESC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				ReplyVO vo=new ReplyVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setMsg(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setRno(rs.getInt(5));
				vo.setPno(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
		
		return list;
		
	}
	
	public void insertReply(ReplyVO vo) {
		
		try {
			getConnection();
			String sql="INSERT INTO PRO_REPLY (rno,pno,id,name,msg) "
						+"VALUES(reply_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getPno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
	}
	
	public void deleteReply(int rno) {
		try {
			getConnection();
			String sql="DELETE from pro_reply WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
		
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
		
	}
	
	public void replyUpdate(int rno,String content) {
		try {
			getConnection();
			String sql="UPDATE pro_reply SET "
						+"msg=?,regdate=sysdate"
						+ " WHERE rno="+rno;
			ps=conn.prepareStatement(sql);
			ps.setString(1, content);
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
