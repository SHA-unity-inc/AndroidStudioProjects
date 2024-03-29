package com.example.artemshloma6;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.open();
    }

    public void Home(View view){
        for (int i = 0; i < views.size(); i++){
            views.get(i).setVisibility(false);
        }
    }

    public void Notifications(View view){

    }

    public void Settings(View view){

    }
}