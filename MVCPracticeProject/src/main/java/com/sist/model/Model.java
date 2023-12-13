package com.sist.model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface Model {
public String handleRequest(HttpServletRequest request);
}
