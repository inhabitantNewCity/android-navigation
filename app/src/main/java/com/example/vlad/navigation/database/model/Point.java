package com.example.vlad.navigation.database.model;

import com.example.vlad.navigation.connection.rest.FlexibleFloatDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Point {
    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    private float x;
    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
