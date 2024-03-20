package com.example.artemshloma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String group = intent.getStringExtra("group");
            int age = intent.getIntExtra("age", 0);
            float grade = intent.getFloatExtra("grade", 0.0f);

            // Далее вы можете использовать полученные данные по вашему усмотрению, например, отобразить их в TextView
            TextView textViewData = findViewById(R.id.textViewData);
            String data = "ФИО: " + name + "\n" +
                    "Номер группы: " + group + "\n" +
                    "Возраст: " + age + "\n" +
                    "Оценка за практику: " + grade;
            textViewData.setText(data);
        }
    }
}