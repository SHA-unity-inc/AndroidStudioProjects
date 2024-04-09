package com.example.artemshloma9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName, editTextFileContent;
    TextView exportTextFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextFileContent = findViewById(R.id.editTextFileContent);
        exportTextFileContent = findViewById(R.id.answerText);
    }

    public void createFile(View view) {
        String fileName = editTextFileName.getText().toString();
        String fileContent = editTextFileContent.getText().toString();
        fileContent += "\n";

        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(fileContent.getBytes());
            fos.close();

            Toast.makeText(MainActivity.this, "Файл создан", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(View view) {
        String fileName = editTextFileName.getText().toString();

        FileInputStream fis;
        try {
            fis = openFileInput(fileName);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {}
            fis.close();

            exportTextFileContent.setText(new String(input));

            Toast.makeText(MainActivity.this, "Файл прочитан", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            exportTextFileContent.setText("Файл не найден");

            e.printStackTrace();
        }
    }

    public void appendFile(View view) {
        String fileName = editTextFileName.getText().toString();
        String fileContent = editTextFileContent.getText().toString();
        fileContent += "\n";

        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, MODE_APPEND);
            fos.write(fileContent.getBytes());
            fos.close();

            Toast.makeText(MainActivity.this, "Информация добавлена в файл", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(View view) {
        String fileName = editTextFileName.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Удаление файла")
                .setMessage("Вы действительно хотите удалить этот файл?")
                .setPositiveButton("Да", (dialog, id) -> {
                    deleteFile(fileName);
                    Toast.makeText(MainActivity.this, "Файл удален", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Нет", (dialog, id) -> dialog.cancel())
                .create()
                .show();
    }
}