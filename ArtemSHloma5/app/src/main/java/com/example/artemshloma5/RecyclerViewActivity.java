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

import java.util.Vector;

public class RecyclerViewActivity extends AppCompatActivity {
    Vector<MegaClass> realClasses;
    int nowClass;

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
            realClasses = (Vector<MegaClass>) intent.getSerializableExtra("rc");
            nowClass = intent.getIntExtra("nowClass", -1);
        }

        CreateListOfMicroClass();
    }

    private void CreateListOfMicroClass(){

        RecyclerView recyclerView = findViewById(R.id.main_resycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MegaClassAdapter adapter = new MegaClassAdapter(realClasses);
        recyclerView.setAdapter(adapter);
        recyclerView.bringToFront();
    }
}