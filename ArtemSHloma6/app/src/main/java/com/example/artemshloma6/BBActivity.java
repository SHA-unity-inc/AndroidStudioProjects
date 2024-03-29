package com.example.artemshloma6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomappbar.BottomAppBar;
import androidx.fragment.app.Fragment;

public class BBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomAppBar = findViewById(R.id.babn);
        if(bottomAppBar != null) {
            setSupportActionBar(bottomAppBar);

            bottomAppBar.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.bottom_bar_item1) {
                    updateFragment(new Fragment1());
                    return true;
                } else if (itemId == R.id.bottom_bar_item2) {
                    updateFragment(new Fragment2());
                    return true;
                }
                return false;
            });
        }
    }

    private void updateFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(fragment.getClass().getSimpleName());
        }
    }
}
