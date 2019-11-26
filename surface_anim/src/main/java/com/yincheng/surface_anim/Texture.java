package com.yincheng.surface_anim;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * 纹理持有要执行动画的view所生成的bitmap
 */
class Texture {

    int mId;
    int mIndex;
    Bitmap mBmp;

    Texture(Bitmap bmp) {
        Log.i("cycleWay", "根据bitmap生成texture纹理: " + (bmp == null));
        this.mBmp = bmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Texture)) {
            return false;
        }
        Texture t = (Texture) obj;
        if (mBmp == null) {
            if (t.mBmp != null) {
                return false;
            }
        } else {
            if (!mBmp.equals(t.mBmp)) {
                return false;
            }
        }
        return mId == t.mId && mIndex == t.mIndex;
    }
}
