package com.example.dev_task_advanced.DTOs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationDTO {

    public String Id;
    public String Name;
    public String Location;

    public static ArrayList<LocationDTO> GetValue(String content) throws JSONException{

        ArrayList<LocationDTO> location = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(content);

        for (int i = 0 ; i < jsonArray.length() ; i++ ){

            LocationDTO jsonParsing = new LocationDTO();

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            jsonParsing.Id = jsonObject.getString("id");
            jsonParsing.Name = jsonObject.getString("name");
            jsonParsing.Location = jsonObject.getString("address");

            location.add(jsonParsing);
        }

        return location;
    }
}
