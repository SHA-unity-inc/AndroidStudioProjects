package com.example.artemshloma8;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

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

    public void loadImageFromUrl(View view) {
        String url = "https://random.dog/woof.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DogImage dogImage = gson.fromJson(response.toString(), DogImage.class);
                        ImageView imageView = findViewById(R.id.imageView);
                        Glide.with(getApplicationContext()).load(dogImage.getUrl()).into(imageView);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}