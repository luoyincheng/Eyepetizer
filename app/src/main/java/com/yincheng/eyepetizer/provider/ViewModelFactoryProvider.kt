package com.yincheng.eyepetizer.provider

import android.content.Context
import com.yincheng.eyepetizer.provider.db.AppDatabase
import com.yincheng.eyepetizer.provider.repositories.MissionInProgressRepository
import com.yincheng.eyepetizer.provider.repositories.MissionRepository
import com.yincheng.eyepetizer.provider.viewmodelfactories.LoginViewModelFactory
import com.yincheng.eyepetizer.provider.viewmodelfactories.MissionDetailViewModelFactory
import com.yincheng.eyepetizer.provider.viewmodelfactories.MissionListViewModelFactory

// todo object
object ViewModelFactoryProvider {

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        return LoginViewModelFactory()
    }

    /**
     * todo object 修饰后 方法不需要 public关键词 可以直接通过object.方法名调用
     */
    public fun provideMissionListViewModelFactory(context: Context): MissionListViewModelFactory {
        val repository = getMissionRepository(context)
        return MissionListViewModelFactory(repository)
    }

    fun provideMissionDetailViewModelFactory(
        context: Context,
        missionId: String
    ): MissionDetailViewModelFactory {
        return MissionDetailViewModelFactory(
            getMissionRepository(context),
            getMissionInProgressRepository(context),
            missionId
        )
    }

    private fun getMissionRepository(context: Context): MissionRepository {
        return MissionRepository.getInstance(AppDatabase.getInstance(context.applicationContext).missionDao())
    }

    private fun getMissionInProgressRepository(context: Context): MissionInProgressRepository {
        return MissionInProgressRepository.getInstance(AppDatabase.getInstance(context.applicationContext).missionInProgressDao())
    }
}