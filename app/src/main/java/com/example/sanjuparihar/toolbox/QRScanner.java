package com.example.sanjuparihar.toolbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class QRScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

        private static final int REQUEST_CAMERA = 1;

        private ZXingScannerView scannerView;
        private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            scannerView = new ZXingScannerView(this);
            setContentView(scannerView);
            int currentApiVersion = Build.VERSION.SDK_INT;

            if(currentApiVersion >=  Build.VERSION_CODES.M)
            {
                if(checkPermission())
                {
                    Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    requestPermission();
                }
            }
        }

        private boolean checkPermission()
        {
            return (ContextCompat.checkSelfPermission(QRScanner.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
        }

        private void requestPermission()
        {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
        }




        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CAMERA:
                    if (grantResults.length > 0) {

                        boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        if (cameraAccepted){
                            Toast.makeText(QRScanner.this, "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(QRScanner.this, "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(CAMERA)) {
                                    displayAlertMessage("You need to allow access to both the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                    }
                                                }
                                            });
                                    return;
                                }
                            }
                        }
                    }
                    break;
            }
        }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }


        public void displayAlertMessage(String message,DialogInterface.OnClickListener listener){

             new AlertDialog.Builder(QRScanner.this)
                     .setMessage(message)
                     .setPositiveButton("OK",listener)
                     .setNegativeButton("Cancle",null)
                     .create()
                     .show();
        }


        @Override
        public void handleResult(Result result) {
            final String myResult = result.getText();
            Log.d("QRCodeScanner", result.getText());
            Log.d("QRCodeScanner", result.getBarcodeFormat().toString());

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan Result");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    scannerView.resumeCameraPreview(QRScanner.this);
                }
            });
            builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));
                    startActivity(browserIntent);
                }
            });
            builder.setMessage(result.getText());
            AlertDialog alert1 = builder.create();
            alert1.show();
        }


}