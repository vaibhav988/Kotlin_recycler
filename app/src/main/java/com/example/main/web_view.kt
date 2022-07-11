package com.example.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class web_view : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url : String? = intent.getStringExtra("url")

        val webView : WebView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.apply {
            if (url != null) {
                loadUrl(url)
            }
            settings.safeBrowsingEnabled = true
            settings.javaScriptEnabled = true
        }
    }
}