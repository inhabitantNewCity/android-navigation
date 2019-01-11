package com.example.vlad.navigation.calculation.machineVisionSystem.filtration;

import android.graphics.Bitmap;

// TODO: 18.12.2018 add implementation of sigma definition
public class BluringDetectorImpl implements BluringDetector {
    @Override
    public Double blurDetection(Bitmap image) {
        double gause = 0;
        int numberRows = image.getHeight() / 2;
        for(int i = 0; i < numberRows; i++){
            gause += getGauseForLine(image, i);
        }
        return new Double(gause/numberRows);
    }

    private double getGauseForLine(Bitmap image, int i) {
        return 0;
    }
}
