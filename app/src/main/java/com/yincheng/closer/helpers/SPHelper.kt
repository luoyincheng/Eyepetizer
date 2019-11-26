package com.yincheng.closer.helpers

import android.content.Context
import android.content.SharedPreferences
import com.yincheng.closer.app.CloserApplication

/**
 * 如果需要返回值就使用commit()方法，否则就使用apply()方法
 */

object SPHelper {
    private const val OURS_SP_NAME = "oursSP"
    private const val OURS_SP_MODE = Context.MODE_PRIVATE  // todo 有时候需要为MODE_APPEND
    private var instance: SharedPreferences? = null

    private fun getSPInstance(name: String, mode: Int): SharedPreferences? {
        if (isNullOrEmpty(name)) return null
        return instance ?: synchronized(this) {
            instance ?: CloserApplication.getApplication().getSharedPreferences(name, mode)
        }
    }

    fun putAndConfirm(key: String, value: Any): Boolean? {
        val editor = getEditor(key, value) ?: return false
        return editor.commit()
    }

    fun putIgnoreResult(key: String, value: Any) {
        getEditor(key, value)?.apply()
    }

    fun removeAndConfirm(key: String) =
        getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().remove(key).commit()

    fun removeIgnoreResult(key: String) {
        getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().remove(key).apply()
    }

    fun getString(key: String): String? {
        return getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.getString(key, "")
    }

    fun getBoolean(key: String): Boolean? {
        return getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.getBoolean(key, false)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getEditor(key: String, value: Any): SharedPreferences.Editor? {
        if (isNullOrEmpty(key)) return null // todo （value == null）为什么总为false
        var editor: SharedPreferences.Editor? = null
        when (value) {
            is String -> editor =
                getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().putString(key, value)
            is Boolean -> editor =
                getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().putBoolean(key, value)
            is Int -> editor = getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().putInt(key, value)
            is Float -> editor =
                getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().putFloat(key, value)
            is Long -> editor =
                getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit().putLong(key, value)
            is Set<*> -> editor =
                getSPInstance(OURS_SP_NAME, OURS_SP_MODE)!!.edit()
                    .putStringSet(key, value as MutableSet<String>)
        }
        return editor
    }
}