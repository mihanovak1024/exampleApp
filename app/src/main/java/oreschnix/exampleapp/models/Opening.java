package oreschnix.exampleapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Miha on 19.10.2015.
 */
public class Opening implements Serializable {

    @SerializedName("week")
    private ArrayList<String> week;
    @SerializedName("saturday")
    private Object saturday;
    @SerializedName("sunday")
    private Object sunday;
    @SerializedName("notes")
    private String notes;

    public ArrayList<String> getWeek() {
        return week;
    }

    public void setWeek(ArrayList<String> week) {
        this.week = week;
    }

    public Object getSaturday() {
        return saturday;
    }

    public void setSaturday(Object saturday) {
        this.saturday = saturday;
    }

    public Object getSunday() {
        return sunday;
    }

    public void setSunday(Object sunday) {
        this.sunday = sunday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
