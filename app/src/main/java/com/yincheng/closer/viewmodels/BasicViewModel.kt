package com.yincheng.closer.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yincheng.closer.annotations.NonNullOrEmpty
import com.yincheng.closer.helpers.RxHelper
import com.yincheng.closer.helpers.SPHelper
import com.yincheng.closer.network.services.ServiceFactory
import com.yincheng.closer.provider.SP_CREATED_AT
import com.yincheng.closer.provider.SP_IS_LOGIN
import com.yincheng.closer.provider.SP_TOKEN
import com.yincheng.closer.provider.SP_UPDATED_AT
import com.yincheng.closer.provider.models.GithubBasicAuthorizationRequestModel
import com.yincheng.closer.provider.models.User
import com.yincheng.closer.provider.response.github.GithubBasicAuthorizationResponseModel
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import okhttp3.Credentials
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.*

abstract class BasicViewModel : ViewModel() {
    private var authToken: String = ""
    var isLogin = MutableLiveData(false)
    private var mLoginDisposable: Disposable? = null
    private var mRawUserInfoDisposable: Disposable? = null
    private var mUserInfoDisposable: Disposable? = null
    // todo 自定义一个检测String是否为空的注解　＠NonEmpty
    fun githubBasicLogin(@NonNullOrEmpty userName: String, @NonNullOrEmpty password: String) {
        authToken = Credentials.basic(userName, password)
        basicLogin()
    }

    fun wechatLogin() {

    }

    private fun linkedLogin() {
        mLoginDisposable = RxHelper.getObservable(
            ServiceFactory.getGithubServiceJson(authToken).authorizations(
                GithubBasicAuthorizationRequestModel.generate()
            )
        ).subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .flatMap(Function<Response<GithubBasicAuthorizationResponseModel>, ObservableSource<User>> { null })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun basicLogin() {
        mLoginDisposable = RxHelper.getObservable(
            ServiceFactory.getGithubServiceJson(authToken).authorizations(
                GithubBasicAuthorizationRequestModel.generate()
            )
        ).subscribe({
            applyLoginResult(it)
        }, {
            applyLoginResult(null)
        })
    }

    private fun getRawUserInfo() {
        mRawUserInfoDisposable = RxHelper.getObservable(
            ServiceFactory.getGithubServiceJson(authToken).getRawUserInfo(true)
        ).subscribe({ applyRawUserInfo(it) }, { applyRawUserInfo(null) })
    }

    private fun getUserInfo() {
        mUserInfoDisposable = RxHelper.getObservable(
            ServiceFactory.getGithubServiceJson(authToken).getUserInfo(true)
        ).subscribe({ applyUserInfo(it) }, { applyUserInfo(null) })
    }

    private fun applyLoginResult(result: Response<GithubBasicAuthorizationResponseModel>?) {
        if (result != null) {
            if (result.isSuccessful && result.body() != null) {
                val responseModel = result.body()
                SPHelper.putIgnoreResult(SP_TOKEN, responseModel?.token as String)
                SPHelper.putIgnoreResult(SP_CREATED_AT, responseModel.createdAt as Date)
                SPHelper.putIgnoreResult(SP_UPDATED_AT, responseModel.updatedAt as Date)
                SPHelper.putIgnoreResult(SP_IS_LOGIN, true)
                isLogin.postValue(true)
                Log.i("login_rawUser_user", "token: ${responseModel.token}")
                getRawUserInfo()
                getUserInfo()
            } else {
                isLogin.postValue(false)
            }
        } else {
            Log.i("login_rawUser_user", "get token failed!")
        }
    }

    private fun applyRawUserInfo(rawUserInfo: ResponseBody?) {
        if (rawUserInfo != null) {
            Log.i("login_rawUser_user", "rawUser: ${rawUserInfo.string()}")
        } else {
            Log.i("login_rawUser_user", "get rawUserFailed")
        }
    }

    private fun applyUserInfo(userInfo: Response<User>?) {
        if (userInfo != null) {
            val user = userInfo.body()
            Log.i("login_rawUser_user", "user: ${user?.name} ")
        } else {
            Log.i("login_rawUser_user", "get user failed")
        }
    }
}