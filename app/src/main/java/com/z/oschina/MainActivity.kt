package com.z.oschina

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.oschin.lib_login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        XXPermissions.with(this)
            .permission(Permission.WRITE_EXTERNAL_STORAGE)
            .permission(Permission.READ_EXTERNAL_STORAGE)
            .request(object : OnPermission {
                override fun noPermission(denied: MutableList<String>?, never: Boolean) {
                }

                override fun hasPermission(granted: MutableList<String>?, all: Boolean) {
                }
            });


    }

    fun onLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}