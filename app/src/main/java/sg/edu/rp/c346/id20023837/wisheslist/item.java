package sg.edu.rp.c346.id20023837.wisheslist;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class item implements Serializable {

    private int id;
    private String name;
    private String location;
    private float price;
    private int stars;

    public item(int id, String name, String location, float price, int stars) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.stars = stars;

    }

    public int getId() {
        return id;
    }

    public item setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public item setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public item setLocation(String location) {
        this.location = location;
        return this;
    }

    public float getPrice() { return price; }

    public item setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public item setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return name + "\n" + price + "\n" + location + " + " + starsString;

    }
}