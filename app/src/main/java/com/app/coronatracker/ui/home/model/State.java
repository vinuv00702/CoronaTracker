package com.app.coronatracker.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    @SerializedName("active")
    @Expose
    private Integer active;

    @SerializedName("confirmed")
    @Expose
    private String confirmed;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("district")
    @Expose
    private HashMap<String, District> states = new HashMap<String, District>();;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public HashMap<String, District> getStates() {
        return states;
    }

    public void setStates(HashMap<String, District> states) {
        this.states = states;
    }
}
