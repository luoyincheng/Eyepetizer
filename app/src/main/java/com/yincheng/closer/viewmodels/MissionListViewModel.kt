package com.yincheng.closer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.yincheng.closer.provider.models.Mission
import com.yincheng.closer.provider.repositories.MissionRepository

// todo() internal????
class MissionListViewModel internal constructor(missionRepository: MissionRepository) :
    BasicViewModel() {

    companion object {
        private const val NO_GROW_ZONE = -1
    }

    private val growZoneNumber = MutableLiveData<Int>().apply {
        value =
            NO_GROW_ZONE
    }

    val missions: LiveData<List<Mission>> = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE) {
            missionRepository.getAllMissions()
        } else {
            missionRepository.getMissionsViaCycleTime(it)
        }
    }

    fun setGrowZoneNumber(newNumber: Int) {
        growZoneNumber.value = newNumber
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value =
            NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE
}