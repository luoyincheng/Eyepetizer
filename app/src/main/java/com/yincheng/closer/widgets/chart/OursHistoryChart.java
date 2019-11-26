package com.yincheng.closer.widgets.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yincheng.closer.R;
import com.yincheng.closer.app.CloserApplication;
import com.yincheng.closer.helpers.PaletteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OursHistoryChart extends HabitCard {

    @BindView(R.id.historyChart)
    HistoryChart chart;

    @BindView(R.id.title)
    TextView title;
    @NonNull
    private Controller controller;
    @Nullable
    private TaskRunner taskRunner;

    public OursHistoryChart(Context context) {
        super(context);
        init();

    }

    public OursHistoryChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    @OnClick(R.id.edit)
    public void onClickEditButton() {
        controller.onEditHistoryButtonClick();
    }

    public void setController(@NonNull Controller controller) {
        this.controller = controller;
    }

    @Override
    protected void refreshData() {
        if (taskRunner == null) return;
        taskRunner.execute(new RefreshTask(getHabit()));
    }

    private void initEditMode() {
        int color = PaletteUtils.getAndroidTestColor(1);
        title.setTextColor(color);
        chart.setColor(color);
        chart.populateWithRandomData();
    }

    private void init() {
        inflate(getContext(), R.layout.show_habit_history, this);
        ButterKnife.bind(this);

        Context appContext = getContext().getApplicationContext();
        if (appContext instanceof CloserApplication) {
            CloserApplication app = (CloserApplication) appContext;
//            taskRunner = app.getComponent().getTaskRunner();
        }

        controller = new Controller() {
        };
        if (isInEditMode()) initEditMode();
    }

    public interface Controller {
        default void onEditHistoryButtonClick() {
        }
    }

    private class RefreshTask implements Task {
        private final Habit habit;

        public RefreshTask(Habit habit) {
            this.habit = habit;
        }

        @Override
        public void doInBackground() {
            int checkmarks[] = habit.getCheckmarks().getAllValues();
            chart.setCheckmarks(checkmarks);
        }

        @Override
        public void onPreExecute() {
            int color = PaletteUtils.getColor(getContext(), habit.getColor());
            title.setTextColor(color);
            chart.setColor(color);
            if (habit.isNumerical()) {
                chart.setTarget((int) (habit.getTargetValue() * 1000));
                chart.setNumerical(true);
            }
        }
    }
}
