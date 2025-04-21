package models;

import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class LicensePlate {
    private int id;
    private String name;
    private String imageUrl;
    private String isRegistered;

    public String getIsRegistered() {
        return isRegistered;
    }
 public LicensePlate() {
    }
    public LicensePlate(int id, String name, String imageUrl, String isRegistered) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isRegistered = isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



   
    
}
