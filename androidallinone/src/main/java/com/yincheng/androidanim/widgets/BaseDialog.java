package com.yincheng.androidanim.widgets;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }
//    private View dialogView;
//    @LayoutRes
//    private int layoutId = R.layout.dialog_result;
//    @AnimRes
//    private int animRes = R.anim.anim_dialog_in;
//
//    private float aspectRatio;
//
//    public BaseDialog(@NonNull DialogBuilder dialogBuilder) {
//        this(dialogBuilder, 0);
//        init(dialogBuilder);
//    }
//
//    public BaseDialog(@NonNull DialogBuilder dialogBuilder, int themeResId) {
//        super(dialogBuilder.mContext, themeResId);
//        init(dialogBuilder);
//    }
//
//    protected BaseDialog(@NonNull DialogBuilder dialogBuilder, boolean cancelable, @Nullable
//            OnCancelListener cancelListener) {
//        super(dialogBuilder.mContext, cancelable, cancelListener);
//        init(dialogBuilder);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(layoutId);
//    }
//
//    protected void init(DialogBuilder dialogBuilder) {
//        dialogView = findViewById(android.R.id.content);
//        this.aspectRatio = dialogBuilder.aspectRatio;
//        //init things here
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        initWindow(aspectRatio);
//        dialogView.startAnimation(loadAnimation(this.getContext(), animRes));
//    }
//
//    public void initWindow(double aspectRatio) {
//        Window dialogWindow = getWindow();
////      WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
//        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
////      layoutParams.width = (int) (displayMetrics.widthPixels * 0.9);
////      layoutParams.height = (int) (layoutParams.width * aspectRatio);
//        dialogWindow.setLayout((int) (displayMetrics.widthPixels * 0.9),
//                (int) (displayMetrics.widthPixels * 0.9 * aspectRatio));
////      dialogWindow.setAttributes(layoutParams);
//        // TODO: 2018/6/9     0.9 应该是计算出来的，如果窗口有动画，就会影响动画的展示
//        // TODO: 2018/6/19    因此应该先解析动画中的xml文件，对缩放动画检测是否有大小是否有超过原始动画，然后为其设置margin
//    }
//
//    public static class DialogBuilder {
//        private Context mContext;
//        @LayoutRes
//        private int mLayoutId;
//        private FrameLayout.LayoutParams mLayoutParams;
//        private String title;
//        private float aspectRatio;
//        @AnimRes
//        private int animRes;
//
//        public DialogBuilder(Context context) {
//            this.mContext = context;
//        }
//
//        public DialogBuilder withAspectRatio(float aspectRatio) {
//            this.aspectRatio = aspectRatio;
//            return this;
//        }
//
//        public DialogBuilder withLayout(@LayoutRes int layoutId) {
//            this.mLayoutId = layoutId;
//            return this;
//        }
//
//        public DialogBuilder withLayoutParams(FrameLayout.LayoutParams layoutParams) {
//            this.mLayoutParams = layoutParams;
//            return this;
//        }
//
//        public DialogBuilder withTitle(String title) {
//            this.title = title;
//            return this;
//        }
//
//        public DialogBuilder withAnimation(@AnimRes int animRes) {
//            this.animRes = animRes;
//            return this;
//        }
//
//        public BaseDialog Build() {
//            return new BaseDialog(this, R.style.result_dialog);
//        }
//    }
}