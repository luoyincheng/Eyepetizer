package com.yincheng.eyepetizer.provider.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "missions_in_progress",
    // todo foreignKeys 作用？
    foreignKeys = [ForeignKey(
        entity = Mission::class,
        parentColumns = ["mission_id"],
        childColumns = ["mission_id"]
    )],
    // todo indices 作用？
    indices = [Index("mission_id")]
)
class MissionInProgress(
    @ColumnInfo(name = "mission_id") val missionId: String,
    @ColumnInfo(name = "launch_date") val launchDate: Calendar = Calendar.getInstance(),
    // todo 这里初始化是几个意思
    @ColumnInfo(name = "last_watering_date") val lastWateringDate: Calendar = Calendar.getInstance()
) {
    // todo 为什么写在里面 为什么不用plantId作为主键来和plant中保持一致 而且为什么同时要plantId和gardenPlantingId？ 一个不够吗？
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mission_in_progress_id")
    var missionInProgressId: Long = 0
}