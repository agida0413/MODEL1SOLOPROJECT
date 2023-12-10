package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static ProductDAO dao;
	
	public static ProductDAO newInstance() {
		
		if (dao ==null) {
			dao=new ProductDAO();
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
	
	public int toatalPage(String fd,String search) {
		int totalpage=0;
		
		try {
			getConnection();
			
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM product_detail "
						+"WHERE "+fd+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1,search );
			
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
	
	public List<ProductVO> searchListData(int page,String fd,String search){
		List<ProductVO>list=new ArrayList<ProductVO>();
		
		try {
			getConnection();
			String sql="SELECT pno,p_name,p_price,p_image,p_percent,p_lower_price,num "
						+"FROM (SELECT pno,p_name,p_price,p_image,p_percent,p_lower_price,rownum as num "
						+ "FROM ( SELECT pno,p_name,p_price,p_image,p_percent,p_lower_price FROM product_detail "
						+"WHERE " +fd+" LIKE '%'||?||'%')) "
						+"WHERE num BETWEEN ? AND ?";
						
		int rowsize=12;
		int start=(rowsize*page)-(rowsize-1);
		int end=rowsize*page;
		
		ps=conn.prepareStatement(sql);
		ps.setString(1, search);
		ps.setInt(2, start);
		ps.setInt(3, end);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			
			ProductVO vo= new ProductVO();
			vo.setPno(rs.getInt(1));
			vo.setP_name(rs.getString(2));
			vo.setP__price(rs.getString(3));
			vo.setP_image(rs.getString(4));
			vo.setP_percent(rs.getString(5));
			vo.setP_lowerprice(rs.getString(6));
			list.add(vo);
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
		
	}
	
	public ProductVO productDetail(int pno) {
		
		ProductVO vo=new ProductVO();
		try {
			getConnection();
			
			String sql="UPDATE product_detail SET "
					+"p_hit=p_hit+1 "
					+"WHERE pno="+pno;
			
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			
			
			 sql="SELECT p_name,p_price,p_image,p_percent,p_lower_price,p_shipment,p_stack,p_category,p_detail_image,"
					+"p_hit FROM PRODUCT_DETAIL WHERE pno="+pno;
			 ps=conn.prepareStatement(sql);
			 ResultSet rs= ps.executeQuery();
			 rs.next();
			 vo.setP_name(rs.getString(1));
			 vo.setP__price(rs.getString(2));
			 vo.setP_image(rs.getString(3));
			 vo.setP_percent(rs.getString(4));
			 vo.setP_lowerprice(rs.getString(5));
			 vo.setP_shipment(rs.getString(6));
			 vo.setP_stack(rs.getInt(7));
			 vo.setP_category(rs.getString(8));
			 vo.setP_detail_image(rs.getString(9));
			 vo.setP_hit(rs.getInt(10));
			 rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
		
		
		return vo;
		
	}
	
	
	
public ProductVO productCookieData(int pno) {
		
		ProductVO vo=new ProductVO();
		try {
			getConnection();
			
			
			
			
			String sql="SELECT p_name,p_price,p_image,p_percent,p_lower_price,p_shipment,p_stack,p_category,p_detail_image,"
					+"p_hit,pno FROM PRODUCT_DETAIL WHERE pno="+pno;
			 ps=conn.prepareStatement(sql);
			 ResultSet rs= ps.executeQuery();
			 rs.next();
			 vo.setP_name(rs.getString(1));
			 vo.setP__price(rs.getString(2));
			 vo.setP_image(rs.getString(3));
			 vo.setP_percent(rs.getString(4));
			 vo.setP_lowerprice(rs.getString(5));
			 vo.setP_shipment(rs.getString(6));
			 vo.setP_stack(rs.getInt(7));
			 vo.setP_category(rs.getString(8));
			 vo.setP_detail_image(rs.getString(9));
			 vo.setP_hit(rs.getInt(10));
			 vo.setPno(rs.getInt(11));
			 rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			disConnection();
		}
		
		
		return vo;
		
	}
	
	
}
