package co.edu.udea.compumovil.gr1.lab2apprun.classes;

import java.util.Date;

/**
 * Created by felipe on 21/03/16.
 */
public class Event {
    private String name;
    private String description;
    private Double distance;
    private String place;
    private String date;
    private String phone;
    private String email;
    private String imagePath;

    public Event(String name, String description, Double distance, String place, String date,
                 String phone, String email, String imagePath) {
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.place = place;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}