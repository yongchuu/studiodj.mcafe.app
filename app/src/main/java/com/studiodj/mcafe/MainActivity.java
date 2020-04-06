package com.studiodj.mcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    WebView web;

    /**
     * javascript와 Interface를 위한..
     * 꼭 내부함수로 해야할까..?
     */
    public class WebAppInterface{
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        web = new WebView(this);
        WebSettings webSet = web.getSettings();
        webSet.setAllowFileAccessFromFileURLs(true);
        webSet.setJavaScriptEnabled(true);
        webSet.setUseWideViewPort(true);
        webSet.setBuiltInZoomControls(false);
        webSet.setAllowUniversalAccessFromFileURLs(true);
        webSet.setJavaScriptCanOpenWindowsAutomatically(true);
        webSet.setSupportMultipleWindows(true);
        webSet.setSaveFormData(false);
        webSet.setSavePassword(false);
        webSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        web.addJavascriptInterface(new WebAppInterface(this), "Android");
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
//        web.loadUrl("file:///android_asset/index.html");
        web.loadUrl("http://34.64.187.82/index.html");
        layout.addView(web);

        setContentView(layout);
    }

    /**
     * 뒤로가기 버튼 클릭 시.
     */
    public void onBackPressed(){
        if(web.canGoBack()) {
            web.goBack();
        }else{
            finish();
        }
    }
}
