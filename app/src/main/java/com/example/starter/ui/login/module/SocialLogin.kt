package com.example.starter.ui.login.module

import androidx.appcompat.app.AppCompatActivity
import com.example.starter.constants.LoginType
import com.example.starter.ui.login.LoginDialog
import com.google.api.Context

class SocialLogin {

    private val loginDialog by lazy { LoginDialog.newInstance() }
    private lateinit var context: Context
    private lateinit var loginType: MutableList<LoginType>

    fun addContext(context: Context): SocialLogin {
        this.context = context
        return this
    }

    fun setLoginType(vararg loginType: LoginType): SocialLogin {
        repeat (loginType.size) {
            this.loginType.add(loginType[it])
        }
        return this
    }

    fun show() {
        loginDialog.show((context as AppCompatActivity).supportFragmentManager, loginDialog.tag)
    }

    companion object {
        fun getSingleton() = SocialLogin()
    }
}