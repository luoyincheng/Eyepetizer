package com.yincheng.closer.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.observe
import com.yincheng.closer.databinding.FragmentMissionListBinding
import com.yincheng.closer.helpers.RxHelper
import com.yincheng.closer.helpers.SPHelper
import com.yincheng.closer.network.services.ServiceFactory
import com.yincheng.closer.provider.SP_TOKEN
import com.yincheng.closer.provider.ViewModelFactoryProvider
import com.yincheng.closer.provider.adapters.MissionListAdapter
import com.yincheng.closer.viewmodels.MissionListViewModel
import com.yincheng.closer.widgets.contributions.ContributionsDay
import com.yincheng.closer.widgets.contributions.ContributionsProvider
import com.yincheng.closer.widgets.contributions.GitHubContributionsView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_mission_list.*
import okhttp3.ResponseBody
import java.util.*

class MissionListFragment : Fragment() {
    private var mContributionsDisposable: Disposable? = null

    private val missionListViewModel: MissionListViewModel by viewModels {
        ViewModelFactoryProvider.provideMissionListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMissionListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MissionListAdapter(this.context!!)
        binding.rvMissionList.adapter = adapter
        binding.rvMissionList.setHasFixedSize(true)
//        binding.rvMissionList.isNestedScrollingEnabled = true
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        getContributions()
        return binding.root
    }

    private fun subscribeUi(adapter: MissionListAdapter) {
        /**
         * todo
         * import androidx.lifecycle.observe
         * observe()方法不是跳转后的方法，而是上方的observe
         * Adds the given [onChanged] lambda as an observer within the lifespan of the given
         * [owner] and returns a reference to observer.
         * The events are dispatched on the main thread. If LiveData already has data
         * set, it will be delivered to the onChanged.
         *
         * The observer will only receive events if the owner is in [Lifecycle.State.STARTED]
         * or [Lifecycle.State.RESUMED] state (active).
         *
         * If the owner moves to the [Lifecycle.State.DESTROYED] state, the observer will
         * automatically be removed.
         *
         * When data changes while the [owner] is not active, it will not receive any updates.
         * If it becomes active again, it will receive the last available data automatically.
         *
         * LiveData keeps a strong reference to the observer and the owner as long as the
         * given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to
         * the observer and the owner.
         *
         * If the given owner is already in [Lifecycle.State.DESTROYED] state, LiveData
         * ignores the call.
         */
        missionListViewModel.missions.observe(viewLifecycleOwner) { missions ->
            /**
             *  Mission may return null, but the [observe] extension function assumes it will not be null.
             *  So there will be a warning（Condition `missions != null` is always `true`） here.
             *  I am not sure if the database return data type should be defined as nullable, Such as `LiveData<List<Mission>?>` .
             */
            if (missions != null) {
                adapter.submitList(missions)
                Log.i("subscribeUi", "missions.zize: ${missions.size}")
            } else {
                Log.i("subscribeUi", "missions获取失败")
            }
        }
    }

    private fun getContributions() {
        mContributionsDisposable = RxHelper
            .getObservable(
                ServiceFactory.getGithubServiceString(SPHelper.getString(SP_TOKEN)!!).getContributions(
                    "https://github.com/users/luoyincheng/contributions"
                )
            )
            .subscribe({ onRawResult(it) }, { onRawResult(null) })
//
//        val call    = ServiceFactory.getGithubServiceString(SPHelper.getString(SP_TOKEN)!!).getContributionsString("https://github.com/users/luoyincheng/contributions")
//        call.enqueue(object : Callback<ResponseBody>{
//            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.i("hahahaha","onResponse:" + response.body())
//            }
//
//            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
//                Log.i("hahahaha","onFailure:" )
//            }
//        })
    }

    private fun onRawResult(result: ResponseBody?) {
        if (result != null) {
            Log.i("getContributions", "onRawResult")
            val rawString = result.string()
            val contributions = ContributionsProvider().getContributions(rawString)
            loadContributions(contributions, gcv_launcher)
        }
    }

    private fun loadContributions(
        contributions: ArrayList<ContributionsDay>?,
        gitHubContributionsView: GitHubContributionsView
    ) {
        Log.i("getContributions", "loadContributions")
        val filter = gitHubContributionsView.getLastContributions(contributions)
        if (filter != null && contributions != null) {
            gitHubContributionsView.drawOnCanvas(filter, contributions)
            gitHubContributionsView.onResponse()
        }
    }
}