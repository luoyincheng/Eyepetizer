package com.yincheng.eyepetizer.provider.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.yincheng.eyepetizer.helpers.Converters
import com.yincheng.eyepetizer.provider.DB_NAME
import com.yincheng.eyepetizer.provider.dao.MissionDao
import com.yincheng.eyepetizer.provider.dao.MissionInProgressDao
import com.yincheng.eyepetizer.provider.models.Mission
import com.yincheng.eyepetizer.provider.models.MissionInProgress
import com.yincheng.eyepetizer.provider.workers.DatabaseWorker

@Database(entities = [Mission::class, MissionInProgress::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun missionDao(): MissionDao
    abstract fun missionInProgressDao(): MissionInProgressDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return instance ?: synchronized(this) {
                instance ?: buildAppDatabase(context).also { instance = it }
            }
        }

        private fun buildAppDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<DatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}