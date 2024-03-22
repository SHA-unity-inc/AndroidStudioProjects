package com.example.artemshloma5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<MegaClass> realClasses = new ArrayList<>();
    ArrayList<Integer> imagesArray = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        imagesArray.add(res.getIdentifier("apel_aja", "drawable", getPackageName()));
        imagesArray.add(res.getIdentifier("catt", "drawable", getPackageName()));
        imagesArray.add(res.getIdentifier("banana", "drawable", getPackageName()));

        AddNewMegaClass("Apple", new ArrayList<String>(Arrays.asList("Grenny Smith", "Pupa Lup", "Escho Odin Shloma")), 0, 0);
        AddNewMegaClass("Bananas", new ArrayList<String>(Arrays.asList("Africa", "Aziya", "Jew Banana")), 1, 2);
        AddNewMegaClass("Animes", new ArrayList<String>(Arrays.asList("Made in Abyss", "Jojo", "Boku No")), 2, 1);

        CreateListOfMegaClass();
    }

    public void ClickOnMegaClassButton(View view){
        Log.i("DEBUG LOG FROM C#", "BTN CLCK");

        if (view.getId() == R.id.standart_button) {
            LinearLayout linearLayout = (LinearLayout) view;
            TextView textView = linearLayout.findViewById(R.id.button_text);
            String text = textView.getText().toString();
            Log.i("DEBUG LOG FROM C#", "ТЕКСТ УКРАДЕН: " + text);

            int nowClass = FindNameInMegaClass(text);
            int type = realClasses.get(nowClass).type;
            GoToType(type, nowClass);
        }
    }

    private void GoToType(int type, int nowClass){
        switch (type){
            case 0:
                Intent intent = new Intent(this, RecyclerViewActivity.class);
                intent.putExtra("rc", realClasses);
                intent.putExtra("nowClass", nowClass);
                startActivity(intent);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                Log.e("DEBUG LOG FROM C#", "Тип не известен");
        }
    }

    private int FindNameInMegaClass(String txt){
        for(int i = 0; i < realClasses.size(); i++){
            if(Objects.equals(realClasses.get(i).name, txt)){
                return i;
            }
        }
        return -1;
    }

    private void CreateListOfMegaClass(){

        RecyclerView recyclerView = findViewById(R.id.main_resycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MegaClassAdapter adapter = new MegaClassAdapter(realClasses);
        recyclerView.setAdapter(adapter);
        recyclerView.bringToFront();
    }

    private void AddNewMegaClass(String name, ArrayList<String> names, int type, int k){
        ArrayList<MicroClass> mc = new ArrayList<MicroClass>();
        for (int i = 0; i < names.size(); i++){
            mc.add(new MicroClass(names.get(i), imagesArray.get(random.nextInt(imagesArray.size()))));
        }

        Log.i("DEBUG LOG FROM C#", String.valueOf(k));
        Log.i("DEBUG LOG FROM C#", String.valueOf(imagesArray.get(k)));
        realClasses.add(new MegaClass(name, mc, type, imagesArray.get(k)));
    }
}