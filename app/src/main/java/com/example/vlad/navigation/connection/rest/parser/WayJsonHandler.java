package com.example.vlad.navigation.connection.rest.parser;

import android.util.Log;

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
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WayJsonHandler extends JsonHttpResponseHandler {

    private ObjectMapper mapper;
    private CollectionType typeReference;
    private DatabaseCache instance = DatabaseCache.getInstance();

    public WayJsonHandler() {
        this.mapper = new ObjectMapper();
        this.typeReference =
                TypeFactory.defaultInstance().constructCollectionType(List.class, Line.class);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        try {
            List<Line> lines = mapper.readValue(response.toString(), typeReference);
            NavigationWay map = new NavigationWay(NavigationWay.convert(lines));
            instance.setWay("CORP1FLOOR212", map);
            super.onSuccess(statusCode, headers, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}