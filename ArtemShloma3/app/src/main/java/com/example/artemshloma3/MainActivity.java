package com.example.artemshloma3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String buttonOneText = getResources().getString(R.string.button_one);
        String buttonTwoText = getResources().getString(R.string.button_two);
        String buttonThreeText = getResources().getString(R.string.button_three);

        Button relativeLayoutBtn = findViewById(R.id.relativeLayoutBtn);
        Button constraintLayoutBtn = findViewById(R.id.constraintLayoutBtn);
        Button frameLayoutBtn = findViewById(R.id.frameLayoutBtn);

        relativeLayoutBtn.setText(buttonOneText);
        constraintLayoutBtn.setText(buttonTwoText);
        frameLayoutBtn.setText(buttonThreeText);



        editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("text")) {
            String text = intent.getStringExtra("text");
            editText.setText(text);
        }

    }

    public void onClick(View v) {
        String text = editText.getText().toString();
        TextHolder textData = new TextHolder(text);

        Intent intent = new Intent();
        if (v.getId() == R.id.relativeLayoutBtn) {
            intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
        } else if (v.getId() == R.id.constraintLayoutBtn) {
            intent = new Intent(MainActivity.this, ConstraintLayoutActivity.class);
        } else if (v.getId() == R.id.frameLayoutBtn) {
            intent = new Intent(MainActivity.this, FrameLayoutActivity.class);
        }

        intent.putExtra("textData", textData);
        startActivity(intent);
    }
}