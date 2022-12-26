package kr.co.mobidoo.sauce.ui.dialog.login.module

import android.content.Context
import com.securepreferences.SecurePreferences
import kr.co.mobidoo.sauce.util.CommonUtil
import org.koin.java.KoinJavaComponent

abstract class Login {
    val securePreferences : SecurePreferences by KoinJavaComponent.inject(SecurePreferences::class.java)
    abstract var socialLoginItem : SocialLoginItem
    abstract var context : Context
    abstract var userInfoCallBackListener : UserInfoCallBackListener

    fun addContext(context: Context) : Login {
        this.context = context
        return this
    }

    fun addListener(userInfoCallBackListener: UserInfoCallBackListener) : Login {
        this.userInfoCallBackListener = userInfoCallBackListener
        return this
    }

    fun saveSocialLoginToken(accessToken : String?) {
        CommonUtil.saveSauceAccessToken(accessToken.toString())
    }

    abstract fun login()


}