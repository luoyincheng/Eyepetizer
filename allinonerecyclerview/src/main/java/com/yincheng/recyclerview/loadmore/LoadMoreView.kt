package com.yincheng.recyclerview.loadmore

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.yincheng.recyclerview.adapters.BaseViewHolder

abstract class LoadMoreView {

    var loadMoreStatus = STATUS_DEFAULT
    /**
     * No more data is hidden
     *
     * @return true for no more data hidden load more
     */
    @get:Deprecated("Use {@link BaseQuickAdapter#loadMoreEnd(boolean)} instead.")
    var isLoadEndGone = false
        private set

    val isLoadEndMoreGone: Boolean
        get() = if (loadEndViewId == 0) {
            true
        } else isLoadEndGone

    /**
     * load more layout
     *
     * @return
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * loading view
     *
     * @return
     */
    @get:IdRes
    protected abstract val loadingViewId: Int

    /**
     * load fail view
     *
     * @return
     */
    @get:IdRes
    protected abstract val loadFailViewId: Int

    /**
     * load end view, you can return 0
     *
     * @return
     */
    @get:IdRes
    protected abstract val loadEndViewId: Int

    fun convert(holder: BaseViewHolder) {
        when (loadMoreStatus) {
            STATUS_LOADING -> {
                visibleLoading(holder, true)
                visibleLoadFail(holder, false)
                visibleLoadEnd(holder, false)
            }
            STATUS_FAIL -> {
                visibleLoading(holder, false)
                visibleLoadFail(holder, true)
                visibleLoadEnd(holder, false)
            }
            STATUS_END -> {
                visibleLoading(holder, false)
                visibleLoadFail(holder, false)
                visibleLoadEnd(holder, true)
            }
            STATUS_DEFAULT -> {
                visibleLoading(holder, false)
                visibleLoadFail(holder, false)
                visibleLoadEnd(holder, false)
            }
        }
    }

    private fun visibleLoading(holder: BaseViewHolder, visible: Boolean) {
        holder.setGone(loadingViewId, visible)
    }

    private fun visibleLoadFail(holder: BaseViewHolder, visible: Boolean) {
        holder.setGone(loadFailViewId, visible)
    }

    private fun visibleLoadEnd(holder: BaseViewHolder, visible: Boolean) {
        val loadEndViewId = loadEndViewId
        if (loadEndViewId != 0) {
            holder.setGone(loadEndViewId, visible)
        }
    }

    fun setLoadMoreEndGone(loadMoreEndGone: Boolean) {
        this.isLoadEndGone = loadMoreEndGone
    }

    companion object {

        val STATUS_DEFAULT = 1
        val STATUS_LOADING = 2
        val STATUS_FAIL = 3
        val STATUS_END = 4
    }
}
