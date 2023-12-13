package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class listModel implements Model {

	@Override
	public String handleRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	
		return "product/list.jsp";
	}

}
