package com.yincheng.eyepetizer.helpers

import android.content.Context
import java.io.File

object FileHelper {
    fun getCacheDir(context: Context, dirName: String): File {
        var rootDir = context.externalCacheDir  // todo
//        var rootDir = context.cacheDir  // todo 上下区别
        var cacheDir = File(rootDir, dirName)
        if (!cacheDir.exists()) cacheDir.mkdir()
        return cacheDir
    }
}