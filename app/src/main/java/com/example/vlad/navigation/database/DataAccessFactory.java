package com.example.vlad.navigation.database;

import com.example.vlad.navigation.utils.DynamicPropertyResolver;

public class DataAccessFactory {
    private static DataAccessService instance;
    private static final String DB_ENABLE = "db.enable";

    public static DataAccessService getDataAccess(){
        /*if(instance == null){
            instance = DynamicPropertyResolver.getBooleanProperty(DB_ENABLE, true)
                    ? new DataAccessServiceRest()
                    : new DataAccessServiceStub();
        }
        return instance;*/
        return new DataAccessServiceRest();
    }
}
