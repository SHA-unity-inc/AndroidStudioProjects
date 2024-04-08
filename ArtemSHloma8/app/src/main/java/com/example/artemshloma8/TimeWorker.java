package com.example.artemshloma8;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeWorker extends Worker {
    public TimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Random random = new Random();
        while (!isStopped()) {
            System.out.println(System.currentTimeMillis());
            try {
                int randomTime = random.nextInt(10) + 1;
                TimeUnit.SECONDS.sleep(randomTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }
}
