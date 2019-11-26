package com.yincheng.closer.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.widget.TextView;

public class TypeFaceHelper {
    private static SparseArray<Typeface> mTypefaces = new SparseArray<>();


    public static void generateAllAvailableTypefaces(Context context) {
        mTypefaces.put(0, Typeface.createFromAsset(context.getAssets(), "fonts/Arial_Rounded_MT_Bold.ttf"));
        mTypefaces.put(1, Typeface.createFromAsset(context.getAssets(), "fonts/qingliuti.ttf"));
    }

    public static void applyTypeface(TextView textView, int typeFace) {
        Typeface nowTypeFace = mTypefaces.get(typeFace);
        if (nowTypeFace != null) textView.setTypeface(nowTypeFace);
    }
}