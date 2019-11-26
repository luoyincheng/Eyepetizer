package com.yincheng.eyepetizer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.yincheng.eyepetizer.R;
import com.yincheng.eyepetizer.helpers.TypeFaceHelper;
import com.yincheng.eyepetizer.helpers.ViewHelper;

public class FontTextView extends AppCompatTextView {

    int tintColor = -1;
    boolean selected;

    public FontTextView(@NonNull Context context) {
        this(context, null);
    }

    public FontTextView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

//    @Override public Parcelable onSaveInstanceState() {
//        return StateSaver.saveInstanceState(this, super.onSaveInstanceState());
//    }

//    @Override public void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(StateSaver.restoreInstanceState(this, state));
//        tintDrawables(tintColor);
//        setSelected(selected);
//    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        this.selected = selected;
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        int language = 0;
        if (attributeSet != null) {
            TypedArray tp = context.obtainStyledAttributes(attributeSet, R.styleable.FontTextView);
            try {
                int color = tp.getColor(R.styleable.FontTextView_drawableColor, -1);
                language = tp.getInt(R.styleable.FontTextView_typeface, 0);
                tintDrawables(color);
            } finally {
                tp.recycle();
            }
        }
        if (isInEditMode()) return;
        setFreezesText(true);
        TypeFaceHelper.applyTypeface(this, language);
    }

    public void tintDrawables(@ColorInt int color) {
        if (color != -1) {
            this.tintColor = color;
            Drawable[] drawables = getCompoundDrawablesRelative();
            for (Drawable drawable : drawables) {
                if (drawable == null) continue;
                ViewHelper.tintDrawable(drawable, color);
            }
        }
    }

    public void setEventsIcon(@DrawableRes int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, width / 2, height / 2);
        ScaleDrawable sd = new ScaleDrawable(drawable, Gravity.CENTER, 0.6f, 0.6f);
        sd.setLevel(8000);
        ViewHelper.tintDrawable(drawable, ViewHelper.getTertiaryTextColor(getContext()));
        setCompoundDrawablesWithIntrinsicBounds(sd, null, null, null);
    }
}