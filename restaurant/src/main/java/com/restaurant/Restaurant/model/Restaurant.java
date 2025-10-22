package com.restaurant.Restaurant.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "restaurants")
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String ownerName;
    private String openingTime;
    private String closingTime;
    private String image;
   // private String status = "pending";

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public void setImage(String image) { this.image = image; }

    //public void setStatus(String status) { this.status = status; }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public String getImage() { return image; }

    //public String getStatus() { return status; }



    // Constructors, Getters & Setters
}



