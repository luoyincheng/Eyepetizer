package com.yincheng.androidanim.surfaceanim.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.yincheng.androidanim.R;
import com.yincheng.androidanim.surfaceanim.MagicMultiSurface;
import com.yincheng.androidanim.surfaceanim.MagicMultiSurfaceUpdater;
import com.yincheng.androidanim.surfaceanim.MagicSurface;
import com.yincheng.androidanim.surfaceanim.MagicSurfaceMatrixUpdater;
import com.yincheng.androidanim.surfaceanim.MagicSurfaceModelUpdater;
import com.yincheng.androidanim.surfaceanim.MagicSurfaceView;
import com.yincheng.androidanim.surfaceanim.MagicUpdater;
import com.yincheng.androidanim.surfaceanim.MagicUpdaterListener;
import com.yincheng.androidanim.surfaceanim.updater.WaveAnimUpdater;

public abstract class MagicActivity extends AppCompatActivity {
    private MagicSurfaceView mPageSurfaceView;
    private FrameLayout mPageViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_magic);
        mPageViewContainer = findViewById(R.id.page_view_container);
        mPageSurfaceView = findViewById(R.id.page_surface_view);
        if (!show()) {
            mPageViewContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPageSurfaceView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!hide()) {
            finish();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mPageViewContainer, true);
    }

    protected MagicUpdater getPageUpdater(boolean isHide) {
        if (isHide) {
            return new WaveAnimUpdater(true, Direction.RIGHT, false);
        } else {
            return new WaveAnimUpdater(false, Direction.RIGHT, false);
        }
    }

    protected MagicMultiSurfaceUpdater getPageMultiUpdater(boolean isHide) {
        return null;
    }

    protected int pageAnimRowCount() {
        return 30;
    }

    protected int pageAnimColCount() {
        return 30;
    }

    protected void onPageAnimEnd() {
    }

    private boolean show() {
        MagicUpdater updater = getPageUpdater(false);
        if (updater != null) {
            return showWithSurface(updater);
        }
        MagicMultiSurfaceUpdater multiUpdater = getPageMultiUpdater(false);
        if (multiUpdater != null) {
            return showWithMultiSurface(multiUpdater);
        }
        return false;
    }

    private boolean showWithSurface(MagicUpdater updater) {
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mPageViewContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mPageViewContainer.setVisibility(View.VISIBLE);
                mPageSurfaceView.setVisibility(View.GONE);
                mPageSurfaceView.release();
                onPageAnimEnd();
            }
        });
        // todo 核心
        final MagicSurface s = new MagicSurface(mPageViewContainer)
                .setGrid(pageAnimRowCount(), pageAnimColCount())
                .drawGrid(false);
        if (updater instanceof MagicSurfaceMatrixUpdater) {
            s.setMatrixUpdater((MagicSurfaceMatrixUpdater) updater);
        } else {
            s.setModelUpdater((MagicSurfaceModelUpdater) updater);
        }
        mPageSurfaceView.setVisibility(View.VISIBLE);
        mPageSurfaceView.render(s);
        return true;
    }

    private boolean showWithMultiSurface(MagicMultiSurfaceUpdater updater) {
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mPageViewContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mPageViewContainer.setVisibility(View.VISIBLE);
                mPageSurfaceView.setVisibility(View.GONE);
                mPageSurfaceView.release();
                onPageAnimEnd();
            }
        });
        final MagicMultiSurface s = new MagicMultiSurface(mPageViewContainer, pageAnimRowCount(), pageAnimColCount());
        s.setUpdater(updater);
        mPageSurfaceView.setVisibility(View.VISIBLE);
        mPageSurfaceView.render(s);
        return true;
    }

    private boolean hide() {
        MagicUpdater updater = getPageUpdater(true);
        if (updater != null) {
            return hideWithSurface(updater);
        }

        MagicMultiSurfaceUpdater multiUpdater = getPageMultiUpdater(true);
        if (multiUpdater != null) {
            return hideWithMultiSurface(multiUpdater);
        }
        return false;
    }

    private boolean hideWithSurface(MagicUpdater updater) {
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mPageViewContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mPageSurfaceView.setVisibility(View.GONE);
                mPageSurfaceView.release();
                finish();
            }
        });
        MagicSurface s = new MagicSurface(mPageViewContainer)
                .setGrid(pageAnimRowCount(), pageAnimColCount())
                .drawGrid(false);
        if (updater instanceof MagicSurfaceMatrixUpdater) {
            s.setMatrixUpdater((MagicSurfaceMatrixUpdater) updater);
        } else {
            s.setModelUpdater((MagicSurfaceModelUpdater) updater);
        }
        mPageSurfaceView.setVisibility(View.VISIBLE);
        mPageSurfaceView.render(s);
        return true;
    }

    private boolean hideWithMultiSurface(MagicMultiSurfaceUpdater updater) {
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mPageViewContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mPageSurfaceView.setVisibility(View.GONE);
                mPageSurfaceView.release();
                finish();
            }
        });
        final MagicMultiSurface s = new MagicMultiSurface(mPageViewContainer, pageAnimRowCount(), pageAnimColCount());
        s.setUpdater(updater);
        mPageSurfaceView.setVisibility(View.VISIBLE);
        mPageSurfaceView.render(s);
        return true;
    }
}
