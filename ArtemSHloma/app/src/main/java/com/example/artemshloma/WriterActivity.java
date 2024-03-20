package com.example.artemshloma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WriterActivity extends AppCompatActivity {
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer);

        EditText editText = findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    writeNewLine(editText);
                    return true;
                }
                return false;
            }
        });
    }

    public void writeNewLine(EditText editText){
        String text = editText.getText().toString();
        editText.setText("");

        n++;

        TextView textView = findViewById(R.id.textView);

        int sendUser = n % 2;
        textView.append("\n\n User" + sendUser + ": " + text);
    }
}