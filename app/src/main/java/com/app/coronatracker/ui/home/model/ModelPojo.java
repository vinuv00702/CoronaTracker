package com.app.coronatracker.ui.home.model;

public class ModelPojo {

    private String cases;
    private String deaths;
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
