/*
 * Copyright (C) 2017 Álinson Santos Xavier <isoron@gmail.com>
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

package com.yincheng.closer.helpers;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static StandardToStringStyle toStringStyle = null;

    public static String getRandomId() {
        return new BigInteger(260, new Random()).toString(32).substring(0, 32);
    }

    public static ToStringStyle defaultToStringStyle() {
        if (toStringStyle == null) {
            toStringStyle = new StandardToStringStyle();
            toStringStyle.setFieldSeparator(", ");
            toStringStyle.setUseClassName(false);
            toStringStyle.setUseIdentityHashCode(false);
            toStringStyle.setContentStart("{");
            toStringStyle.setContentEnd("}");
            toStringStyle.setFieldNameValueSeparator(": ");
            toStringStyle.setArrayStart("[");
            toStringStyle.setArrayEnd("]");
        }

        return toStringStyle;
    }

    public static boolean isChineseWithNoPunctuation(CharSequence charSequence) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(charSequence);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    public static boolean isChineseWithPunctuation(CharSequence charSequence) {
        boolean isChinese = true;
        for (int i = 0; i < charSequence.length(); i++) {
            if (!isChineseChar(charSequence.charAt(i))) isChinese = false;
        }
        return isChinese;
    }

    //使用UnicodeBlock方法判断
    private static boolean isChineseByBlock(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
    }

    // 根据UnicodeBlock方法判断中文标点符号
    private static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS;
    }

    // 完整的判断中文汉字和符号
    private static boolean isChineseChar(char c) {
        return isChineseByBlock(c) || isChinesePunctuation(c);
    }

    /**
     * 根据是否是中文字符返回不同的字符所占长度
     *
     * @param c
     * @return 中文或中文标点长度为2
     */
    public static int getCharLength(char c) {
        return isChineseChar(c) ? 2 : 1;
    }

    /**
     * 按照中文或中文标点长度为2，计算字符串长度
     *
     * @param text
     * @return
     */
    public static int getStringLength(String text) {
        int len = 0;
        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);
            if (isChineseChar(c)) {
                len = len + 2;
            } else {
                len = len + 1;
            }
        }
        return len;
    }
}
