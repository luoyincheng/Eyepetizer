package com.yincheng.closer.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.yincheng.closer.R
import com.yincheng.closer.databinding.FragmentMissionDetailBinding
import com.yincheng.closer.provider.ViewModelFactoryProvider
import com.yincheng.closer.viewmodels.MissionDetailViewModel

class MissionDetailFragment : Fragment() {

    // todo by 关键词
    private val args: MissionDetailFragmentArgs by navArgs()
    // todo by 关键词
    private val missionDetailViewModel: MissionDetailViewModel by viewModels {
        ViewModelFactoryProvider.provideMissionDetailViewModelFactory(
            requireActivity(),
            args.missionId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentMissionDetailBinding>(
                inflater,
                R.layout.fragment_mission_detail,
                container,
                false
            ).apply {
                viewModel = missionDetailViewModel
                lifecycleOwner = this@MissionDetailFragment // todo
                fab.setOnClickListener { view ->
                    missionDetailViewModel.addMissionToOngoing()
                    Snackbar
                        .make(view, "Added mission to garden", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        return binding.root
    }
}