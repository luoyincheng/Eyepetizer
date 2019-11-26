package com.yincheng.eyepetizer.provider.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yincheng.eyepetizer.provider.models.MissionAndMissionInProgress
import com.yincheng.eyepetizer.provider.models.MissionInProgress

@Dao
interface MissionInProgressDao {

    @Query("SELECT * FROM missions_in_progress")
    fun getAllMissionsInProgress(): LiveData<List<MissionInProgress>>

    @Query("SELECT * FROM missions_in_progress where mission_in_progress_id = :missionInProgressId")
    fun getMissionInProgressViaMissionInProgressId(missionInProgressId: Long): LiveData<MissionInProgress>

    @Query("SELECT * FROM missions_in_progress where mission_id = :missionId")
    // todo 为什么类型是<MissionInProgress?>
    fun getMissionInProgressViaId(missionId: String): LiveData<MissionInProgress?>

    @Transaction
    @Query("select * from missions")
    fun getMissionsAndMissionsInProgress(): LiveData<List<MissionAndMissionInProgress>>

    // todo 还能有返回值？？
    @Insert
    fun insertMissionInProgress(MissionInProgress: MissionInProgress): Long

    @Delete
    fun deleteMissionInProgress(MissionInProgress: MissionInProgress)
}