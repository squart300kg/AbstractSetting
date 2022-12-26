package com.example.starter.ui.login

import android.os.Bundle
import android.view.View
import com.example.starter.R
import com.example.starter.base.BaseBottomDialog
import com.example.starter.constants.LoginType
import com.example.starter.databinding.FragmentDialogBottomLoginBinding
import kr.co.mobidoo.sauce.ui.dialog.login.module.*

class LoginDialog : BaseBottomDialog<FragmentDialogBottomLoginBinding>(R.layout.fragment_dialog_bottom_login) {

    private val loginListener = object : UserInfoCallBackListener {
        override fun onSocialLoginInfoReceived(socialLoginItem: SocialLoginItem) {

            // TODO: process user data with your custom logic

            dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            btnLoginKakao.setOnClickListener {
                loginFor(LoginType.KAKAO)
            }
            btnLoginNaver.setOnClickListener {
                loginFor(LoginType.NAVER)
            }
            btnLoginFacebook.setOnClickListener {
                loginFor(LoginType.FACEBOOK)
            }
        }
    }

    private fun loginFor(type : LoginType) {
       when(type) {
            LoginType.NAVER -> NaverLogin.getSingleton()
            LoginType.FACEBOOK -> FaceBookLogin.getSingleton().addFaceBookButton(dataBinding.facebookLoginButton)
            LoginType.KAKAO -> KakaoLogin.getSingleton()
            else -> throw Exception("unknown login type")
        }.run {
            addContext(requireActivity())
            addListener(loginListener)
            login()
        }
    }

    companion object {
        fun newInstance() = LoginDialog()
    }
}