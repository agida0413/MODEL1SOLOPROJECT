package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.dbcp.CreateDBCPconnection;
import com.sist.vo.productVO;

public class ProductDAO {
private PreparedStatement ps;
private Connection conn;
private  static ProductDAO dao;
private CreateDBCPconnection dbconn = new CreateDBCPconnection();
private final int ROW_SIZE=12;


public static ProductDAO newInstace() {
	if(dao==null) {
		dao=new ProductDAO();
	}
	return dao;
	
}

/*
 * 
 *  PNO                                       NOT NULL NUMBER
P_NAME                                    NOT NULL VARCHAR2(1000)
P_GRADE                                            NUMBER(2,1)
P_PRICE                                   NOT NULL VARCHAR2(100)
P_IMAGE                                            VARCHAR2(1000)
P_PERCENT                                          VARCHAR2(500)
P_LOWER_PRICE                                      VARCHAR2(500)
P_SHIPMENT                                         VARCHAR2(500)
P_STACK                                            NUMBER
P_CATEGORY                                NOT NULL VARCHAR2(500)
P_EXPIRE_DATE                                      VARCHAR2(500)
P_DETAIL_TEXT                                      CLOB
P_DETAIL_IMAGE                                     VARCHAR2(4000)
P_LIKE                                             NUMBER
P_HIT                                              NUMBER
P_REVIEW_NUM                                       NUMBER
 * 
 * 
 */

public List<productVO> productList(String ct,int page){
	List< productVO> list= new ArrayList<productVO>();
	String msg="";
	if(ct.equals("전체")){
		msg=" ORDER BY pno ASC ";
	}
	else {
		 msg= "WHERE p_category = ? ORDER BY pno DESC ";	
	}
	
	try {
		int start=(ROW_SIZE*page)-(ROW_SIZE-1);
		int end= ROW_SIZE*page;
		conn= dbconn.getConnection();
		String sql="SELECT pno,p_name,p_image,p_percent,p_lower_price,p_category,num "
					+"FROM (SELECT pno,p_name,p_image,p_percent,p_lower_price,p_category,rownum as num "
					+"FROM (SELECT pno,p_name,p_image,p_percent,p_lower_price,p_category FROM product_detail "+msg
					+")) " +"WHERE num BETWEEN ? and ?";
		ps=conn.prepareStatement(sql);
		if(!(ct.equals("전체"))) {
			ps.setString(1, ct);
			ps.setInt(2, start);
			ps.setInt(3, end);
		}
		else {
			ps.setInt(1, start);
			ps.setInt(2, end);
		}
		
		
		
	
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			productVO vo= new productVO();
			vo.setPno(rs.getInt(1));
			vo.setP_name(rs.getString(2));
			vo.setP_image(rs.getString(3));
			vo.setP_percent(rs.getString(4));
			vo.setP_lower_price(rs.getString(5));
			vo.setP_category(rs.getString(6));
			
			list.add(vo);
			
		}
		rs.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		dbconn.disConnection(conn, ps);
	}
	
	return list;
}

public int productTotalPage(String ct) {
	String msg="";
	if(ct.equals("전체")){
		msg=" FROM product_detail ";
	}
	else {
		 msg= "FROM product_detail WHERE p_category=? ";	
	}
	int total=0;
	try {
		conn=dbconn.getConnection();
		String sql= "Select CEIL(COUNT(*)/"+ROW_SIZE+") "+ msg; 
		ps=conn.prepareStatement(sql);
		
		if(!(ct.equals("전체"))) {
			ps.setString(1, ct);
		}
		ResultSet rs= ps.executeQuery();
		rs.next();
		total = rs.getInt(1);
		rs.close();
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		dbconn.disConnection(conn, ps);
	}
	
	return total;
	
}
}
