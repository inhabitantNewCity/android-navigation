package com.example.vlad.navigation.calculation.machineVisionSystem.filtration;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

// TODO: 18.12.2018 add implementation of sigma definition
public class BluringDetectorImpl implements BluringDetector {

    private static final double CONST_G = 0.5;

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
        double[] oneDerivativeF = new double[3];
        double[] twoDerivativeF = new double[2];
        double[] threeDerivativeF = new double[1];
        List<Double> roots = new ArrayList<>();

        double d = 0;
        for(int j = 3; j< image.getWidth(); j++){
            oneDerivativeF[0] = image.getPixel(i, j-2) - image.getPixel(i, j-3);
            oneDerivativeF[1] = image.getPixel(i, j-1) - image.getPixel(i, j-2);
            oneDerivativeF[2] = image.getPixel(i, j) - image.getPixel(i, j-1);
            twoDerivativeF[0] = oneDerivativeF[1] - oneDerivativeF[0];
            twoDerivativeF[1] = oneDerivativeF[2] - oneDerivativeF[1];
            threeDerivativeF[0] = twoDerivativeF[1] - twoDerivativeF[0];
            roots.add(calculateRoots(oneDerivativeF[2], twoDerivativeF[1], threeDerivativeF[0], image.getPixel(i, j), j));
        }
        for(int q = 1; q < roots.size(); q+=2){
            d+= roots.get(q) - roots.get(q-1);
        }
        return d/image.getWidth();
    }

    private double calculateRoots(double oneDerivativeF, double twoDerivativeF, double threeDerivativeF, int f, int indexOfColumn){
        return (threeDerivativeF -
                ((3.0 * twoDerivativeF * indexOfColumn) / Math.pow(CONST_G, 2)) +
                (3.0 * oneDerivativeF * ((Math.pow(indexOfColumn,2)/Math.pow(CONST_G, 4)) - (1.0/Math.pow(CONST_G, 2)))) +
                f *((3.0 * indexOfColumn)/Math.pow(CONST_G, 4) - (Math.pow(indexOfColumn,3)/Math.pow(CONST_G, 6)))) * getValueGauseFunction(indexOfColumn)
                ;
    }
    private double getValueGauseFunction(int x){
        return 1.0/(CONST_G * Math.sqrt(2.0 * Math.PI) * Math.exp((-x * x) / (2.0 * Math.pow(CONST_G, 2))));
    }
}
