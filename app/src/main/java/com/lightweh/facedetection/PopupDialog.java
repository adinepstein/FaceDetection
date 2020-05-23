package com.lightweh.facedetection;


import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import static java.lang.Thread.sleep;

public class PopupDialog {
    private Dialog dialog;
    private Boolean noFace;

    public PopupDialog(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup);
        noFace = true;
    }



    public void setNoFace(Boolean noFace) {
        this.noFace = noFace;
    }

    public void showPopup() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (noFace) {
                    synchronized (this) {
                        try {

                            sleep(4000);
                            if (noFace) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialog.show();

                                    }
                                });
                            }
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }).start();
    }

    public void removePopup() {

        if (dialog != null && dialog.isShowing()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });
        }

    }
}
