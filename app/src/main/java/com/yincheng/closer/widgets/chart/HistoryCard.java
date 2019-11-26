/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.yincheng.closer.widgets.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yincheng.closer.R;
import com.yincheng.closer.app.CloserApplication;
import com.yincheng.closer.helpers.ColorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryCard extends HabitCard {
    @BindView(R.id.historyChart)
    HistoryChart chart;

    @BindView(R.id.title)
    TextView title;

    @NonNull
    private Controller controller;

    @Nullable
    private TaskRunner taskRunner;

    public HistoryCard(Context context) {
        super(context);
        init();
    }

    public HistoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @OnClick(R.id.edit)
    public void onClickEditButton() {
        controller.onEditHistoryButtonClick();
    }

    public void setController(@NonNull Controller controller) {
        this.controller = controller;
        chart.setController(controller);
    }

    @Override
    protected void refreshData() {
        if (taskRunner == null) return;
        taskRunner.execute(new RefreshTask(getHabit()));
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

    private void initEditMode() {
        int color = ColorUtils.getAndroidTestColor(1);
        title.setTextColor(color);
        chart.setColor(color);
        chart.populateWithRandomData();
    }

    public interface Controller extends HistoryChart.Controller {
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
            int color = ColorUtils.getColor(getContext(), habit.getColor());
            title.setTextColor(color);
            chart.setColor(color);
        }
    }
}
