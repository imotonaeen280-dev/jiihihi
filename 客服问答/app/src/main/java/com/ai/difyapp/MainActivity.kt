package com.ai.difyapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.parseColor("#1a1d27")
        window.navigationBarColor = Color.parseColor("#1a1d27")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val root = RelativeLayout(this)
        root.setBackgroundColor(Color.parseColor("#0f1117"))

        webView = WebView(this)
        webView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )

        val progress = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
        val pbParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 8)
        pbParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        progress.layoutParams = pbParams
        progress.progressTintList = android.content.res.ColorStateList.valueOf(Color.parseColor("#6366f1"))

        root.addView(webView)
        root.addView(progress)
        setContentView(root)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            @Suppress("DEPRECATION")
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode = WebSettings.LOAD_DEFAULT
            useWideViewPort = true
            loadWithOverviewMode = true
            builtInZoomControls = false
            displayZoomControls = false
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress.progress = newProgress
                progress.visibility = if (newProgress == 100) View.GONE else View.VISIBLE
            }
        }
        webView.webViewClient = WebViewClient()
        webView.loadUrl("file:///android_asset/index.html")
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }

    override fun onPause()   { super.onPause();   webView.onPause() }
    override fun onResume()  { super.onResume();  webView.onResume() }
    override fun onDestroy() { webView.destroy(); super.onDestroy() }
}
