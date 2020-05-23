package com.lightweh.facedetection;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasPermission()) {
            if (null == savedInstanceState) {
                setFragment();
            }
        } else {
            requestPermission();
        }
//        accelerometer = new Accelerometer(this);
//        gyroscope = new Gyroscope(this);
//        accelerometer.setListener(new Accelerometer.Listener() {
//            @Override
//            public void onTranslation(float x, float y, float z) {
//                Log.d("Accelerator", " " + x + " , " + y + " , " + z);
//                if(x>1.0f){
//                    getWindow().getDecorView().setBackgroundColor(Color.RED);
//                }
//                else if (x<1.0f){
//                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
//                }
//            }
//        });
//        gyroscope.setListener(new Gyroscope.Listener() {
//            @Override
//            public void onRotation(float rx, float ry, float rz) {
////                Log.d("Gyroscope", " " + rx + " , " + ry + " , " + rz);
//                if (rz>1.0f){
//                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
//                }
//                else if (rz<1.0f){
//                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
//                }
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setFragment();
            } else {
                requestPermission();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        accelerometer.register();
//        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        accelerometer.unregister();
//        gyroscope.unregister();
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Toast.makeText(MainActivity.this, "Camera permission are required for this demo", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        }
    }

    private void setFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commitNowAllowingStateLoss();
    }
}
