package com.example.artemshloma8;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    OneTimeWorkRequest workRequest, workRequest2, workRequest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void ReCreateWorker(){

        Data data1 = new Data.Builder()
                .putString("name", "A_TW")
                .build();
        workRequest = new OneTimeWorkRequest.Builder(TimeWorker.class)
                .setInputData(data1)
                .build();

        Data data2 = new Data.Builder()
                .putString("name", "B_TW")
                .build();
        workRequest2 = new OneTimeWorkRequest.Builder(TimeWorker.class)
                .setInputData(data2)
                .build();

        Data data3 = new Data.Builder()
                .putString("name", "C_TW")
                .build();
        workRequest3 = new OneTimeWorkRequest.Builder(TimeWorker.class)
                .setInputData(data3)
                .build();
    }

    public void ParallelQuest(View view){
        ReCreateWorker();

        // Запуск задач параллельно
        List<OneTimeWorkRequest> listParallel = new ArrayList<>();
        listParallel.add(workRequest); listParallel.add(workRequest2); listParallel.add(workRequest3);

        WorkManager.getInstance(this).enqueue(listParallel);

    }

    public void BeginThenQuest(View view){
        ReCreateWorker();

        // Запуск задач последовательно
        WorkManager.getInstance(this).beginWith(workRequest).then(workRequest2).enqueue();

    }
}