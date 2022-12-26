package kr.co.mobidoo.sauce.ui.dialog.login.module

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import kr.co.mobidoo.sauce.contant.ThirdParty
import kr.co.mobidoo.sauce.ui.login.RegisterActivity

class StartRegistor {
    private lateinit var context: Context
    private lateinit var socialLoginItem : SocialLoginItem

    fun addContext(context : Context) : StartRegistor {
        this.context = context
        return this
    }

    fun addSocialLoginItem(socialLoginItem: SocialLoginItem) : StartRegistor {
        this.socialLoginItem = socialLoginItem
        return this
    }

    fun start(launcher: ActivityResultLauncher<Intent>) {
        Intent(context, RegisterActivity::class.java).apply {

            when (socialLoginItem.loginType) {
                ThirdParty.KAKAO.type -> {
                    putExtra("loginType", socialLoginItem.loginType)

                    putExtra("kakaoId", socialLoginItem.socialId)
                    putExtra("nickname", socialLoginItem.nickName)
                    putExtra("gender", socialLoginItem.gender)
                    putExtra("birth", socialLoginItem.birthday)
                    putExtra("email", socialLoginItem.accountEmail)
                    putExtra("phone", socialLoginItem.phoneNumber)
                    putExtra("profileImageUrl", socialLoginItem.profileImage)
                }
                ThirdParty.NAVER.type -> {
                    putExtra("loginType", socialLoginItem.loginType)

                    putExtra("naverId", socialLoginItem.socialId)
                    putExtra("email", socialLoginItem.accountEmail)
                    putExtra("phone", socialLoginItem.phoneNumber)
                    putExtra("profileImageUrl", socialLoginItem.profileImage)
                }
                ThirdParty.FACEBOOK.type -> {
                    putExtra("loginType", socialLoginItem.loginType)

                    putExtra("facebookId", socialLoginItem.socialId)
                    putExtra("nickname", socialLoginItem.nickName)
                    putExtra("profileImageUrl",socialLoginItem. profileImage)
                }
            }

            launcher.launch(this)
        }

    }



    companion object {
        fun getInstance() = StartRegistor()

    }
}