package com.example.dev_task_advanced.DTOs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityDTO {
    public String cityId;
    public String city;

    public static ArrayList<CityDTO> getValue(String content) throws JSONException {

        ArrayList<CityDTO> city = new ArrayList<>();
        JSONArray jsonArray=new JSONArray(content);
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CityDTO cityDTO = new CityDTO();

            cityDTO.cityId = jsonObject.getString("id");
            cityDTO.city = jsonObject.getString("name");

            city.add(cityDTO);
        }
        return city;
    }
}
