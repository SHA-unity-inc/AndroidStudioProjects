package com.example.artemshloma12;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAuthor;
    Button buttonAdd, buttonLoad;
    TextView textViewBooks; // Добавлено поле для вывода книг
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewBooks = findViewById(R.id.textViewBooks); // Находим TextView

        buttonAdd.setOnClickListener(view -> {
            String title = editTextName.getText().toString();
            String author = editTextAuthor.getText().toString();
            addBook(title, author);
        });
    }

    private void addBook(String title, String author) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        Uri contentUri = Uri.parse("content://com.example.app.provider/books"); // Используем Uri второго приложения
        getContentResolver().insert(contentUri, values);
        Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
    }
}
