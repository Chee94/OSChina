package com.z.oschina

import android.content.Intent
import android.view.View
import com.alibaba.fastjson.JSON
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.orhanobut.logger.Logger
import com.oschin.lib_login.LoginActivity
import com.oschina.lib_core.base.BaseActivity
import com.oschina.lib_core.cache.UserCache
import com.oschina.lib_core.constant.OSConfig
import com.oschina.lib_network.OSNetwork
import com.z.lib_core.network.ParamBuilder
import com.z.lib_core.utils.RxLifeCycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    override fun init() {
        XXPermissions.with(this)
            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            .request(object : OnPermission {
                override fun noPermission(denied: MutableList<String>?, never: Boolean) {
                }

                override fun hasPermission(granted: MutableList<String>?, all: Boolean) {
                }
            });
    }

    override fun getLayoutId(): Int = R.layout.activity_main;


    fun onLogin(view: View) {
        if (UserCache.getInstance().code.isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            OSNetwork.getOAuthInter().getToken(
                ParamBuilder.builder()
                    .put("client_id", OSConfig.CLIENT_ID)
                    .put("client_secret", OSConfig.CLIENT_SECRET)
                    .put("grant_type", "authorization_code")
                    .put("redirect_uri", OSConfig.CALLBACK_URL)
                    .put("code", UserCache.getInstance().code)
                    .put("dataType", "json")
                    .toMap()
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(RxLifeCycleUtils.autoDispose(this))
                .subscribe({
                    Logger.d(JSON.toJSONString(it))
                }, {
                    it.printStackTrace()
//                    Logger.e("请求错误", it.message)
                })

        }
    }

    fun onLogout(view: View) {
        UserCache.getInstance().logout();
    }

}


