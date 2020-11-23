package com.oschin.lib_login

import android.net.Uri
import com.orhanobut.logger.Logger
import com.oschina.lib_core.base.BaseActivity
import com.oschina.lib_core.cache.UserCache
import com.oschina.lib_core.constant.CacheKey
import com.oschina.lib_core.constant.OSConfig
import com.oschina.lib_network.inter.OAuthInter
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.login_act.*

/**
 * Creator:  chee
 * Date：2020/11/20 - 17:01
 * Desc：
 */
class LoginActivity : BaseActivity() {


    override fun init() {

        val webSetting: WebSettings = web_view.getSettings()
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(true)
        // webSetting.setLoadWithOverviewMode(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true)
        // webSetting.setDatabaseEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.domStorageEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE

        web_view.loadUrl(OAuthInter.AUTHORIZE + "?client_id=" + OSConfig.CLIENT_ID + "&response_type=code" + "&redirect_uri=" + OSConfig.CALLBACK_URL)
        web_view.webViewClient = object : WebViewClient() {
            /**
             * 防止加载网页时调起系统浏览器
             */
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                Logger.d("shouldOverrideUrlLoading: $url")
                if (url.startsWith(OSConfig.CALLBACK_URL)) {
                    val uri = Uri.parse(url);
                    val code = uri.getQueryParameter("code");
                    code?.let {
                        UserCache.getInstance()
                            .encode(CacheKey.UserInfo.CODE, code);
                        finish()
                    }
                    val error_description = uri.getQueryParameter("error_description")
                    error_description?.let {
                        toastShort(error_description)
                    }
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.login_act

}