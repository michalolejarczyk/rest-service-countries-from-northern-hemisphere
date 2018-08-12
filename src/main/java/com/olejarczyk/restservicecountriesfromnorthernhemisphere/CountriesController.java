package com.olejarczyk.restservicecountriesfromnorthernhemisphere;


import org.json.simple.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;


@RestController
public class CountriesController {
    private ApiInterface apiIPVigilante;

    public CountriesController(ApiIPVigilante api) {
        this.apiIPVigilante = api;
    }

    @GetMapping(value = "northcountries", produces = MediaType.APPLICATION_JSON_VALUE)
    public NorthCountriesResponse getCoutriesFromNorthHemisphere(@RequestParam(value = "ip") ArrayList<String> ipAddresses) {

        int endListIndex;
        if (ipAddresses.size() >= 5) {
            endListIndex = 5;
        } else {
            endListIndex = ipAddresses.size();
        }

        List<JSONObject> jsonObjectsFromIpVigilanteApi = ipAddresses.subList(0, endListIndex)
                .stream()
                .map(ip -> apiIPVigilante.getJSONObject(ip))
                .collect(Collectors.toList());

        SortedSet<String> sortedNorthCountries = new TreeSet<String>();

        jsonObjectsFromIpVigilanteApi.stream()
                .filter(country -> country != null && ((String) country.get("status")).equals("success"))
                .map(country -> (JSONObject) country.get("data"))
                .filter(country -> Double.parseDouble((String) country.get("latitude")) > 0)
                .map(country -> (String) country.get("country_name"))
                .forEach(country -> sortedNorthCountries.add(country));

        return new NorthCountriesResponse(sortedNorthCountries.toArray(new String[sortedNorthCountries.size()]));
    }
}
