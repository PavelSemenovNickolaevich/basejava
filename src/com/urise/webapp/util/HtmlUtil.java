package com.urise.webapp.util;

import com.urise.webapp.model.Organization;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Organization.Experience experience) {
        return DateUtil.format(experience.getBeginDate()) + " - " + DateUtil.format(experience.getEndDate());
    }
}
