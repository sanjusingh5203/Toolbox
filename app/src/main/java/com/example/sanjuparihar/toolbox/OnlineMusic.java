package com.example.sanjuparihar.toolbox;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.os.SystemClock.sleep;
import static com.example.sanjuparihar.toolbox.R.id.webView;

public class OnlineMusic extends AppCompatActivity {
    WebView myWebView;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final WebChromeClient webChromeClient = new WebChromeClient();
    private static final WebViewClient webViewClient = new WebViewClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_music);

        WebView myWebView = (WebView) findViewById(webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUseWideViewPort(true);
        myWebView.loadUrl("https://www.wynk.in/music");

        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(webChromeClient);
        myWebView.setWebViewClient(webViewClient);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        myWebView.getSettings().setSupportMultipleWindows(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);




        myWebView.setDownloadListener(new DownloadListener() {
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

    public void onBackPressed ( )
    {

        final AlertDialog.Builder linkedin_builder = new AlertDialog.Builder(this);
        linkedin_builder.setTitle("please pause the music");
        sleep(3000);
        linkedin_builder.setCancelable(false);
        linkedin_builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        linkedin_builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                OnlineMusic.this.finish();
            }
        });
        AlertDialog alert = linkedin_builder.create();
        alert.show();

    }


    @Override
    public void onPause() {
        super.onPause();
        if( myWebView!= null) {
            myWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
