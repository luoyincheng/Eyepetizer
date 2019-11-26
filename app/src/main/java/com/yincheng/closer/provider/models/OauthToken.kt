package com.yincheng.closer.provider.models

import com.google.gson.annotations.SerializedName

class OauthToken {
    @SerializedName("access_token")
    var accessToken: String? = null

    var scope: String? = null
}