package com.yincheng.eyepetizer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.eyepetizer.provider.repositories.MissionInProgressRepository
import com.yincheng.eyepetizer.provider.repositories.MissionRepository
import com.yincheng.eyepetizer.viewmodels.MissionDetailViewModel

class MissionDetailViewModelFactory(
    private val missionRepository: MissionRepository,
    private val missionInProgressRepository: MissionInProgressRepository,
    private val missionId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MissionDetailViewModel(
            missionRepository,
            missionInProgressRepository,
            missionId
        ) as T
    }
}