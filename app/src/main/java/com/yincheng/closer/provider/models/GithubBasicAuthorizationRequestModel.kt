package com.yincheng.closer.provider.models

import com.google.gson.annotations.SerializedName

class GithubBasicAuthorizationRequestModel {
    var scopes: List<String>? = null
    var note: String? = null
    var noteUrl: String? = null
    @SerializedName("client_id")
    var clientId: String? = null
    @SerializedName("client_secret")
    var clientSecret: String? = null

    companion object {
        fun generate(): GithubBasicAuthorizationRequestModel {
            val model = GithubBasicAuthorizationRequestModel()
            model.scopes = listOf("user", "repo", "gist", "notifications")
            model.note = "com.thirtydegreesray.openhub"
            model.noteUrl = "https://github.com/ThirtyDegreesRay/OpenHub/CallBack"
            model.clientId = "8f7213694e115df205fb"
            model.clientSecret = "82c57672382db5c7b528d79e283c398ad02e3c3f"
            return model
        }
    }
}