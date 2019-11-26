package com.yincheng.closer.provider.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "missions")
data class Mission(
    @PrimaryKey @ColumnInfo(name = "mission_id") val missionId: String,
    val name: String,
    val description: String,
    val cycleTime: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String
) {
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply {
            add(Calendar.DAY_OF_YEAR, wateringInterval)
        }
}