package com.yincheng.eyepetizer.provider

//region appConfig
const val DB_NAME = "closer"
const val DB_STORED_FILE_NAME = "missions.json"
//endregion

//region SP
const val SP_IS_LOGIN = "isLogin"
const val SP_USER_NAME = "userName"
const val SP_PASSWORD = "password"
const val SP_CREATED_AT = "created_at"
const val SP_UPDATED_AT = "updated_at"
const val SP_TOKEN = "token"
//endregion

//region network
const val HTTP_TIME_OUT: Long = 30 * 1000
const val HTTP_MAX_CACHE: Long = 64 * 1024 * 1024
const val HTTP_CACHE_DIR = "closer"

//region github
const val GITHUB_BASE_URL = "https://api.github.com/"
//endregion

//region time
const val MINUTE_OF_SECONDS = 60
const val HOUR_OF_SECONDS = 60 * 60
const val DAY_OF_SECONDS = 24 * HOUR_OF_SECONDS
const val Year_OF_SECONDS = 365 * DAY_OF_SECONDS
//endregion


enum class ResultProcessType {
    TV_SHOW, TV_COUNT_DOWN,
    IV_SHOW,
    SNACKBAR_SHOW, DIALOG_SHOW,
    INTENT_ACTIVITY_JUMP
}