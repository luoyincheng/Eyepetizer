package com.yincheng.closer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.closer.provider.repositories.MissionInProgressRepository
import com.yincheng.closer.provider.repositories.MissionRepository
import com.yincheng.closer.viewmodels.MissionDetailViewModel

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