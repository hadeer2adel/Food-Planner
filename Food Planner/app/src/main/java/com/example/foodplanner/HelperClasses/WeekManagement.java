package com.example.foodplanner.HelperClasses;

import android.content.Context;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.foodplanner.Models.UserDTO;
import com.example.foodplanner.View.MyWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeekManagement {
    public static List<String> getInitDays() {
        List<String> days = new ArrayList<>();
        String id = UserDTO.getUser().getId();
        days.add("Saturday");
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        return days;
    }

    public static void isNewWeek(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (initialDelay < 0) {
            initialDelay += TimeUnit.DAYS.toMillis(7);
        }

        PeriodicWorkRequest.Builder myWorkBuilder =
                new PeriodicWorkRequest.Builder(MyWorker.class, 7, TimeUnit.DAYS)
                        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS);

        WorkManager.getInstance(context).enqueue(myWorkBuilder.build());
    }
}
