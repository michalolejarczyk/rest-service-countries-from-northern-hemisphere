package com.olejarczyk.restservicecountriesfromnorthernhemisphere;

public class NorthCountriesResponse {
    private String[] northcountries;
    public NorthCountriesResponse(String [] countries) {
        this.northcountries = countries;
    }

    public String [] getnorthcountries() {
        return this.northcountries;
    }
}
