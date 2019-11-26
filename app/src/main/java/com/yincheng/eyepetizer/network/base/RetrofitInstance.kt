package com.yincheng.eyepetizer.network.base

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.yincheng.eyepetizer.app.EyepetizerApplication
import com.yincheng.eyepetizer.helpers.FileHelper
import com.yincheng.eyepetizer.provider.HTTP_CACHE_DIR
import com.yincheng.eyepetizer.provider.HTTP_MAX_CACHE
import com.yincheng.eyepetizer.provider.HTTP_TIME_OUT
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

enum class RetrofitInstance {
    INSTANCE;

    companion object ParseType {
        const val json = "json"
        const val xml = "xml"
    }

    private var token: String? = null
    private val allRetrofits = HashMap<String, Retrofit>()
    private var mOkHttpClient: OkHttpClient? = null

    private fun provideOkHttpClient(): OkHttpClient? {
        if (mOkHttpClient == null) {
            val cache = Cache(
                FileHelper.getCacheDir(EyepetizerApplication.getApplication(), HTTP_CACHE_DIR),
                HTTP_MAX_CACHE
            )
            mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(HTTP_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(AuthenticationInterceptor(token))
                .cache(cache)
                .build()
        }
        return mOkHttpClient
    }

    private fun generateNewRetrofit(baseUrl: String, parseType: String?) {
        val retrofitBuilder = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient()!!)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        if (parseType != null) {
            when (parseType) {
                "json" -> retrofitBuilder.addConverterFactory(GsonConverterFactory.create()) // todo to un factory
                "xml" -> retrofitBuilder.addConverterFactory(SimpleXmlConverterFactory.create())
            }
        }
        allRetrofits["$baseUrl-$parseType"] = retrofitBuilder.build()
    }

    fun getRetrofit(@NonNull baseUrl: String, @Nullable token: String, parseType: String?): Retrofit {
        this.token = token
        val key = "$baseUrl-$parseType"
        if (!allRetrofits.containsKey(key)) generateNewRetrofit(baseUrl, parseType)
        val retrofit = allRetrofits[key]
        requireNotNull(retrofit) { "Oops,retrofit generate failed!" }
        return retrofit
    }
}