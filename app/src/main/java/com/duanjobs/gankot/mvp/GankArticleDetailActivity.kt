package com.duanjobs.gankot.mvp

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.R
import com.duanjobs.gankot.extensions.loading
import com.duanjobs.gankot.utils.Const
import kotlinx.android.synthetic.main.activity_gank_article_detail.*

/**
 * Created by duanjobs on 17/9/8.
 */
class GankArticleDetailActivity(override val layoutId: Int = R.layout.activity_gank_article_detail)
    :BaseActivity() {

    var title:String?=null
    var url:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initWebView()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        }else{
            super.onBackPressed()
        }

    }

    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler!!.proceed()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                loading(Const.SHOW)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loading(Const.DISMISS)
                super.onPageFinished(view, url)
            }
        })
        webView.loadUrl(url)
    }

    private fun getData() {
        title = intent.getStringExtra("desc")
        url = intent.getStringExtra("url")
    }
}