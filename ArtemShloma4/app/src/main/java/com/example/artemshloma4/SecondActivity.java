package com.example.artemshloma4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {

    String name1, name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewFName = findViewById(R.id.textViewFName);
        TextView textViewLName = findViewById(R.id.textViewLName);

        Intent intent = getIntent();

        NameSender names = (NameSender) intent.getSerializableExtra("names");
        if(names != null && names.name != null && names.surname != null){
            name1 = names.name;
            name2 = names.surname;
            textViewFName.setText(names.name);
            textViewLName.setText(names.surname);
            Log.i("names", "names");
        }

        TimeSender timeSender = (TimeSender) intent.getSerializableExtra("timeInfo");
        if (timeSender != null){
            ((TextView)findViewById(R.id.textView)).setText(
                    timeSender.day + "\n" + timeSender.time + "\n" + timeSender.comment);
            showToast();
            Log.i("names", "names");
        }
    }

    public void EnterData(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("item", (
                (EditText)findViewById(R.id.enterText)).getText().toString());
        intent.putExtra("names", new NameSender(name1, name2));
        startActivity(intent);
    }

    public void showToast() {
        String message = "Время занятия успешно передано!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}