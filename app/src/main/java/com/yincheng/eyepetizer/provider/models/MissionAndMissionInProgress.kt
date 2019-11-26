package com.yincheng.eyepetizer.provider.models

import androidx.room.Embedded
import androidx.room.Relation

class MissionAndMissionInProgress {

    // todo
    @Embedded
    lateinit var mission: Mission

    @Relation(parentColumn = "mission_id", entityColumn = "mission_id")
    var missionsInProgress: List<MissionInProgress> = arrayListOf()
}