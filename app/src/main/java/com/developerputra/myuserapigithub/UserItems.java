package com.developerputra.myuserapigithub;

import org.json.JSONObject;

public class UserItems {
    private String username;
    private String company;
    private String location;
    private String img;

    public UserItems(JSONObject object){
        try {
            String username1 = object.getString("login");
            String company1  = object.getString("company");
            String location1 = object.getString("location");
            String image1    = object.getString("avatar_url");

            this.username     = username1;
            this.company      = company1;
            this.location     = location1;
            this.img          = image1;

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
