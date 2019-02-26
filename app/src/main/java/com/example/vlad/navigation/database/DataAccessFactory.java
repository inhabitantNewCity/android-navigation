package com.example.vlad.navigation.database;

import com.example.vlad.navigation.utils.DynamicPropertyResolver;

public class DataAccessFactory {
    private static DataAccessService instance;
    private static final String STUB_IS_ENABLED = "db.enable";

    public static DataAccessService getDataAccess(){
        if(instance == null){
            instance = DynamicPropertyResolver.getBooleanProperty(STUB_IS_ENABLED, true)
                    ? new DataAccessServiceStub()
                    : new DataAccessServiceRest();
        }
        return instance;
    }
}
