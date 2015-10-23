package oreschnix.exampleapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Miha on 19.10.2015.
 */
public class Restaurant implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("telephone")
    private ArrayList<String> telephoneNumbers;
    @SerializedName("price")
    private String price;
    @SerializedName("coordinates")
    private ArrayList<String> coordinates;
    @SerializedName("opening")
    private Opening opening;
    @SerializedName("menu")
    private ArrayList<ArrayList<String>> dishMenu;
    @SerializedName("features")
    private ArrayList<Feature> features;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(ArrayList<String> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<String> coordinates) {
        this.coordinates = coordinates;
    }

    public Opening getOpening() {
        return opening;
    }

    public void setOpening(Opening opening) {
        this.opening = opening;
    }

    public ArrayList<ArrayList<String>> getDishMenu() {
        return dishMenu;
    }

    public void setDishMenu(ArrayList<ArrayList<String>> dishMenu) {
        this.dishMenu = dishMenu;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
}
