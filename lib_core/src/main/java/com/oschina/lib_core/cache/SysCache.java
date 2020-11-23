package com.oschina.lib_core.cache;

import com.z.lib_core.cache.BaseCache;

/**
 * Creator:  chee
 * Date：2020/11/23 - 14:07
 * Desc：
 */
public class SysCache extends BaseCache {

    private static SysCache instance;

    public static SysCache getInstance() {
        if (instance == null) {
            synchronized (SysCache.class) {
                if (instance == null) {
                    instance = new SysCache();
                }
            }
        }
        return instance;
    }

    @Override
    protected String getCacheName() {
        return "SYS_INFO";
    }

}
