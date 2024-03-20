package com.example.artemshloma4_1;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;

public class BlankFragment extends AppCompatActivity {

    private View staticFragment;
    private View dynamicFragmentContainer;
    private View fragmentContainerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_fragment);

        staticFragment = findViewById(R.id.static_fragment);
        dynamicFragmentContainer = findViewById(R.id.dynamic_fragment_container);
        fragmentContainerView = findViewById(R.id.fragment_container_view);

        goToFragment1(null);
    }

    public void goToFragment1(View view) {
        staticFragment.setVisibility(View.VISIBLE);
        dynamicFragmentContainer.setVisibility(View.GONE);
        fragmentContainerView.setVisibility(View.GONE);
    }

    public void goToFragment2(View view) {
        staticFragment.setVisibility(View.GONE);
        dynamicFragmentContainer.setVisibility(View.VISIBLE);
        fragmentContainerView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dynamic_fragment_container, new Fragment2())
                .commit();
    }

    public void goToFragment3(View view) {
        staticFragment.setVisibility(View.GONE);
        dynamicFragmentContainer.setVisibility(View.GONE);
        fragmentContainerView.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new Fragment3())
                .commit();
    }
}
