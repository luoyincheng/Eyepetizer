package com.yincheng.closer.provider.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.yincheng.closer.provider.DB_STORED_FILE_NAME
import com.yincheng.closer.provider.db.AppDatabase
import com.yincheng.closer.provider.models.Mission
import kotlinx.coroutines.coroutineScope

// todo
class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { DatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(DB_STORED_FILE_NAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val missionType = object : TypeToken<List<Mission>>() {}.type
                    val missionList: List<Mission> = Gson().fromJson(jsonReader, missionType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.missionDao().insertMissions(missionList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}