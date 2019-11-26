package com.yincheng.recyclerview.views

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.yincheng.library.R
import com.yincheng.provider.cache.GlideCacheUtil.getCachedFile
import com.yincheng.recyclerview.util.ImageUtils
import kotlinx.android.synthetic.main.activity_photo_operations.*

class PhotoOperationsActivity : AppCompatActivity() {
    private var startTime: Long = 0L
    private var layoutChangedTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_operations)
        Log.i("lifeCycle", "onCreate()")
        window.setBackgroundDrawable(null)
        startTime = System.currentTimeMillis()
        val imageUrl = intent.getStringExtra("imageUrl")
        Glide.with(this).load(getCachedFile(this, imageUrl) ?: imageUrl)
            .into(iv_source)
        iv_source.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            layoutChangedTime += 1
            if (layoutChangedTime == 2) {
                Log.i("lifeCycle", "LayoutChanged: ${iv_source.width}")
                generateNewPngAndLoadIt(iv_source)
//                val bitmapCenter = generateBitmapWithDraw(iv_source)
//                iv_compressed_left.setImageBitmap(bitmapCenter)
//                iv_compressed_center.setImageBitmap(bitmapCenter)
//                iv_compressed_right.setImageBitmap(bitmapCenter)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i("lifeCycle", "onAttachedToWindow()")
    }

    /**
     * todo to un
     * 在这个方法中设置监听，最多只会收到一次LayoutChanged事件(有可能一次都收不到哦)，如果放在onCreate()方法中就会收到两次，在这两次中只有第二次能获取到图片的绘制信息。
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.i(
            "lifeCycle",
            "onWindowFocusChanged():usedTime: ${System.currentTimeMillis() - startTime}"
        )
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
        Log.i("lifeCycle", "onWindowAttributesChanged()")
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.i("lifeCycle", "onEnterAnimationComplete()")
    }

    private fun generateBitmapWithDraw(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return bitmap
    }

    private fun generateNewPngAndLoadIt(imageView: AppCompatImageView) {
        val compressedBitmap: Bitmap
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache(true)
        val drawingCache = imageView.drawingCache
        if (drawingCache != null) {
            val bitmap = Bitmap.createBitmap(drawingCache)
            Log.i("loadListener", "drawingCache exists ${bitmap.width}")
            imageView.isDrawingCacheEnabled = false
            compressedBitmap = ImageUtils.compressByScale(bitmap, 300, 300)
            iv_compressed_left.setImageBitmap(compressedBitmap)
            iv_compressed_center.setImageBitmap(compressedBitmap)
            iv_compressed_right.setImageBitmap(compressedBitmap)
            clpb_image.hide()
        } else {
            Log.i("loadListener", "drawingCache not exists")
        }
    }

    interface Callback<T> {
        fun call(t: T)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBitmapFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(
                    window,
                    Rect(
                        locationOfViewInWindow[0],
                        locationOfViewInWindow[1],
                        locationOfViewInWindow[0] + view.width,
                        locationOfViewInWindow[1] + view.height
                    ),
                    bitmap,
                    { copyResult ->
                        if (copyResult == PixelCopy.SUCCESS) {
                            callback(bitmap)
                        }
                        // possible to handle other result codes ...
                    },
                    Handler()
                )
            } catch (e: IllegalArgumentException) {
                // PixelCopy may throw IllegalArgumentException, make sure to handle it
                e.printStackTrace()
            }
        }
    }
}