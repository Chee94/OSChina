package com.z.lib_core.cache;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Collections;
import java.util.Set;

/**
 * Creator:  chee
 * Date：2020/11/23 - 14:09
 * Desc：通用缓存封装
 */
public abstract class BaseCache {

    private MMKV mv;

    public BaseCache() {
        mv = MMKV.mmkvWithID(getCacheName());
    }

    /**
     * 缓存目录名称
     *
     * @return
     */
    protected abstract String getCacheName();

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void encode(String key, Object object) {
        if (object instanceof String) {
            mv.encode(key, (String) object);
        } else if (object instanceof Integer) {
            mv.encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            mv.encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            mv.encode(key, (Float) object);
        } else if (object instanceof Long) {
            mv.encode(key, (Long) object);
        } else if (object instanceof Double) {
            mv.encode(key, (Double) object);
        } else if (object instanceof byte[]) {
            mv.encode(key, (byte[]) object);
        } else {
            mv.encode(key, object.toString());
        }
    }

    public void encodeSet(String key, Set<String> sets) {
        mv.encode(key, sets);
    }

    public void encodeParcelable(String key, Parcelable obj) {
        mv.encode(key, obj);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public Integer decodeInt(String key) {
        return mv.decodeInt(key, 0);
    }

    public Double decodeDouble(String key) {
        return mv.decodeDouble(key, 0.00);
    }

    public Long decodeLong(String key) {
        return mv.decodeLong(key, 0L);
    }

    public Boolean decodeBoolean(String key) {
        return mv.decodeBool(key, false);
    }

    public Float decodeFloat(String key) {
        return mv.decodeFloat(key, 0F);
    }

    public byte[] decodeBytes(String key) {
        return mv.decodeBytes(key);
    }

    public String decodeString(String key) {
        return mv.decodeString(key, "");
    }

    public Set<String> decodeStringSet(String key) {
        return mv.decodeStringSet(key, Collections.<String>emptySet());
    }

    public Parcelable decodeParcelable(String key) {
        return mv.decodeParcelable(key, null);
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    public void removeKey(String key) {
        mv.removeValueForKey(key);
    }

    /**
     * 清除所有key
     */
    public void clearAll() {
        mv.clearAll();
    }

}
