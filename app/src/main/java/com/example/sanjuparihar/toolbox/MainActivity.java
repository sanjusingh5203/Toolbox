package com.example.sanjuparihar.toolbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button Calculatorbtn,Vplayerbtn,notePadBtn,CamBtn,FlashBtn,musicbtn,BrowserBtn,GalleryBtn,audiorecorder, Screenrecorder,QRBtn,filemanager,tictactoe,
                       newsbtn;


    public void init(){
        Calculatorbtn =(Button)findViewById(R.id.CalculatorBtn);

        Calculatorbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent toy = new Intent(MainActivity.this,Calculator.class);
                startActivity(toy);



            }
        });
        Vplayerbtn=(Button)findViewById(R.id.VideoPlayerbtn);
        Vplayerbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,Audioplayer.class);

                startActivity(toy);
            }
        });
        notePadBtn=(Button)findViewById(R.id.notePadBtn);
        notePadBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,NotePad.class);

                startActivity(toy);
            }
        });

        FlashBtn=(Button)findViewById(R.id.FlashBtn);
        FlashBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,Flash.class);

                startActivity(toy);
            }
        });
        CamBtn=(Button)findViewById(R.id.CamBtn);
        CamBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

               /* final AlertDialog.Builder linkedin_builder = new AlertDialog.Builder(MainActivity.this);

                linkedin_builder.setMessage("" +
                        "CAMERA")
                        .setCancelable(true)

                        .setNegativeButton("Capture Image", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 0);
                            }
                        })
                        .setPositiveButton("Capture Video", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent p=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                                startActivity(p);
                            }


                        })
                ;
                AlertDialog alert = linkedin_builder.create();
                alert.show();*/

                Intent toy = new Intent(MainActivity.this,Cam.class);
                startActivity(toy);
            }
        });


        musicbtn=(Button)findViewById(R.id.Vplayer);
        musicbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,VideoPlayer.class);

                startActivity(toy);
            }
        });

        BrowserBtn=(Button)findViewById(R.id.BrowserBtn);
        BrowserBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,WelcomeBrowser.class);

                startActivity(toy);
            }
        });

        GalleryBtn=(Button)findViewById(R.id.GalleryBtn);
        GalleryBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,PhotoGallery.class);

                startActivity(toy);
            }
        });
        newsbtn=(Button)findViewById(R.id.newsbtn);
        newsbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,News.class);

                startActivity(toy);
            }
        });

        QRBtn=(Button)findViewById(R.id.QRBtn);
        QRBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,QRScanner.class);

                startActivity(toy);
            }
        });

        filemanager=(Button)findViewById(R.id.filemanager);
        filemanager.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,Drawing.class);

                startActivity(toy);
            }
        });

        tictactoe=(Button)findViewById(R.id.tictactoe);
        tictactoe.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,TicTacToe.class);
                startActivity(toy);
            }
        });
        Screenrecorder=(Button)findViewById(R.id. Screenrecorder);
        Screenrecorder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent toy = new Intent(MainActivity.this,ScreenRecorder.class);
                startActivity(toy);
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        public void onBackPressed() {
            final AlertDialog.Builder linkedin_builder = new AlertDialog.Builder(this);

            linkedin_builder.setMessage("" +
                    "Are you sure you want to exit?")
                    .setCancelable(false)
                    .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id)
                        {

                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    })
            ;
            AlertDialog alert = linkedin_builder.create();
            alert.show();
    }
}
