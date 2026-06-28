package com.wordsearch.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.window.OnBackInvokedDispatcher;

public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true); // localStorage(최고 기록 저장)용

        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);

        // API 33+(예측형 뒤로가기): 권장 콜백 사용. 페이지 내 뒤로 이동, 더 없으면 앱 종료.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT,
                () -> {
                    if (web != null && web.canGoBack()) {
                        web.goBack();
                    } else {
                        finish();
                    }
                }
            );
        }
    }

    // API 32 이하 폴백 (33+에서는 위 콜백이 대신 호출됨)
    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        if (web != null && web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
