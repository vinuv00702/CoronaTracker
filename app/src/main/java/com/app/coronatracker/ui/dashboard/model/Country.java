package com.app.coronatracker.ui.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private String cases;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("todayDeaths")
    private String todayDeaths;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("active")
    private String active;
    @SerializedName("critical")
    private String critical;

    public Country(String country, String cases, String todayCases, String deaths,
                   String todayDeaths, String recovered, String active, String critical){
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

//    getter/setter


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeaths = todayDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
