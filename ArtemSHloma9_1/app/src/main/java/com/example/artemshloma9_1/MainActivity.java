package com.example.artemshloma9_1;

import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileInputStream;
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

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void readFileLocal(String fileName){
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

    private void readFileGlobal(String fileName){
        FileInputStream fis;
        try {

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);

            fis = new FileInputStream(file);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {}
            fis.close();

            exportTextFileContent.setText(new String(input));

            Toast.makeText(MainActivity.this, "Файл прочитан глобально", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            exportTextFileContent.setText("Файл не найден");

            e.printStackTrace();
            Log.e("Test", e.toString());
        }

    }

    public void readFile(View view) {
        String fileName = editTextFileName.getText().toString();
        if (isExternalStorageReadable()) {
            Log.i("File Type", "Global");
            Toast.makeText(MainActivity.this, "Глобально", Toast.LENGTH_LONG).show();
            readFileGlobal(fileName);
        }else{
            Log.i("File Type", "Local");
            Toast.makeText(MainActivity.this, "Локально", Toast.LENGTH_LONG).show();
            readFileLocal(fileName);
        }
    }

    public void deleteFile(View view){
        String fileName = editTextFileName.getText().toString();
        if (isExternalStorageReadable()) {
            Log.i("File Type", "Global");
            deleteFileGlobal(fileName);
        }else{
            Log.i("File Type", "Local");
            deleteFileLocal(fileName);
        }
    }

    public void deleteFileGlobal(String fileName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);
        builder.setTitle("Удаление файла")
                .setMessage("Вы действительно хотите удалить этот файл?")
                .setPositiveButton("Да", (dialog, id) -> {
                    file.delete();
                    Toast.makeText(MainActivity.this, "Файл удален глобально", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Нет", (dialog, id) -> dialog.cancel())
                .create()
                .show();
    }

    public void deleteFileLocal(String fileName) {
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("EdTFN", editTextFileName.getText().toString());
        outState.putString("EdTFC", editTextFileContent.getText().toString());
        outState.putString("ExTFN", exportTextFileContent.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextFileName.setText(
                savedInstanceState.getString("EdTFN"));
        editTextFileContent.setText(
                savedInstanceState.getString("EdTFC"));
        exportTextFileContent.setText(
                savedInstanceState.getString("ExTFN"));
    }
}