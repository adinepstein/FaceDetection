package com.lightweh.facedetection;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {
    public interface Listener{
        void onTranslation(float x, float y,float z);
    }

    private Listener listener;

    public void setListener(Listener l){
        listener=l;
    }

    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;


    public Accelerometer(Context context){
        sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener!=null){
                    listener.onTranslation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }
    public void register(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}