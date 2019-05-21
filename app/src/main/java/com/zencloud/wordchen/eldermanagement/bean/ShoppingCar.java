package com.zencloud.wordchen.eldermanagement.bean;


import java.io.Serializable;

public class ShoppingCar implements Serializable {

	private Integer shopcart_id;
	private Integer service_id;
	private Integer user_id;
    private String service_name;
    private String service_image_url;
    private float price;
	private boolean isSelect;


	public Integer getShopcart_id() {
        return shopcart_id;
    }

	public void setShopcart_id(Integer shopcart_id) {
        this.shopcart_id = shopcart_id;
    }

	public Integer getService_id() {
        return service_id;
    }

	public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

	public Integer getUser_id() {
        return user_id;
    }

	public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_image_url() {
        return service_image_url;
    }

    public void setService_image_url(String service_image_url) {
        this.service_image_url = service_image_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}
}
