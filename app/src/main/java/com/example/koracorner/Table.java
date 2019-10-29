package com.example.koracorner;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

public class Table extends Fragment {
    private ProgressBar progressBar;
    public WebView mWebView;
    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootview = inflater.inflate(R.layout.fragment_table, container, false);

        mWebView = (WebView) rootview.findViewById(R.id.webview3);
        mWebView.loadUrl("https://mobikora.tv/table/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());





        progressBar = (ProgressBar) rootview.findViewById(R.id.pro);





        return rootview;
    }
}