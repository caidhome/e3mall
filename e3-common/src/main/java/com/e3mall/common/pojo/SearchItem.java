package com.e3mall.common.pojo;

import java.io.Serializable;

public class SearchItem implements Serializable {

    private String id;
    private String title;
    private String sell_point;
    private long price;
    private String image;
    private String categrory_name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategrory_name() {
        return categrory_name;
    }

    public void setCategrory_name(String categrory_name) {
        this.categrory_name = categrory_name;
    }

    public String[] getImages(){
        if(image != null && !"".equals(image)){
            String str[] = image.split(",");
            System.out.println(str[0]);
            return str;
        }
        return null;
    }
}

