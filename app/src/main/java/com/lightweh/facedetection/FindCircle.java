package com.lightweh.facedetection;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

public class FindCircle {

    private int dp = 1;
    private int param1 = 40;
    private int param2 = 40;
    private int minRadius = 30;
    private int maxRadius = 200;


    public List<Circle> detectCircule(Bitmap bitmap) {

        Mat mat = new Mat(bitmap.getWidth(), bitmap.getHeight(),
                CvType.CV_8UC1);
        Mat grayMat = new Mat(bitmap.getWidth(), bitmap.getHeight(),
                CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, mat);
        /* convert to grayscale */
        int colorChannels = (mat.channels() == 3) ? Imgproc.COLOR_BGR2GRAY
                : ((mat.channels() == 4) ? Imgproc.COLOR_BGRA2GRAY : 1);
        Imgproc.cvtColor(mat, grayMat, colorChannels);

        //Imgproc.blur(grayMat,grayMat,new Size(9, 9));
        // Mat tempM=new Mat();

        Imgproc.GaussianBlur(grayMat, grayMat, new Size(9, 9), 2, 2);
        //Imgproc.bilateralFilter(grayMat,tempM,15,80,80,Core.BORDER_DEFAULT);

///* reduce the noise so we avoid false circle detection */
// accumulator value
        //double dp = 1d;
// minimum distance between the center coordinates of detected circles in pixels
        // double minDist = grayMat.rows() / 8;//double minDist = 100; original
        double minDist = 100;
// min and max radii (set these values as you desire)
        //int minRadius = 20, maxRadius = 100;

// param1 = gradient value used to handle edge detection
// param2 = Accumulator threshold value for the
// cv2.CV_HOUGH_GRADIENT method.
// The smaller the threshold is, the more circles will be
// detected (including false circles).
// The larger the threshold is, the more circles will
// potentially be returned.
        // double param1 =
        // , param2 = 60;//70,72 original

        /* create a Mat object to store the circles detected */
        Mat circles = new Mat(bitmap.getWidth(),
                bitmap.getHeight(), CvType.CV_8UC1);

        /* find the circle in the image */
        Imgproc.HoughCircles(grayMat, circles,
                Imgproc.CV_HOUGH_GRADIENT, dp, minDist, param1,
                param2, minRadius, maxRadius);


        /* get the number of circles detected */
        int numberOfCircles = (circles.rows() == 0) ? 0 : circles.cols();
//        get first cirlce
        List<Circle> circles_list= new ArrayList<>();
        for(int i=0;i<numberOfCircles;i++) {
            double[] circleCoordinates = circles.get(0, i);
            circles_list.add(new Circle(circleCoordinates[0], circleCoordinates[1], circleCoordinates[2]));
        }

             return circles_list;
//        Log.d("harsimarSingh", "circles = " + numberOfCircles);

            /* draw the circles found on the image */



    }


}

