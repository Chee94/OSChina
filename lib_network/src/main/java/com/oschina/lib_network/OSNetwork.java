package com.oschina.lib_network;

import com.oschina.lib_network.inter.BlogInter;
import com.oschina.lib_network.inter.NewsInter;
import com.oschina.lib_network.inter.UserInter;

import retrofit2.Retrofit;

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
                .build();
    }

    public static <T> T getInter(Class<T> apiInter) {
        return retrofit.create(apiInter);
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
