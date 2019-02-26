package com.example.vlad.navigation.calculation.machineVisionSystem.filtration;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import java.util.SortedSet;
import java.util.TreeSet;

public class BlurDetectionMap {
    private SortedSet<BlurImage> query;
    private BluringDetector detector = new BluringDetectorImpl();
    private static BlurDetectionMap instance;

    private BlurDetectionMap() {
        this.query = new TreeSet<>();
    }

    public static BlurDetectionMap getInstance(){
        if(instance == null)
            instance = new BlurDetectionMap();
        return instance;
    }

    public Bitmap getImage(Bitmap image){
        BlurImage topImage = query.first();
        Double gause = detector.blurDetection(image);
        Log.i("BluringDetector", image.toString() + gause);
        if(topImage.getGauseDestribution().compareTo(gause) != -1) {
            BlurImage newImage = new BlurImage(image);
            newImage.setGauseDestribution(gause);
            query.add(newImage);
            return image;
        }
        return topImage.getImage();
    }
}
