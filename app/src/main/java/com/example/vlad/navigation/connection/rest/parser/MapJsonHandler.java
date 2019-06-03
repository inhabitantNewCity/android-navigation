package com.example.vlad.navigation.connection.rest.parser;


import android.util.Log;

import com.example.vlad.navigation.connection.rest.DatabaseClient;
import com.example.vlad.navigation.database.DatabaseCache;
import com.example.vlad.navigation.database.model.Line;
import com.example.vlad.navigation.database.model.NavigationMap;
import com.example.vlad.navigation.database.model.NavigationWay;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MapJsonHandler extends JsonHttpResponseHandler {

    private ObjectMapper mapper;
    private CollectionType typeReference;
    private DatabaseCache instance = DatabaseCache.getInstance();

    public MapJsonHandler() {
        this.mapper = new ObjectMapper();
        this.typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Line.class);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        try {
            List<Line> lines = mapper.readValue(response.toString(), typeReference);
            NavigationMap map = new NavigationMap(NavigationMap.convert(lines));
            instance.setMap("CORP1FLOOR2", map);
            super.onSuccess(statusCode, headers, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
