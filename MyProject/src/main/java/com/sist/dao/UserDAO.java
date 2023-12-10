package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	private PreparedStatement ps;
	private Connection conn;
	private static UserDAO dao;


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
	
	public static UserDAO newInstance() {
		
		if (dao==null) {
			dao =new UserDAO();
		}
		
		return dao;	
		
		
	}
	
	public UserVO loginCheck(String id,String pwd) {
		UserVO vo=new UserVO();
		try {
			getConnection();
			String r_id="'"+id+"'";
			String sql="SELECT COUNT(*) FROM users WHERE id="+r_id;
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if (count==0) {
				vo.setMsg("NO");
				
			}
			else {
				sql="SELECT id,pwd,name FROM users WHERE id="+r_id;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				
				rs.next();
				
				vo.setId(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				
				if (vo.getPwd().equals(pwd)) {
					vo.setMsg("YES");
				}
				else {
					vo.setMsg("NO");
				}
				
					
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return vo;
		
	}
	
	
	
}
