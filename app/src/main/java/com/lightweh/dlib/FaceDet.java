package com.lightweh.dlib;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import android.util.Log;

import java.util.Arrays;
import java.util.List;



public class FaceDet {
    private static final String TAG = "FaceDet";

    // accessed by native methods
    @SuppressWarnings("unused")
    private long mNativeFaceDetContext;

    static {
        try {
            System.loadLibrary("native-lib");
            jniNativeClassInit();
            Log.d(TAG, "jniNativeClassInit success");
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "library not found");
        }

    }

    public FaceDet() {
        jniInit();
    }



    @Nullable
    @WorkerThread
    public List<VisionDetRet> detect(@NonNull Bitmap bitmap) {
        VisionDetRet[] detRets = jniBitmapDet(bitmap);
        return Arrays.asList(detRets);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public void release() {
        jniDeInit();
    }

    @Keep
    private native static void jniNativeClassInit();

    @Keep
    private synchronized native int jniInit();

    @Keep
    private synchronized native int jniDeInit();

    @Keep
    private synchronized native VisionDetRet[] jniBitmapDet(Bitmap bitmap);
}
