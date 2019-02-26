package com.example.vlad.navigation.calculation.machineVisionSystem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import com.example.vlad.navigation.calculation.machineVisionSystem.filtration.BlurDetectionMap;
import com.example.vlad.navigation.database.DataAccessFactory;
import com.example.vlad.navigation.database.DataAccessService;
import com.example.vlad.navigation.database.model.Point;
import com.example.vlad.navigation.database.model.Template;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;

public class ImageProcessor implements Runnable {

    private final Image mImage;
    private final RecognitionFaced recognitionFaced = RecognitionFacedFactory.getFaced();
    private DataAccessService dataAccess = DataAccessFactory.getDataAccess();
    private BlurDetectionMap blurDetection = BlurDetectionMap.getInstance();

    public ImageProcessor(Image image) {
        mImage = image;
    }

    @Override
    public void run() {
        Bitmap currentBitmap = buildBitmap(mImage);
        Bitmap detected = blurDetection.getImage(currentBitmap);
        if(detected.equals(currentBitmap)) {
            byte[] bytes = getByteBuffer(mImage);
            Template template = dataAccess.getTemplateById(recognitionFaced.recognize(bytes, mImage.getWidth(), mImage.getHeight()));
            List<Point> point = template.getPoints();
            System.out.print(point);
        }
    }

    private Bitmap buildBitmap(Image image){
        byte[] bytes = getByteBuffer(image);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return BitmapFactory.decodeStream(bis);
    }

    private byte[] getByteBuffer(Image image){
        ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

}