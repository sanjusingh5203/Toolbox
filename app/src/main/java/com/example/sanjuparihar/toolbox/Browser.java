package com.example.sanjuparihar.toolbox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Browser extends AppCompatActivity implements View.OnClickListener {


    private ImageButton SearchButtonHome;
    private EditText InputURL;
    private Button facebookBtn;
    private Button youtubeBtn;
    private Button instagramBtn;
    private Button flipkartBtn;
    private Button snapchatBtn;
    private Button googleBtn;
    private ImageButton qrcode;
    private ProgressDialog Loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);


        Loadingbar = new ProgressDialog(this);

        SearchButtonHome = (ImageButton) findViewById(R.id.search_button);
        InputURL = (EditText) findViewById(R.id.search_url_home);

        facebookBtn = (Button) findViewById(R.id.facebook);
        youtubeBtn = (Button) findViewById(R.id.youtube);
        instagramBtn = (Button) findViewById(R.id.instagram);
        snapchatBtn = (Button) findViewById(R.id.snapchat);
        flipkartBtn = (Button) findViewById(R.id.flipkart);
        googleBtn = (Button) findViewById(R.id.google);
        qrcode = (ImageButton) findViewById(R.id.qrcode);


        SearchButtonHome.setOnClickListener(this);
        facebookBtn.setOnClickListener(this);
        youtubeBtn.setOnClickListener(this);
        instagramBtn.setOnClickListener(this);
        snapchatBtn.setOnClickListener(this);
        flipkartBtn.setOnClickListener(this);
        googleBtn.setOnClickListener(this);
        qrcode.setOnClickListener(this);

        qrcode.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent toy = new Intent(Browser.this,QRScanner.class);
                startActivity(toy);

            }
        });



    }


    @Override
    public void onClick(View view) {
        if (view == SearchButtonHome) {
            OpenWebSite();
        }

        if (view == facebookBtn) {
            Intent Open_facebook = new Intent(Browser.this, UrlSearch.class);
            Open_facebook.putExtra("url_address", "https://www.facebook.com");
            startActivity(Open_facebook);
        }
        if (view == youtubeBtn) {
            Intent Open_youtube = new Intent(Browser.this, UrlSearch.class);
            Open_youtube.putExtra("url_address", "https://www.youtube.com");
            startActivity(Open_youtube);
        }
        if (view == instagramBtn) {
            Intent Open_instagram = new Intent(Browser.this, UrlSearch.class);
            Open_instagram.putExtra("url_address", "https://www.instagram.com");
            startActivity(Open_instagram);
        }
        if (view == snapchatBtn) {
            Intent Open_snapchat = new Intent(Browser.this, UrlSearch.class);
            Open_snapchat.putExtra("url_address", "https://www.snapchat.com");
            startActivity(Open_snapchat);
        }
        if (view == flipkartBtn) {
            Intent Open_flipkart = new Intent(Browser.this, UrlSearch.class);
            Open_flipkart.putExtra("url_address", "https://www.flipkart.com");
            startActivity(Open_flipkart);
        }
        if (view == googleBtn) {
            Intent Open_google = new Intent(Browser.this, UrlSearch.class);
            Open_google.putExtra("url_address", "https://www.google.com");
            startActivity(Open_google);
        }

    }

    private void OpenWebSite() {



        String url_address = InputURL.getText().toString();

        if (TextUtils.isEmpty(url_address)) {
            Toast.makeText(Browser.this, "Enter valid Url", Toast.LENGTH_SHORT).show();
        } else {

            String url_without_https = url_address.replaceAll("https://www.", "");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(Browser.this, UrlSearch.class);

            search.putExtra("url_address", https + www + url_without_https);
            startActivity(search);

            Loadingbar.setTitle("Loading....");
            Loadingbar.setMessage("Please wait");
            Loadingbar.show();

            InputURL.setText("");
            InputURL.requestFocus();

        }

    }


}
