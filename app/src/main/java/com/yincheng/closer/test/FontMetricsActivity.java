package com.yincheng.closer.views.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FontMetricsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView v = new MyView(this);
        this.setContentView(v);
    }
}

class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String textToDraw = "平";
        Paint p = new Paint();
        TextPaint textPaint = new TextPaint();
        p.setColor(Color.WHITE);
        textPaint.setColor(Color.WHITE);
        p.setTextSize(1000);
        textPaint.setTextSize(50);
        p.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        Paint.FontMetrics fm = p.getFontMetrics();

        float width = 1080;
        float baseline = 1500f;
        float offsetAscent = baseline + fm.ascent;
        float offsetDescent = baseline + fm.descent;
        float offsetTop = baseline + fm.top;
        float offsetBottom = baseline + fm.bottom;

        canvas.drawText(textToDraw, 0, baseline, p);

        canvas.drawLine(0, baseline, width, baseline, p);//baseline
        canvas.drawText("baseLine", 0, baseline, textPaint);//baseline
        Log.i("fiveLines", "baseline:" + baseline);

        canvas.drawLine(0, offsetAscent, width, offsetAscent, p);//ascent
        canvas.drawText("ascent", 0, offsetAscent, textPaint);//ascent
        Log.i("fiveLines", "offsetAscent:" + offsetAscent);

        canvas.drawLine(0, offsetDescent, width, offsetDescent, p);//descent
        canvas.drawText("descent", 0, offsetDescent, textPaint);//ascent
        Log.i("fiveLines", "offsetDescent:" + offsetDescent);

        canvas.drawLine(0, offsetTop, width, offsetTop, p);//top
        canvas.drawText("top", 0, offsetTop, textPaint);//ascent
        Log.i("fiveLines", "offsetTop:" + offsetTop);

        canvas.drawLine(0, offsetBottom, width, offsetBottom, p);//bottom
        canvas.drawText("bottom", 0, offsetBottom, textPaint);//ascent
        Log.i("fiveLines", "offsetBottom:" + offsetBottom);


        /*
         * 这个高度是刚好能同时将中文和英文完全显示所需要的最小高度
         * 由于中文和英文在显示效果上不一致的问题，需要对全中文、全英文、中英混输三种情况都区别对待
         */
//        Rect rect = new Rect();
//        p.getTextBounds(textToDraw, 0, textToDraw.length(), rect);
//        float topLine = offsetTop + 210;
//        RectF rectF = new RectF(500, topLine, 600, topLine + rect.height());
//        canvas.drawRect(rectF, p);
//        canvas.drawText("minTextHeight", 500, topLine, textPaint);


        /*
         * 中文
         * 绘制刚好包括注中文上面和下面的横线
         * baseline:1500
         * offsetAscent:572.2656
         * offsetDescent:1744.1406
         * offsetTop:443.84766
         * offsetBottom:1770.9961
         */
        float topLine = offsetTop + 171;
        float bottomLine = topLine + 918;
        p.setColor(Color.parseColor("#ff0000"));
        canvas.drawLine(0, topLine, getWidth(), topLine, p);
        canvas.drawLine(0, bottomLine, getWidth(), bottomLine, p);

        float totalHeight = fm.bottom - fm.top;  // 1327.1484
        float paddingTop = topLine - offsetTop; // 217
        float extraScaleRatio = 918 * 1.0f / totalHeight; // 0.6917086
        float paddingRatio = 217 * 1.0f / totalHeight; // 0.16350846
        Log.i("fiveLines", totalHeight + ":" + paddingTop + ":" + extraScaleRatio + ":" + paddingRatio);

        /*
         * 英文
         */
//        float aa = fm.bottom - fm.descent;
//
//        float topLine = offsetTop + 338 -aa * 0.5f;
//        float bottomLine = topLine + 918 + aa * 0.5f;
//        p.setColor(Color.parseColor("#ff0000"));
//        canvas.drawLine(0, topLine, getWidth(), topLine, p);
//        canvas.drawLine(0, bottomLine, getWidth(), bottomLine, p);
//
//        float totalHeight = fm.bottom - fm.top;  // 1327.1484
//        float paddingTop = topLine - offsetTop; // 217
//        float extraScaleRatio = 918 * 1.0f / totalHeight; // 0.6917086
//        float paddingRatio = 217 * 1.0f / totalHeight; // 0.16350846
//        Log.i("fiveLines", totalHeight + ":" + paddingTop + ":" + extraScaleRatio + ":" + paddingRatio);
    }
}