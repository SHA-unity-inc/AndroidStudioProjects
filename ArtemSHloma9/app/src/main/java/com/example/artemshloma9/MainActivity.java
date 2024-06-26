package com.example.artemshloma9;

import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.os.Environment;
import android.util.Log;

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
        return false;
    }

    private void createFileLocal(String fileName, String fileContent){
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

    private void createFileGlobal(String fileName, String fileContent){
        Log.i("Test", "PrePreA");
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);

        Log.i("Test", file.toString());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            Log.i("Test", "A");
            fos.write(fileContent.getBytes());

            Log.i("Test", "B");
            Toast.makeText(MainActivity.this, "Файл создан глобально", Toast.LENGTH_LONG).show();

            Log.i("Test", "C");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Test", e.toString());
        }
    }

    public void createFile(View view) {
        String fileName = editTextFileName.getText().toString();
        String fileContent = editTextFileContent.getText().toString();
        fileContent += "\n";
        if (isExternalStorageReadable()) {
            Log.i("File Type", "Global");
            createFileGlobal(fileName, fileContent);
        }else{
            Log.i("File Type", "Local");
            createFileLocal(fileName, fileContent);
        }
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
        }

    }

    public void readFile(View view) {
        String fileName = editTextFileName.getText().toString();
        if (isExternalStorageReadable()) {
            Log.i("File Type", "Global");
            readFileGlobal(fileName);
        }else{
            Log.i("File Type", "Local");
            readFileLocal(fileName);
        }
    }

    public void appendFile(View view){
        String fileName = editTextFileName.getText().toString();
        if (isExternalStorageReadable()) {
            Log.i("File Type", "Global");
            appendFileGlobal(fileName);
        }else{
            Log.i("File Type", "Local");
            appendFileLocal(fileName);
        }
    }

    public void appendFileLocal(String fileName) {
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

    public void appendFileGlobal(String fileName) {
        String fileContent = editTextFileContent.getText().toString();
        fileContent += "\n";

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file, true); // true for append mode
            fos.write(fileContent.getBytes());
            fos.close();

            Toast.makeText(MainActivity.this, "Информация добавлена в файл глобально", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
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