package kr.co.mobidoo.sauce.ui.dialog.login.module

import android.app.Activity
import android.content.Context
import com.nhn.android.naverlogin.OAuthLoginHandler
import kr.co.mobidoo.sauce.SauceApplication
import kr.co.mobidoo.sauce.contant.ThirdParty
import kr.co.mobidoo.sauce.ui.login.LoginViewModel
import org.koin.java.KoinJavaComponent.inject

class NaverLogin : Login() {
    override lateinit var socialLoginItem: SocialLoginItem
    override lateinit  var context: Context
    override lateinit var userInfoCallBackListener: UserInfoCallBackListener

    private val loginViewModel : LoginViewModel by inject(LoginViewModel::class.java)

    override fun login() {
        val naverLoginModule = SauceApplication.instance?.naverLoginModule

        naverLoginModule?.startOauthLoginActivity(context as Activity, object : OAuthLoginHandler() {
            override fun run(success: Boolean) {
                if (success) {
                    val accessToken  = naverLoginModule.getAccessToken(context)
                    val refreshToken = naverLoginModule.getRefreshToken(context)

                    accessToken?.let {
                        saveSocialLoginToken(it)
                        getNaverUserInfoByToken(it)
                    }

                }
            }

        })
    }

    private fun getNaverUserInfoByToken(token : String) {
        loginViewModel.requestNaverUserInfo2(token) {
            socialLoginItem = SocialLoginItem().apply {
                loginType = ThirdParty.NAVER.type
                socialId = loginViewModel.id.value
                accountEmail = loginViewModel.email.value
                phoneNumber = loginViewModel.mobile.value
                profileImage = loginViewModel.profileImageUrl.value
            }

            userInfoCallBackListener.onSocialLoginInfoReceived(socialLoginItem)
        }
    }

    companion object {
        fun getSingleton() : Login = NaverLogin()
    }
}