package com.example.sanjuparihar.toolbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class play_video extends Activity implements View.OnClickListener {
    private String filename;
   private VideoView vv;
   private  LinearLayout top;
    private ImageButton btn_back;
    private TextView txt_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        System.gc();
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        filename = extras.getString("videofilename");
        // vv = new VideoView(getApplicationContext());
        vv = (VideoView) findViewById(R.id.video_frame);
        vv.setVideoPath(filename);

        vv.setMediaController(new MediaController(this));
        vv.requestFocus();
        vv.start();

        btn_back=(ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener( this);

        txt_title =(TextView)findViewById(R.id.txt_title);
        txt_title.setText(filename);

        top = (LinearLayout)findViewById(R.id.top);
        top.setVisibility(View.VISIBLE);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

            top.setVisibility(View.GONE);

        return false;
    }

    @Override
    public void onClick(View view) {
         int i1= view.getId();
        if(i1==R.id.btn_back){
            finish();
        }
    }

}