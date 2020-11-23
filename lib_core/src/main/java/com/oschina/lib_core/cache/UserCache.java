package com.oschina.lib_core.cache;


import com.oschina.lib_core.constant.CacheKey;
import com.z.lib_core.cache.BaseCache;

/**
 * Creator:  chee
 * Date：2020/11/23 - 14:05
 * Desc：
 */
public class UserCache extends BaseCache {

    private static UserCache instance;

    public static UserCache getInstance() {
        if (instance == null) {
            synchronized (UserCache.class) {
                if (instance == null) {
                    instance = new UserCache();
                }
            }
        }
        return instance;
    }

    @Override
    protected String getCacheName() {
        return "USER_INFO";
    }

    public String getCode() {
        return decodeString(CacheKey.UserInfo.CODE);
    }

    public void logout() {
        clearAll();
    }

}
