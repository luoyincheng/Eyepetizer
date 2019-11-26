package com.yincheng.eyepetizer.provider.repositories

import com.yincheng.eyepetizer.provider.dao.MissionDao

class MissionRepository private constructor(private val missionDao: MissionDao) {
    // todo 为什么只有get  没有 insert
    fun getAllMissions() = missionDao.getAllMissions()

    fun getMissionsViaCycleTime(cycleTime: Int) = missionDao.getMissionsViaCycleTime(cycleTime)
    fun getMissionViaId(missionId: String) = missionDao.getMissionViaId(missionId)

    companion object {
        @Volatile
        private var instance: MissionRepository? = null

        fun getInstance(missionDao: MissionDao): MissionRepository {
            return instance ?: synchronized(this) {
                instance
                    ?: MissionRepository(missionDao).also { instance = it }
            }
        }
    }
}