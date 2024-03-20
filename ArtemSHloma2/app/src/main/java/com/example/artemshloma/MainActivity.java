package com.example.artemshloma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onLog", "Activity is Created");

        // Програмный способ
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivityToSecond();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onLog", "Activity is Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onLog", "Activity is Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onLog", "Activity is Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onLog", "Activity is Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onLog", "Activity is Destroyed");
    }

    // Декларативный способ
    public void onButtonClick(View view) {
        ChangeActivityToSecond();
    }


    public void ChangeActivityToSecond(){
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextGroup = findViewById(R.id.editTextGroup);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextGrade = findViewById(R.id.editTextGrade);

        String name = editTextName.getText().toString();
        String group = editTextGroup.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());
        float grade = Float.parseFloat(editTextGrade.getText().toString());

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("group", group);
        intent.putExtra("age", age);
        intent.putExtra("grade", grade);
        startActivity(intent);
    }
}