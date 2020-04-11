package com.app.coronatracker.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cases")
    @Expose
    private String cases;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    @SerializedName("deaths")
    @Expose
    private String deaths;


}
