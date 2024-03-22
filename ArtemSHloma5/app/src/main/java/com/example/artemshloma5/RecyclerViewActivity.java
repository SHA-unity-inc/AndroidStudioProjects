package com.example.artemshloma5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.io.Serializable;

public class RecyclerViewActivity extends AppCompatActivity {
    ArrayList<MegaClass> realClasses;
    int nowClass;
    ArrayList<Integer> imagesArray;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
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

        RecyclerView recyclerView = findViewById(R.id.recycler_two);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MicroClassAdapterRecycler adapter = new MicroClassAdapterRecycler(realClasses.get(nowClass).podClass);
        recyclerView.setAdapter(adapter);
        recyclerView.bringToFront();
    }

    public void AddNewItem(View view){
        TextInputEditText inputEditText = findViewById(R.id.textInputEditText);
        String text = String.valueOf(inputEditText.getText());
        realClasses.get(nowClass).podClass.add(new MicroClass(text, imagesArray.get(random.nextInt(imagesArray.size()))));

        CreateListOfMicroClass();
    }
}