package com.yincheng.eyepetizer.provider.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yincheng.eyepetizer.databinding.ItemMissionBinding
import com.yincheng.eyepetizer.provider.models.Mission
import com.yincheng.eyepetizer.views.fragments.MissionListFragmentDirections
import com.yincheng.recyclerview.views.PhotoOperationsActivity

class MissionListAdapter(val context: Context) :
    ListAdapter<Mission, MissionListAdapter.ViewHolder>(MissionDiffCallback()) {

    init {
        setContext(context)
    }

    companion object {
        private lateinit var mContext: Context

        fun setContext(context: Context) {
            this.mContext = context.applicationContext //内存泄露
        }

        val mOnClickListener = View.OnClickListener {
            val mDirection =
                MissionListFragmentDirections.actionMissionListFragmentToMissionDetailFragment(
                    (it.tag as Mission).missionId
                )//通过tag来获取数据
            it.findNavController().navigate(mDirection)
        }
        val mOnLongClickListener = View.OnLongClickListener {
            val photoOperationsIntent = Intent(mContext, PhotoOperationsActivity::class.java)
            photoOperationsIntent.putExtra(
                "imageUrl", (it.tag as Mission).imageUrl
            )//通过tag来获取数据
            mContext.startActivity(photoOperationsIntent)
            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMissionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mission = getItem(position)
        holder.apply {
            itemView.tag = mission //设置数据
            bind(mission)
        }
    }

    class ViewHolder(private val binding: ItemMissionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Mission) {
            binding.apply {
                mission = item
                clickListener = mOnClickListener
                longClickListener = mOnLongClickListener
                Log.i("testListener", "$clickListener $longClickListener")
                executePendingBindings()
            }
        }
    }

    private class MissionDiffCallback : DiffUtil.ItemCallback<Mission>() {
        override fun areItemsTheSame(oldItem: Mission, newItem: Mission) =
            oldItem.missionId == newItem.missionId

        override fun areContentsTheSame(oldItem: Mission, newItem: Mission) = oldItem == newItem
    }
}