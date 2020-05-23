package com.lightweh.facedetection;

import androidx.annotation.NonNull;

public class Circle {
    private double x;
    private double y;
    private double r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + r + ")";
    }
}
