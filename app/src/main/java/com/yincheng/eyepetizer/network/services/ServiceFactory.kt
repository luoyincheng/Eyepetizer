package com.yincheng.eyepetizer.network.services

import com.yincheng.eyepetizer.network.base.RetrofitInstance
import com.yincheng.eyepetizer.network.services.github.GithubService
import com.yincheng.eyepetizer.provider.GITHUB_BASE_URL

object ServiceFactory {
    fun getGithubServiceJson(authToken: String): GithubService =
        RetrofitInstance.INSTANCE.getRetrofit(GITHUB_BASE_URL, authToken, "json")
            .create(GithubService::class.java)

    fun getGithubServiceXml(authToken: String): GithubService =
        RetrofitInstance.INSTANCE.getRetrofit(GITHUB_BASE_URL, authToken, "xml")
            .create(GithubService::class.java)

    fun getGithubServiceString(authToken: String): GithubService =
        RetrofitInstance.INSTANCE.getRetrofit(GITHUB_BASE_URL, authToken, null)
            .create(GithubService::class.java)
}