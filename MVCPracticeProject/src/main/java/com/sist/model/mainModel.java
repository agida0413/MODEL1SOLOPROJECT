package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.ProductDAO;
import com.sist.vo.productVO;

public class mainModel implements Model{

	@Override
	public String handleRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String ct= request.getParameter("ct");
		String page=request.getParameter("page");
	
		if(ct==null){
			ct="전체";
		}
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		
		ProductDAO dao=ProductDAO.newInstace();
		List<productVO>list=dao.productList(ct, curpage);
		int totalpage= dao.productTotalPage(ct);
		
		final int block=10;
		
		int start=((curpage-1)/block*block)+1;
		int end = ((curpage-1)/block*block)+10;
		if(end>totalpage) {
			end=totalpage;
		}
		request.setAttribute("plist", list);
		request.setAttribute("ct",ct);
		request.setAttribute("page", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("end", end);
		request.setAttribute("start", start);
		
		
		return "product/main.jsp";
	}

}
