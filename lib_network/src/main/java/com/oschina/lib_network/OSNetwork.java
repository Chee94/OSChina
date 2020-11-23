package com.oschina.lib_network;

import com.oschina.lib_network.conver.FastJsonConverterFactory;
import com.oschina.lib_network.inter.BlogInter;
import com.oschina.lib_network.inter.NewsInter;
import com.oschina.lib_network.inter.OAuthInter;
import com.oschina.lib_network.inter.UserInter;
import com.oschina.lib_network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Creator:  chee
 * Date：2020/11/20 - 16:08
 * Desc： 网络请求公告类型
 */
public class OSNetwork {

    public static final String BASE_URL = "https://www.oschina.net";
    private static Retrofit retrofit;

    public static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor());

        return builder.build();
    }

    public static <T> T getInter(Class<T> apiInter) {
        return retrofit.create(apiInter);
    }

    /**
     * 认证接口
     *
     * @return
     */
    public static OAuthInter getOAuthInter() {
        return getInter(OAuthInter.class);
    }

    /**
     * 用户信息
     *
     * @return
     */
    public static UserInter getUserInter() {
        return getInter(UserInter.class);
    }

    /**
     * 博客
     *
     * @return
     */
    public static BlogInter getBlogInter() {
        return getInter(BlogInter.class);
    }

    /**
     * 新闻
     *
     * @return
     */
    public static NewsInter getNewsInter() {
        return getInter(NewsInter.class);
    }

}
