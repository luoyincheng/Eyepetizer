package com.yincheng.eyepetizer.network.services.github

import com.yincheng.eyepetizer.provider.models.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/repositories")
    fun searchRepositories(
        @Query(
            value = "q",
            encoded = true
        ) query: String, @Query("page") page: Long
    )
            : Observable<Repo>
}