package com.example.artemshloma5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Random;

public class SpinnerActivity extends AppCompatActivity {
    ArrayList<MegaClass> realClasses;
    int nowClass;
    ArrayList<Integer> imagesArray;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();

        if (intent != null) {
            Object object = intent.getSerializableExtra("rc");
            if (object instanceof ArrayList) {
                realClasses = (ArrayList<MegaClass>) object;
            }else{
                Log.e("DEBUG LOG FROM C#", "Неправильный класс: " + object.getClass());
            }
            nowClass = intent.getIntExtra("nowClass", -1);
            imagesArray = intent.getIntegerArrayListExtra("images");
        }

        CreateListOfMicroClass();
    }

    private void CreateListOfMicroClass(){
        ArrayList<MicroClass> microClasses = realClasses.get(nowClass).podClass;

        Spinner spinner = findViewById(R.id.spinner);

        ArrayList<String> names = new ArrayList<>();
        for (MicroClass microClass : microClasses) {
            names.add(microClass.name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void AddNewItem(View view){
        TextInputEditText inputEditText = findViewById(R.id.textInputEditText);
        String text = String.valueOf(inputEditText.getText());
        realClasses.get(nowClass).podClass.add(new MicroClass(text, imagesArray.get(random.nextInt(imagesArray.size()))));

        CreateListOfMicroClass();
    }

    public void BackToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("rc", realClasses);
        intent.putExtra("images", imagesArray);
        startActivity(intent);
    }
}