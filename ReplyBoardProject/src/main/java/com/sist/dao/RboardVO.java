package com.sist.dao;

import java.sql.Date;

public class RboardVO {
private int rno,gi,gon,gtap,depth,root,hit;
public int getHit() {
	return hit;
}
public void setHit(int hit) {
	this.hit = hit;
}
private String name,subject,dbday,content;
private Date regdate;
public int getRno() {
	return rno;
}
public void setRno(int rno) {
	this.rno = rno;
}
public int getGi() {
	return gi;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public void setGi(int gi) {
	this.gi = gi;
}
public int getGon() {
	return gon;
}
public void setGon(int gon) {
	this.gon = gon;
}
public int getGtap() {
	return gtap;
}
public void setGtap(int gtap) {
	this.gtap = gtap;
}
public int getDepth() {
	return depth;
}
public void setDepth(int depth) {
	this.depth = depth;
}
public int getRoot() {
	return root;
}
public void setRoot(int root) {
	this.root = root;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getDbday() {
	return dbday;
}
public void setDbday(String dbday) {
	this.dbday = dbday;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}

}
