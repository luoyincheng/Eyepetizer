package com.yincheng.eyepetizer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.eyepetizer.provider.repositories.MissionRepository
import com.yincheng.eyepetizer.viewmodels.MissionListViewModel

class MissionListViewModelFactory(private val missionRepository: MissionRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = MissionListViewModel(
        missionRepository
    ) as T
}