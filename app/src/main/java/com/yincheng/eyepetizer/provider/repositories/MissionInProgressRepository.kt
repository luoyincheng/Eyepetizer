package com.yincheng.eyepetizer.provider.repositories

import com.yincheng.eyepetizer.provider.dao.MissionInProgressDao
import com.yincheng.eyepetizer.provider.models.MissionInProgress
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MissionInProgressRepository private constructor(private val missionInProgressDao: MissionInProgressDao) {

    companion object {
        @Volatile
        private var instance: MissionInProgressRepository? = null

        fun getInstance(missionInProgressDao: MissionInProgressDao) =
            instance ?: synchronized(this) {
                instance ?: MissionInProgressRepository(missionInProgressDao).also { instance = it }
            }
    }

    fun getAllMissionsInProgress() = missionInProgressDao.getAllMissionsInProgress()
    fun getMissionInProgressViaId(missionId: String) =
        missionInProgressDao.getMissionInProgressViaId(missionId)

    fun getMissionsAndMissionsInProgress() = missionInProgressDao.getMissionsAndMissionsInProgress()
    /**
     * TODO() kotlin 协程
     */
    suspend fun createMissionInProgress(missionId: String) {
        withContext(IO) {
            val missionInProgress = MissionInProgress(missionId)
            missionInProgressDao.insertMissionInProgress(missionInProgress)
        }
    }

    suspend fun deleteMissionInProgress(MissionInProgress: MissionInProgress) {
        withContext(IO) {
            missionInProgressDao.deleteMissionInProgress(MissionInProgress)
        }
    }
}