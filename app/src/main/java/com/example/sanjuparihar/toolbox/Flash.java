package com.example.sanjuparihar.toolbox;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class Flash extends AppCompatActivity {


    android.hardware.Camera camera=null;
    ImageButton imageButton;
    Camera.Parameters parameters;
    boolean isflash = false;
    boolean isOn=false;
    private void releaseCameraAndPreview() {
        if(camera!=null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
              if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH));
        {

            try {
                releaseCameraAndPreview();
                camera = Camera.open();
                parameters = camera.getParameters();
                isflash = true;
            }catch (Exception e) {
                Log.e(getString(R.string.app_name), "failed to open Flash");
                e.printStackTrace();
            }

        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isflash) {
                    if (!isOn) {
                        imageButton.setImageResource(R.drawable.on);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        isOn=true;
                    }
                    else
                    {
                        imageButton.setImageResource(R.drawable.off);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();
                        isOn=false;
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Flash.this);
                    builder.setTitle("Error....");
                    builder.setMessage("Flashlight is not Available on this device....");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null)
        {
            camera.release();
            camera = null;
        }
    }
}
