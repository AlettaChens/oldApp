package com.zencloud.wordchen.eldermanagement.bean;


import java.io.Serializable;

public class Service implements Serializable {

   private Integer service_id;
   private String  service_name;
   private float service_price;
   private String service_image_url;
   private  String service_description;
   private String service_hot;
   private String service_type;

    public Service() {
    }

    public Service(Integer service_id, String service_name, float service_price, String service_image_url, String service_description, String service_hot, String service_type) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_price = service_price;
        this.service_image_url = service_image_url;
        this.service_description = service_description;
        this.service_hot = service_hot;
        this.service_type = service_type;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public float getService_price() {
        return service_price;
    }

    public void setService_price(float service_price) {
        this.service_price = service_price;
    }

    public String getService_image_url() {
        return service_image_url;
    }

    public void setService_image_url(String service_image_url) {
        this.service_image_url = service_image_url;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public String getService_hot() {
        return service_hot;
    }

    public void setService_hot(String service_hot) {
        this.service_hot = service_hot;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }
}
