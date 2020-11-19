package de.wellenvogel.webviewcrash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private static final String ASSETS="file://assets";
    //if we change assets to an http url everything is working fine
    //private static final String ASSETS="http://assets";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView=findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //this line is causing the problem - when omiiting there will be no crash
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith(ASSETS)) {
                    String remain = url.substring(ASSETS.length() + 1);
                    try {
                        InputStream is = getAssets().open(remain);
                        return new WebResourceResponse(URLConnection.guessContentTypeFromName(remain), null, is);
                    } catch (IOException e) {
                        Log.e("HTTP", "unable to load asset " + remain + ": " + e);
                        return null;
                    }
                } else {
                    return super.shouldInterceptRequest(view, url);
                }
            }
        });
        mWebView.loadUrl(ASSETS+"/index.html");
    }
}