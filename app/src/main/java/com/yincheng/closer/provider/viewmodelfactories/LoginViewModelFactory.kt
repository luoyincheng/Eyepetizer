package com.yincheng.closer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.closer.helpers.SPHelper
import com.yincheng.closer.provider.SP_PASSWORD
import com.yincheng.closer.provider.SP_USER_NAME
import com.yincheng.closer.viewmodels.LoginViewModel

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(SPHelper.getString(SP_USER_NAME), SPHelper.getString(SP_PASSWORD)) as T
    }
}