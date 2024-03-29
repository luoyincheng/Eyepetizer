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

package com.yincheng.eyepetizer.widgets.chart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yincheng.eyepetizer.provider.models.Checkmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In-memory implementation of {@link CheckmarkList}.
 */
public class MemoryCheckmarkList extends CheckmarkList {
    ArrayList<Checkmark> list;

    public MemoryCheckmarkList(Habit habit) {
        super(habit);
        list = new ArrayList<>();
    }

    @Override
    public void add(List<Checkmark> checkmarks) {
        list.addAll(checkmarks);
        Collections.sort(list,
                (c1, c2) -> c2.getTimestamp().compare(c1.getTimestamp()));
    }

    @NonNull
    @Override
    public List<Checkmark> getByInterval(Timestamp fromTimestamp, Timestamp toTimestamp) {
        compute();

        Timestamp newestComputed = new Timestamp(0);
        Timestamp oldestComputed = new Timestamp(0).plus(1000000);

        Checkmark newest = getNewestComputed();
        Checkmark oldest = getOldestComputed();
        if (newest != null) newestComputed = newest.getTimestamp();
        if (oldest != null) oldestComputed = oldest.getTimestamp();

        List<Checkmark> filtered = new ArrayList<>(
                Math.max(0, oldestComputed.daysUntil(newestComputed) + 1));

        for (int i = 0; i <= fromTimestamp.daysUntil(toTimestamp); i++) {
            Timestamp t = toTimestamp.minus(i);
            if (t.isNewerThan(newestComputed) || t.isOlderThan(oldestComputed))
                filtered.add(new Checkmark(t, Checkmark.UNCHECKED));
            else
                filtered.add(list.get(t.daysUntil(newestComputed)));
        }

        return filtered;
    }

    @Override
    public void invalidateNewerThan(Timestamp timestamp) {
        list.clear();
        observable.notifyListeners();
    }

    @Nullable
    @Override
    protected Checkmark getNewestComputed() {
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Nullable
    @Override
    protected Checkmark getOldestComputed() {
        if (list.isEmpty()) return null;
        return list.get(list.size() - 1);
    }
}
