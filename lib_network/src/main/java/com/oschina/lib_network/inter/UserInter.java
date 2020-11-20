package com.oschina.lib_network.inter;

import android.database.Observable;

import com.oschina.lib_network.bean.BaseResult;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Creator:  chee
 * Date：2020/11/20 - 16:21
 * Desc：个人信息
 */
public interface UserInter {

    @POST("action/openapi/user")
    Observable<BaseResult> userInfo(@Body Map map);

}
