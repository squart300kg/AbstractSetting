package kr.co.mobidoo.sauce.ui.dialog.login.module

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kr.co.mobidoo.sauce.SauceApplication
import kr.co.mobidoo.sauce.contant.ThirdParty
import kr.co.mobidoo.sauce.ui.base.BaseActivity
import kr.co.mobidoo.sauce.util.LogUtil

class FaceBookLogin : Login() {
    override lateinit var socialLoginItem: SocialLoginItem
    override lateinit var context: Context
    override lateinit var userInfoCallBackListener: UserInfoCallBackListener

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    private var facebookLastClickTime: Long? = null
    private lateinit var faceBookLoginButton: LoginButton

    private val MIN_CLICK_INTERVAL = 2000

    private val TAG = "FaceBookLoginLog"

    override fun login() {
        if (context is BaseActivity<*>) {
            (context as BaseActivity<*>).setCallbackManager(callbackManager)
        }

        SauceApplication.instance?.facebookLogout()

        faceBookLoginButton.apply {

            Handler(Looper.getMainLooper()).postDelayed({
                performClick()
            }, 1000)

            registerCallback(
                this@FaceBookLogin.callbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(result: LoginResult?) {
                        // App code
                        val request =
                            GraphRequest.newMeRequest(result?.accessToken) { _, response ->
                                val json = response?.jsonObject

                                if (json != null) {
                                    val id = json.getString("id")
                                    val name = json.getString("name")
                                    val profileImageUrl = json
                                        .getJSONObject("picture")
                                        .getJSONObject("data")
                                        .getString("url")

                                    socialLoginItem = SocialLoginItem().apply {
                                        socialId = id
                                        nickName = name
                                        profileImage = profileImageUrl
                                        loginType = ThirdParty.FACEBOOK.type
                                    }

                                    saveSocialLoginToken(result?.accessToken?.token.toString())

                                    // 페이스북 로그인 2초 동안 호출 막기
                                    if (facebookLastClickTime == null || SystemClock.elapsedRealtime() - facebookLastClickTime!! >= MIN_CLICK_INTERVAL) {
                                        userInfoCallBackListener.onSocialLoginInfoReceived(socialLoginItem)
                                    }

                                    facebookLastClickTime = SystemClock.elapsedRealtime()
                                }
                            }
                        val parameters = Bundle()
                        parameters.putString(
                            "fields",
                            "id,name,link,birthday,first_name,gender,last_name,location,email,picture.type(large)"
                        )
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {
                        // App code
                        LogUtil.D(TAG, "facebook_onCancel")
                    }

                    override fun onError(error: FacebookException) {
                        // App code
                        // facebook logout
//                            showSnackBar(SnackBarType.MESSAGE_TYPE_WARNING.type, "onError = ${error?.message}")
                        LoginManager.getInstance().logOut()
                        LogUtil.D(TAG, "facebook_onError")
                    }
                })
        }

    }

    fun addFaceBookButton(faceBookLoginButton : LoginButton) : Login {
        this.faceBookLoginButton = faceBookLoginButton
        return this
    }

    companion object {
        fun getSingleton() = FaceBookLogin()
    }
}