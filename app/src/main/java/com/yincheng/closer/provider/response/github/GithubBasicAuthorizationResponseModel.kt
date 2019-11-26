package com.yincheng.closer.provider.response.github

import com.google.gson.annotations.SerializedName
import com.yincheng.closer.provider.models.OauthToken
import java.util.*

class GithubBasicAuthorizationResponseModel {
    public var id: Int? = null
    public var url: String? = null
    public var token: String? = null
    @SerializedName("created_at")
    public var createdAt: Date? = null
    @SerializedName("updated_at")
    public var updatedAt: Date? = null
    public var scopes: List<String>? = null

    fun generateFromOauthToken(oauthToken: OauthToken): GithubBasicAuthorizationResponseModel {
        var basicToken = GithubBasicAuthorizationResponseModel()
        basicToken.token = oauthToken.accessToken
        basicToken.scopes = oauthToken.scope?.split(",")
        return basicToken
    }
}