package com.app.coronatracker.ui.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private String cases;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("death")
    private String death;
    @SerializedName("todayDeath")
    private String todayDeath;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("active")
    private String active;
    @SerializedName("critical")
    private String critical;

    public Country(String country, String cases, String todayCases, String death,
                   String todayDeath, String recovered, String active, String critical){
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.death = death;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

//    getter/setter

    public String getCountry(){
        return country;
    }
    public void setCountry(String mCountry){
        this.country = mCountry;
    }

    public String getCases(){
        return cases;
    }
    public void setCases(String mCases){
        this.cases = mCases;
    }

    public String getTodayCases(){
        return todayCases;
    }
    public void setTodayCases(String mTodayCases){
        this.todayCases = mTodayCases;
    }

    public String getDeath(){
        return death;
    }
    public void setDeath(String mDeath){
        this.death = mDeath;
    }

    public String getTodayDeath(){
        return getTodayDeath();
    }
    public void setTodayDeath(String mTodayDeath){
        this.todayDeath = mTodayDeath;
    }

    public String getRecovered(){
        return recovered;
    }
    public void setRecovered(String mRecovered){
        this.recovered = mRecovered;
    }

    public String getActive(){
        return active;
    }
    public void setActive(String mActive){
        this.active = mActive;
    }

    public String getCritical(){
        return critical;
    }
    public void setCritical(String mCritical){
        this.critical = mCritical;
    }
}
