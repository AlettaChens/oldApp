package com.zencloud.wordchen.eldermanagement.bean;


import java.io.Serializable;

public class Order implements Serializable {

	private Integer order_id;
	private Integer user_id;
	private Integer service_id;
	private float price;
	private String service_name;
	private String user_name;
	private String service_image_url;
	private String address;
	private String phone;
	private String status;
	private String orderdate;


	public Order() {
	}


	public Order(Integer order_id, Integer user_id, Integer service_id, float price, String service_name, String user_name, String service_image_url, String
			address, String phone, String status, String orderdate) {
		this.order_id = order_id;
		this.user_id = user_id;
		this.service_id = service_id;
		this.price = price;
		this.service_name = service_name;
		this.user_name = user_name;
		this.service_image_url = service_image_url;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.orderdate = orderdate;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getService_image_url() {
		return service_image_url;
	}

	public void setService_image_url(String service_image_url) {
		this.service_image_url = service_image_url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
}
