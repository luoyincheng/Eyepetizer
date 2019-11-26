package com.yincheng.closer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.yincheng.closer.provider.models.Mission
import com.yincheng.closer.provider.repositories.MissionInProgressRepository
import com.yincheng.closer.provider.repositories.MissionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * 给属性加上private val 关键词就可以在类内部调用
 */
class MissionDetailViewModel(
    missionRepository: MissionRepository,
    private val missionInProgressRepository: MissionInProgressRepository,
    private val missionId: String
) : ViewModel() {
    val missionAchieved: LiveData<Boolean>
    val mission: LiveData<Mission>

    /**
     * todo init代码块
     */
    init {
        val missionInProgress = missionInProgressRepository.getMissionInProgressViaId(missionId)
        mission = missionRepository.getMissionViaId(missionId)
        // todo
        missionAchieved = missionInProgress.map { it != null }
    }

    fun addMissionToOngoing() {
        // TODO()
        viewModelScope.launch { missionInProgressRepository.createMissionInProgress(missionId) }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}