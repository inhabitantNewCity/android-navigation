package com.example.vlad.navigation.database;

import com.example.vlad.navigation.connection.rest.DatabaseClient;
import com.example.vlad.navigation.database.model.Line;
import com.example.vlad.navigation.database.model.NavigationMap;
import com.example.vlad.navigation.database.model.NavigationWay;
import com.example.vlad.navigation.database.model.Point;
import com.example.vlad.navigation.database.model.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseCache {
    private Map<String, NavigationMap> caches = new HashMap<>();
    private DatabaseClient client = DatabaseClient.getInstance();
    private static DatabaseCache instance;

    private DatabaseCache(){};

    public static DatabaseCache getInstance(){
        if(instance == null){
            instance = new DatabaseCache();
        }
        return instance;
    }

    public NavigationMap getMap(String name){
        NavigationMap entity = caches.get(name);
        if(entity == null) {
            client.getMap(name);
        }
        return caches.get(name);
    }

    public NavigationWay getWay(String pointStart, String pointEnd, String name){
        String key = name+pointStart+pointEnd;
        NavigationWay entity = (NavigationWay) caches.get(key);
        if(entity == null) {
            client.getWay(name);
        }
        return (NavigationWay) caches.get(key);
    }


    public void setMap(String name, NavigationMap entity){
        caches.put(name, entity);
    }

    public void setWay(String name, NavigationWay entity){
        caches.put(name, entity);
    }

}
