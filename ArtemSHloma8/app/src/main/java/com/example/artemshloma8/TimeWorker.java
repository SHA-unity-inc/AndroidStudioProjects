package com.example.artemshloma8;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeWorker extends Worker {
    public TimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Data inputData = getInputData();
        int timer = 0;

        while (!isStopped() && timer <= 15) {
            try {
                int time = 2;
                TimeUnit.SECONDS.sleep(time);
                String currentTime = sdf.format(new Date());
                System.out.println(currentTime + " " + inputData.getString("name").toString());
                timer += time;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }
}