package com.app.coronatracker.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {


    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("value")
    @Expose
    private Integer value;

    @SerializedName("active")
    @Expose
    private Integer active;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("confirmed")
    @Expose
    private String confirmed;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    @SerializedName("deaths")
    @Expose
    private String deaths;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
