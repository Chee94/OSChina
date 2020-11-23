package com.oschina.lib_network.inter;


import com.oschina.lib_network.OSNetwork;
import com.oschina.lib_network.bean.BaseResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Creator:  chee
 * Date：2020/11/20 - 16:26
 * Desc： 认证接口
 */
public interface OAuthInter {

    /**
     * OpenAPI 授权登录页面
     *
     * @param map
     * @return
     */
    String AUTHORIZE = OSNetwork.BASE_URL + "/action/oauth2/authorize";

    /**
     * authorization_code 方式获取 AccessToken
     *
     * @param map
     * @return
     */
    @POST("/action/openapi/token")
    Observable<BaseResult> getToken(@Body Map map);

}
