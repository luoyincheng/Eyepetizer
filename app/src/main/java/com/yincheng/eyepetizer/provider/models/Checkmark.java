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

package com.yincheng.eyepetizer.provider.models;

import com.yincheng.eyepetizer.widgets.chart.Timestamp;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.yincheng.eyepetizer.helpers.StringUtils.defaultToStringStyle;

/**
 * A Checkmark represents the completion status of the habit for a given day.
 * <p>
 * While repetitions simply record that the habit was performed at a given date,
 * a checkmark provides more information, such as whether a repetition was
 * expected at that day or not.
 * <p>
 * Checkmarks are computed automatically from the list of repetitions.
 */
//@ThreadSafe
public final class Checkmark {
    /**
     * Indicates that there was a repetition at the timestamp.
     */
    public static final int CHECKED_EXPLICITLY = 2;

    /**
     * Indicates that there was no repetition at the timestamp, but one was not
     * expected in any case, due to the frequency of the habit.
     */
    public static final int CHECKED_IMPLICITLY = 1;

    /**
     * Indicates that there was no repetition at the timestamp, even though a
     * repetition was expected.
     */
    public static final int UNCHECKED = 0;

    private final Timestamp timestamp;

    /**
     * The value of the checkmark.
     * <p>
     * For boolean habits, this equals either UNCHECKED, CHECKED_EXPLICITLY,
     * or CHECKED_IMPLICITLY.
     * <p>
     * For numerical habits, this number is stored in thousandths. That
     * is, if the user enters value 1.50 on the app, it is stored as 1500.
     */
    private final int value;

    public Checkmark(Timestamp timestamp, int value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Checkmark checkmark = (Checkmark) o;

        return new EqualsBuilder()
                .append(timestamp, checkmark.timestamp)
                .append(value, checkmark.value)
                .isEquals();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(timestamp)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, defaultToStringStyle())
                .append("timestamp", timestamp)
                .append("value", value)
                .toString();
    }
}
