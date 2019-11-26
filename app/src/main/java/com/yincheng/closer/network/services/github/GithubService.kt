package com.yincheng.closer.network.services.github

import androidx.annotation.NonNull
import com.yincheng.closer.provider.models.GithubBasicAuthorizationRequestModel
import com.yincheng.closer.provider.models.User
import com.yincheng.closer.provider.response.github.GithubBasicAuthorizationResponseModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubService {
    @POST("authorizations")
    @Headers("Accept: application/json")
    fun authorizations(@Body githubBasicAuthorizationRequestModel: GithubBasicAuthorizationRequestModel)
            : Observable<Response<GithubBasicAuthorizationResponseModel>>

    @NonNull
    @GET("user")
    fun getUserInfo(@Header("forceNetWork") forceNetWork: Boolean)
            : Observable<Response<User>>

    // 这个请求和上面的一样，但是这个用ResponseBody代替Response<User>，用来查看服务器返回的原始字符串
    @NonNull
    @GET("user")
    fun getRawUserInfo(@Header("forceNetWork") forceNetWork: Boolean)
            : Observable<ResponseBody>

    @GET
    fun getContributions(@Url url: String): Observable<ResponseBody>

    /**
     * MissionListFragment中有使用
     */
    @GET
    fun getContributionsString(@Url url: String): Call<ResponseBody>
}