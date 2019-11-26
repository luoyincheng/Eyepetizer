package com.yincheng.eyepetizer.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

import com.yincheng.eyepetizer.helpers.StringUtils;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class FullFilledTextView extends AppCompatTextView {
    //    private Paint mPaint;
//    private TextPaint mTextPaint;
    private boolean isChinese;

    public FullFilledTextView(Context context) {
        super(context);
        init();
    }

    public FullFilledTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FullFilledTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isChinese = StringUtils.isChineseWithPunctuation(getText());
//        mPaint = new Paint(Color.BLUE);
//        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(1);

//        mTextPaint = new TextPaint(Color.WHITE);
//        mTextPaint.setAntiAlias(true);
//        mTextPaint.setTextSize(40);
    }

    /**
     * 中英文高度占的位置不一样
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        CharSequence charSequence = getText().toString();
        float measuredTextWidth = getPaint().measureText(charSequence, 0, charSequence.length());
        float widthRatio = measuredTextWidth * 1.0f / w;
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        float fullTextHeight = fontMetrics.bottom - fontMetrics.top;
        float heightRatio = fullTextHeight * 1.0f / h;
        float currentTextSizeInPx = getTextSize();
        if (widthRatio > heightRatio) {
            setTextSize(COMPLEX_UNIT_PX, currentTextSizeInPx / widthRatio);
        } else {
            if (isChinese)
                setTextSize(COMPLEX_UNIT_PX, (float) (currentTextSizeInPx / heightRatio / 0.6917086));
            else {

            }
        }
        Paint.FontMetrics newFontMetrics = getPaint().getFontMetrics();
        float newFullHeight = newFontMetrics.bottom - newFontMetrics.top;
        Log.i("afadfafd", newFullHeight * 0.6917086 + ":" + h);
        if (isChinese)
            setPadding(0, (int) (-newFullHeight * 0.16350846), 0, 0);
        else setPadding(0, (int) (-newFullHeight * 0.16350846), 0, 0);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
//        float drawingBaseLine = -fontMetrics.top;
//        canvas.drawLine(0, drawingBaseLine, getWidth(), drawingBaseLine, mPaint);
//        canvas.drawLine(0, drawingBaseLine + fontMetrics.ascent, getWidth(), drawingBaseLine + fontMetrics.ascent, mPaint);
//        canvas.drawLine(0, drawingBaseLine + fontMetrics.descent, getWidth(), drawingBaseLine + fontMetrics.descent, mPaint);
//        canvas.drawLine(0, drawingBaseLine + fontMetrics.bottom - 1, getWidth(), drawingBaseLine + fontMetrics.bottom - 1, mPaint);
//        canvas.drawLine(0, drawingBaseLine + fontMetrics.top + 1, getWidth(), drawingBaseLine + fontMetrics.top + 1, mPaint);
//
//        float ad = (fontMetrics.bottom - (fontMetrics.ascent - fontMetrics.top)) * 0.5f;
//        canvas.drawLine(0, drawingBaseLine + ad, getWidth(), drawingBaseLine + ad, mPaint);
//        canvas.drawLine(0, drawingBaseLine + fontMetrics.ascent + ad, getWidth(), drawingBaseLine + fontMetrics.ascent + ad, mPaint);
//    }
}
