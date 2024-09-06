/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.components;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author lth7p
 */
public class DateUtils {

    public static String getTimeRemainingMessage(Date now, Date startTime, Date endTime) {
        if (now.before(startTime)) {
            return "Thời gian check-in còn lại: " + formatDuration(now, startTime);
        } else if (now.after(startTime) && now.before(endTime)) {
            return "Thời gian đỗ còn lại: " + formatDuration(now, endTime);
        } else {
            return "Thời gian đỗ kết thúc";
        }
    }

    private static String formatDuration(Date from, Date to) {
        long duration = to.getTime() - from.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        return String.format("%02d giờ %02d phút %02d giây", hours, minutes, seconds);
    }
}
