package com.yincheng.eyepetizer.viewmodels

class LoginViewModel(userName: String?, password: String?) : BasicViewModel() {
    var mUserName: String?
    var mPassword: String?

    init {
        mUserName = userName
        mPassword = password
    }
}