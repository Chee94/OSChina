package com.z.oschina;

import android.util.Log;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.oschina.lib_network.OSNetwork;
import com.tencent.mmkv.MMKV;
import com.tencent.smtt.sdk.QbSdk;
import com.z.lib_core.app.CoreApp;

import static com.orhanobut.logger.Logger.addLogAdapter;

/**
 * Creator:  chee
 * Date：2020/11/20 - 17:04
 * Desc：
 */
public class OSApp extends CoreApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initWebView();
        OSNetwork.init();
        MMKV.initialize(this);
        addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initWebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
