package com.sist.dao;

public class ProductVO {
	private int pno,p_stack,p_like,p_hit,p_review_num;
	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getP_stack() {
		return p_stack;
	}

	public void setP_stack(int p_stack) {
		this.p_stack = p_stack;
	}

	public int getP_like() {
		return p_like;
	}

	public void setP_like(int p_like) {
		this.p_like = p_like;
	}

	public int getP_hit() {
		return p_hit;
	}

	public void setP_hit(int p_hit) {
		this.p_hit = p_hit;
	}

	public int getP_review_num() {
		return p_review_num;
	}

	public void setP_review_num(int p_review_num) {
		this.p_review_num = p_review_num;
	}

	public double getP_grade() {
		return p_grade;
	}

	public void setP_grade(double p_grade) {
		this.p_grade = p_grade;
	}

	public String getP_price() {
		return p_price;
	}

	public void setP_price(String p_price) {
		this.p_price = p_price;
	}

	public String getP_percent() {
		return p_percent;
	}

	public void setP_percent(String p_percent) {
		this.p_percent = p_percent;
	}

	public String getP_lowerprice() {
		return p_lowerprice;
	}

	public void setP_lowerprice(String p_lowerprice) {
		this.p_lowerprice = p_lowerprice;
	}

	public String getP_shipment() {
		return p_shipment;
	}

	public void setP_shipment(String p_shipment) {
		this.p_shipment = p_shipment;
	}

	public String getP_exire_date() {
		return p_exire_date;
	}

	public void setP_exire_date(String p_exire_date) {
		this.p_exire_date = p_exire_date;
	}

	public String getP_detail_text() {
		return p_detail_text;
	}

	public void setP_detail_text(String p_detail_text) {
		this.p_detail_text = p_detail_text;
	}

	public String getP_detail_image() {
		return p_detail_image;
	}

	public void setP_detail_image(String p_detail_image) {
		this.p_detail_image = p_detail_image;
	}

	private double p_grade;
	
private String p_name,p_image,p_category,p_price,p_percent,p_lowerprice,p_shipment,p_exire_date,p_detail_text,p_detail_image;

public String getP_name() {
	return p_name;
}

public void setP_name(String p_name) {
	this.p_name = p_name;
}

public String getP_category() {
	return p_category;
}

public void setP_category(String p_category) {
	this.p_category = p_category;
}

public String getP_image() {
	return p_image;
}

public void setP_image(String p_image) {
	this.p_image = p_image;
}

}
