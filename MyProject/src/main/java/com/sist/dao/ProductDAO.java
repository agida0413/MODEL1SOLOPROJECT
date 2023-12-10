package com.sist.dao;

import java.util.*;

import javax.*;
import javax.naming.*;
import javax.sql.DataSource;

import java.sql.*;
import javax.sql.*;

public class ProductDAO {
private PreparedStatement ps;
private Connection conn;
private static ProductDAO dao;


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

public List<ProductVO> productList(){
	List<ProductVO>list = new ArrayList<ProductVO>();
	
	try {
		getConnection();
		String sql = "Select p_name,p_image FROM product_detail";
		ps=conn.prepareStatement(sql);
		
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			ProductVO vo =new ProductVO();
			vo.setP_name(rs.getString(1));
			vo.setP_image(rs.getString(2));
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

public List<ProductVO>productCategoryList(String category,int page){
	
	List<ProductVO>list =new ArrayList<ProductVO>();
	
	try {
		getConnection();
		String sql="Select p_name,p_image,pno,num "
					+"FROM (SELECT p_name,p_image,pno,rownum as num "
					+"FROM product_detail WHERE p_category="+category+") "
					+"WHERE num BETWEEN ? AND ?";
		ps=conn.prepareStatement(sql);
		
		int rowSize=16;
		int start = (rowSize*page)-(rowSize-1);//1,17,33
		int end  =(rowSize*page);//16 , 32 
		
		ps.setInt(1, start);
		ps.setInt(2, end);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ProductVO vo =new ProductVO();
			vo.setP_name(rs.getString(1));
			vo.setP_image(rs.getString(2));
			vo.setPno(rs.getInt(3));
			list.add(vo);
			
		}
		rs.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		disConnection();
	}
	
	return list;
	
	

}

public ProductVO productDetail(int pno) {
	ProductVO vo =new ProductVO();
	try {
		getConnection();
		
		String sql ="UPDATE product_detail SET p_hit=p_hit+1 where pno="+pno;
		ps=conn.prepareStatement(sql);
		ps.executeUpdate();
	
		
	sql = "SELECT p_image,p_name,p_price,p_percent,p_lower_price,p_shipment,p_category,p_expire_date,p_detail_text,p_detail_image,p_hit "
				     +"FROM product_detail WHERE pno="+pno;
		ps=conn.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
		rs.next();
		vo.setP_image(rs.getString(1));
		vo.setP_name(rs.getString(2));
		vo.setP_price(rs.getString(3));
		vo.setP_percent(rs.getString(4));
		vo.setP_lowerprice(rs.getString(5));
		vo.setP_shipment(rs.getString(6));
		vo.setP_category(rs.getString(7));
		vo.setP_exire_date(rs.getString(8));
		 vo.setP_detail_text(rs.getString(9));
		 vo.setP_detail_image(rs.getString(10));
		 vo.setP_hit(rs.getInt(11));
		 
		rs.close();
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		disConnection();
	}
	
	return  vo;
	
}

public static ProductDAO newInstance() {
	
	if (dao==null) {
		dao =new ProductDAO();
	}
	
	return dao;	
	
	
}

public int totalPage(String category) {
	int totalPage=0;
	try {
		getConnection();
		String sql="SELECT CEIL(COUNT(*)/16.0) FROM product_detail WHERE p_category="+category;
		ps=conn.prepareStatement(sql);
		
		
		
		ResultSet rs=ps.executeQuery();
		rs.next();
		totalPage = rs.getInt(1);
		
	
		rs.close();
		}
	 catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		disConnection();
	}
	return totalPage;
	
	
	
}
	
	
}
