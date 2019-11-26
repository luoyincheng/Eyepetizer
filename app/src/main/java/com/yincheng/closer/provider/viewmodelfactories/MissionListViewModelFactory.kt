package com.yincheng.closer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.closer.provider.repositories.MissionRepository
import com.yincheng.closer.viewmodels.MissionListViewModel

class MissionListViewModelFactory(private val missionRepository: MissionRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = MissionListViewModel(
        missionRepository
    ) as T
}