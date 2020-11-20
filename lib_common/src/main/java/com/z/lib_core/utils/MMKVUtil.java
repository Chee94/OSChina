package com.z.lib_core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Creator:  chee
 * Date：2020/9/11 - 13:44
 * Desc：
 */
public abstract class MMKVUtil {

    /**
     * 保存在手机里面的文件名
     */
    protected abstract String getFileName();


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    public void put(Context context, String key, Object object) {
        put(context, getFileName(), key, object);
    }

    public void put(Context context, String spFileName, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(spFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    public void putString(Context context, String spFileName, String key, String stringData) {
        SharedPreferences sp = context.getSharedPreferences(spFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, stringData);
        editor.commit();
        // SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public Object get(Context context, String key, Object defaultObject) {
        return get(context, getFileName(), key, defaultObject);
    }

    public Object get(Context context, String spName, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    public String getString(Context context, String spName, String key, String stringDataDefault) {
        SharedPreferences sp = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return sp.getString(key, stringDataDefault);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(Context context, String key) {
        remove(context, getFileName(), key);
    }

    public void remove(Context context, String spFileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public void clear(Context context) {
        clear(context, getFileName());
    }

    public void clear(Context context, String spFileName) {
        SharedPreferences sp = context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     */
    public boolean contains(Context context, String key) {
        return contains(context, getFileName(), key);
    }

    public boolean contains(Context context, String spFileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll(Context context) {
        return getAll(context, getFileName());
    }

    public Map<String, ?> getAll(Context context, String spName) {
        SharedPreferences sp = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

}
