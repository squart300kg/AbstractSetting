package kr.co.mobidoo.sauce.ui.dialog.login.module

data class SocialLoginItem(
    var socialId: String? = null,
    var nickName: String? = null,
    var profileImage: String? = null,
    var gender: String? = null,
    var birthday: String? = null,
    var accountEmail: String? = null,
    var phoneNumber: String? = null,
    var countryCode: String? = null,
    var loginType : String? = null
)
