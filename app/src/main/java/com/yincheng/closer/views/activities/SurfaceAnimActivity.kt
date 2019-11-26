package com.yincheng.closer.views.activities

import android.view.View
import com.yincheng.androidanim.surfaceanim.*
import com.yincheng.androidanim.surfaceanim.common.Direction
import com.yincheng.androidanim.surfaceanim.common.MagicActivity
import com.yincheng.androidanim.surfaceanim.updater.MultiScrapUpdater

abstract class SurfaceAnimActivity : MagicActivity() {
    private var magicSurfaceView: MagicSurfaceView? = null

    override fun onDestroy() {
        super.onDestroy()
        magicSurfaceView?.onDestroy()
    }

    fun show(magicSurfaceView: MagicSurfaceView, view: View) {
        this.magicSurfaceView = magicSurfaceView
        val mMultiUpdater = MultiScrapUpdater(false, Direction.RIGHT)
        mMultiUpdater.addListener(object : MagicUpdaterListener {
            override fun onStart() {
                view.visibility = View.INVISIBLE
            }

            override fun onStop() {
                view.visibility = View.VISIBLE
                magicSurfaceView.visibility = View.GONE
                magicSurfaceView.release()
            }
        })
        magicSurfaceView.render(MagicMultiSurface(view, 20, 10).setUpdater(mMultiUpdater))
    }

    private fun hide(magicSurfaceView: MagicSurfaceView, view: View) {
        val mMultiUpdater = MultiScrapUpdater(true, Direction.RIGHT)
        mMultiUpdater.addListener(object : MagicUpdaterListener {
            override fun onStart() {
                view.visibility = View.INVISIBLE
            }

            override fun onStop() {
                view.visibility = View.INVISIBLE
                magicSurfaceView.visibility = View.GONE
                magicSurfaceView.release()
            }
        })
        magicSurfaceView.render(MagicMultiSurface(view, 20, 10).setUpdater(mMultiUpdater))
    }

    // 返回null禁用 单Surface转场动画
    override fun getPageUpdater(isHide: Boolean): MagicUpdater? {
        return null
    }

    override fun getPageMultiUpdater(isHide: Boolean): MagicMultiSurfaceUpdater {
        return MultiScrapUpdater(isHide, Direction.BOTTOM)
    }
}