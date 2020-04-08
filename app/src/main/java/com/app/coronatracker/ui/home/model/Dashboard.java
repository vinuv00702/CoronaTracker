package com.app.coronatracker.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard {

    @SerializedName("cases")
    @Expose
    private String cases;
    @SerializedName("deaths")
    @Expose
    private String deaths;
    @SerializedName("recovered")
    @Expose
    private String recovered;

//    getter / setter

    public String getCases(){
        return cases;
    }
    public void setCases(String mcases){
        this.cases = mcases;
    }

    public String getDeaths(){
        return deaths;
    }
    public void setDeaths(String mdeaths){
        this.deaths = mdeaths;
    }

    public String getRecovered(){
        return recovered;
    }
    public void setRecovered(String mrecovered){
        this.recovered = mrecovered;
    }
}
