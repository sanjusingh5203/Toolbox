package com.example.sanjuparihar.toolbox;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final WebChromeClient webChromeClient = new WebChromeClient();
    private static final WebViewClient webViewClient = new WebViewClient();

    private ImageButton SearchUrlButton,HomeButton;
    private EditText UrlInput;
    private WebView SearchWebAddress;

    String url;
    private ProgressDialog Loadingbar;
    private LinearLayout linearLayout;
    private ImageButton qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_search);

        SearchUrlButton =(ImageButton) findViewById(R.id.search_button);
        UrlInput= (EditText) findViewById(R.id.search_url);
        HomeButton=(ImageButton) findViewById(R.id.Home);
        SearchWebAddress=(WebView) findViewById(R.id.SearchWebSite);
        linearLayout=(LinearLayout) findViewById(R.id.linearLayout);
        url = getIntent().getExtras().get("url_address").toString();
        UrlInput.setText(url);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        WebSettings webSettings = SearchWebAddress.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUseWideViewPort(true);
        SearchWebAddress.getSettings().setPluginState(WebSettings.PluginState.ON);

        SearchWebAddress.setWebViewClient(new WebViewClient());
        SearchWebAddress.setWebChromeClient(webChromeClient);
        SearchWebAddress.setWebViewClient(webViewClient);

        SearchWebAddress.addJavascriptInterface(new WebAppInterface(this), "Android");

        SearchWebAddress.loadUrl(url);
        SearchWebAddress.setWebViewClient(new WebViewClient());

         SearchWebAddress.getSettings().setSupportMultipleWindows(true);
         SearchWebAddress.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
         SearchWebAddress.getSettings().setAllowFileAccess(true);
         SearchWebAddress.getSettings().setBuiltInZoomControls(true);
         SearchWebAddress.getSettings().setDisplayZoomControls(true);
         SearchWebAddress.getSettings().setLoadWithOverviewMode(true);
         SearchWebAddress.getSettings().setUseWideViewPort(true);
         SearchWebAddress.loadUrl(url);
        
        SearchUrlButton.setOnClickListener(this);
        HomeButton.setOnClickListener(this);

        Loadingbar = new ProgressDialog(this);

        qrcode = (ImageButton) findViewById(R.id.qrcode);

        qrcode.setOnClickListener(this);

        qrcode.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent toy = new Intent(UrlSearch.this,QRScanner.class);
                startActivity(toy);

            }
        });

        SearchWebAddress.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);

            }
        });
        //linearLayout.setVisibility(View.GONE);


    }


    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        {
            if(SearchWebAddress.canGoBack()){
                SearchWebAddress.goBack();


            }
            else
            {
                super.onBackPressed();

            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view==HomeButton){
            finish();
            Intent homepage = new Intent(UrlSearch.this,Browser.class);
            startActivity(homepage);
        }
        
        if(view == SearchUrlButton){
            SearchWebAddress();
        }
        
    }

    private void SearchWebAddress() {



        String url_address =UrlInput.getText().toString();

        if(TextUtils.isEmpty(url_address))
        {
            Toast.makeText(UrlSearch.this, "Enter valid Url", Toast.LENGTH_SHORT).show();
        }
        else{

            String url_without_https=url_address.replaceAll("https://www.","");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(UrlSearch.this,UrlSearch.class);
            search.putExtra("url_address", String.format("%s%s%s", https, www, url_without_https));

            startActivity(search);

            Loadingbar.setTitle("Loading....");
            Loadingbar.setMessage("Please wait");
            Loadingbar.show();


            UrlInput.setText("");
            UrlInput.requestFocus();

        }
    }
    @Override
    public void onPause() {
         SearchWebAddress.onPause();
         SearchWebAddress.pauseTimers();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
         SearchWebAddress.resumeTimers();
         SearchWebAddress.onResume();
    }


    @Override
    protected void onDestroy() {
         SearchWebAddress = null;
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
       SearchWebAddress.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        SearchWebAddress.restoreState(savedInstanceState);
    }
}
