package com.example.artemshloma12_1;

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
    Button buttonLoad;
    TextView textViewBooks; // Добавлено поле для вывода книг
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        buttonLoad = findViewById(R.id.buttonLoad);
        textViewBooks = findViewById(R.id.textViewBooks); // Находим TextView

        buttonLoad.setOnClickListener(view -> {
            loadBooks();
        });
    }

    private void loadBooks() {
        Uri contentUri = Uri.parse("content://com.example.app.provider/books"); // Uri второго приложения
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(contentUri, new String[]{"_id", "title", "author"}, null, null, "title ASC");
        if (cursor != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder(); // Для формирования строки с книгами
                int titleColumn = cursor.getColumnIndex("title");
                int authorColumn = cursor.getColumnIndex("author");
                while (cursor.moveToNext()) {
                    String title = cursor.getString(titleColumn);
                    String author = cursor.getString(authorColumn);
                    stringBuilder.append("Название: ").append(title).append(", Автор: ").append(author).append("\n"); // Формируем строку
                }
                textViewBooks.setText(stringBuilder.toString()); // Устанавливаем текст в TextView
            } finally {
                cursor.close();
            }
        }
    }
}