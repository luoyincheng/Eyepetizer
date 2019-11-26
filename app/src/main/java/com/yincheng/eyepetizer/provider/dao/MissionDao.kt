package com.yincheng.eyepetizer.provider.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yincheng.eyepetizer.provider.models.Mission

@Dao
interface MissionDao {

    @Query("SELECT * FROM missions ORDER BY name")
    fun getAllMissions(): LiveData<List<Mission>>

    @Query("SELECT * FROM missions WHERE mission_id = :missionId")
    fun getMissionViaId(missionId: String): LiveData<Mission>

    @Query("SELECT * FROM missions WHERE cycleTime = :cycleTime ORDER BY name")
    fun getMissionsViaCycleTime(cycleTime: Int): LiveData<List<Mission>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMissions(missions: List<Mission>)
}