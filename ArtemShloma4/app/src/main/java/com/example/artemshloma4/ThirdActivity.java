package com.example.artemshloma4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {
    String s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Intent intent = getIntent();

        NameSender names = (NameSender) intent.getSerializableExtra("names");
        if(names != null && names.name != null && names.surname != null){
            s1 = (names.name);
            s2 = (names.surname);
        }
    }

    public void ReturnToTwo(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("timeInfo", new TimeSender(
                ((EditText)findViewById(R.id.textViewDay)).getText().toString(),
                ((EditText)findViewById(R.id.textViewTime)).getText().toString(),
                ((EditText)findViewById(R.id.textViewComment)).getText().toString()
        ));
        intent.putExtra("names", new NameSender(
                s1,
                s2
        ));
        startActivity(intent);
    }
}