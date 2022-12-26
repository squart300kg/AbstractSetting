package kr.co.mobidoo.sauce.ui.dialog.login.module

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import kr.co.mobidoo.sauce.contant.ThirdParty
import kr.co.mobidoo.sauce.util.LogUtil

class KakaoLogin : Login() {
    private val TAG = "KakaoLoginLog"

    override lateinit var socialLoginItem : SocialLoginItem
    override lateinit var context: Context
    override lateinit var userInfoCallBackListener: UserInfoCallBackListener

    override fun login() {
        when (isKakaoTalkInstalled()) {
            true -> loginKakaoTalkWithApp()
            false -> loginKakaoTalkWithWeb()
        }
    }

    private fun isKakaoTalkInstalled(): Boolean {
        return UserApiClient.instance.isKakaoTalkLoginAvailable(context)
    }


    private fun loginKakaoTalkWithWeb() {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                LogUtil.E(TAG, "로그인 실패 : $error")
            }
            else if (token != null) {

                saveSocialLoginToken(token.accessToken)

                getKakaoUserInfo()
            }
        }
    }

    private fun loginKakaoTalkWithApp() {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                LogUtil.E(TAG, "로그인 실패 : $error")
            }
            else if (token != null) {

                saveSocialLoginToken(token.accessToken)

                getKakaoUserInfo()
            }
        }
    }

    private fun getKakaoUserInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                LogUtil.E(TAG, "사용자 정보 요청 실패 : $error")
            }
            else if (user != null) {
                socialLoginItem = SocialLoginItem().apply {
                    accountEmail = user.kakaoAccount?.email
                    birthday = user.kakaoAccount?.birthyear + "-01-01"
                    gender = user.kakaoAccount?.gender?.name?.substring(0, 1)
                    socialId = user.id.toString()
                    nickName = user.kakaoAccount?.profile?.nickname
                    profileImage = user.kakaoAccount?.profile?.profileImageUrl
                    loginType = ThirdParty.KAKAO.type
                    user.kakaoAccount?.phoneNumber?.let {
                        val totalPhoneNumber = user.kakaoAccount?.phoneNumber?.split(" ")

                        countryCode = totalPhoneNumber?.get(0)
                        phoneNumber = totalPhoneNumber?.get(1)?.replace("-", "")
                    }
                }

                userInfoCallBackListener.onSocialLoginInfoReceived(socialLoginItem)
            }
        }
    }

    companion object {
        fun getSingleton() : Login = KakaoLogin()
    }
}

