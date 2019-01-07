package com.example.vlad.navigation.calculation.inertialSystem.definitionTypeStep;

import com.example.vlad.navigation.utils.StepType;

import math.geom3d.Vector3D;


public class StepTypeDefinerImpl implements StepTypeDefiner {

    private static final double ANGLE_BOUND_STEP_TYPE = Math.sqrt(2)/2;

    @Override
    public StepType define(float[] ac) {
        Vector3D vector = new Vector3D(ac[0],ac[1],ac[2]);
        Vector3D vectorZ = new Vector3D(0,0,1);
        vector.normalize();

        double cosAngle = getAngleBetweenVectors(vector, vectorZ);
        if(cosAngle >= ANGLE_BOUND_STEP_TYPE)
            return StepType.STAIR;
        return StepType.WALK;
    }

    private double getAngleBetweenVectors(Vector3D vector, Vector3D vectorZ) {
        double multipleResult = vector.getX() * vectorZ.getX() + vector.getY() * vectorZ.getY() + vector.getZ() * vectorZ.getZ();
        return Math.abs(multipleResult/(vector.norm() * vectorZ.norm()));
    }
}
