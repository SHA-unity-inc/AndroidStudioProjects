package com.example.artemshloma5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Vector<MegaClass> realClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddNewMegaClass("Apple", new Vector<String>(Arrays.asList("Grenny Smith", "Pupa Lup", "Escho Odin Shloma")), 0);
        AddNewMegaClass("Bananas", new Vector<String>(Arrays.asList("Africa", "Aziya", "Jew Banana")), 1);
        AddNewMegaClass("Animes", new Vector<String>(Arrays.asList("Made in Abyss", "Jojo", "Boku No")), 2);
    }

    private void CreateListOfMegaClass(){
        main_resycler
    }

    private void AddNewMegaClass(String name, Vector<String> names, int type){
        Vector<MicroClass> mc = new Vector<MicroClass>();
        for (int i = 0; i < names.size(); i++){
            mc.add(new MicroClass(names.get(i)));
        }
        realClasses.add(new MegaClass(name, mc, type));
    }
}