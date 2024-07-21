package com.tahaalangar.booksshelf

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView=findViewById(R.id.webView)

        webView.settings.javaScriptEnabled

        webView.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }

        webView.loadUrl("https://www.google.com/search?q=what+is+live+daa+in+android&rlz=1C1ONGR_enIN1063IN1063&oq=what+is+live+daa+in+android&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIJCAEQIRgKGKABMgkIAhAhGAoYoAEyCQgDECEYChigATIJCAQQIRgKGKABMgkIBRAhGAoYoAEyBwgGECEYnwUyBwgHECEYnwXSAQg5NTMyajBqN6gCALACAA&sourceid=chrome&ie=UTF-8")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()

        }
    }
}