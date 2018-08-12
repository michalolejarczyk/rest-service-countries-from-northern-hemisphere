package com.olejarczyk.restservicecountriesfromnorthernhemisphere;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class ApiIPVigilante implements ApiInterface {

    private final StringBuilder apiAdrress = new StringBuilder("https://ipvigilante.com/json/");

    private JSONParser jsonParser = new JSONParser();

    public JSONObject getJSONObject(String ipAdrress) {
        StringBuilder jsonText = new StringBuilder();

        try {
            URL url = new URL(apiAdrress.toString() + ipAdrress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                jsonText.append(inputLine);
            }

            bufferedReader.close();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonText.toString());

            return jsonObject;

        } catch (Exception e) {

        }

        return null;
    }


}
