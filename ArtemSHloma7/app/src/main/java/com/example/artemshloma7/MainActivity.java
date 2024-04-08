package com.example.artemshloma7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

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

        Log.i("Debug C#", "A");
        Intent intent = new Intent(this, SHAService.class);
        startService(intent);
    }

    public  void OpenAlertDialog(View view){
        MainActivity cl = MainActivity.this;

        new AlertDialog.Builder(this)
                .setTitle("AlertDialog")
                .setMessage("AlertDialog - ВЫ ХОТИТЕ ТОЧНО УЙТИ ВЫ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String txt = "Вы продали душу";
                        ((TextView)findViewById(R.id.txt)).setText(txt);
                        Intent intent = new Intent(cl, AnotherActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    public  void OpenDatePickerDialog(View view){
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String txt = String.valueOf(year) + " year, " + String.valueOf(month) + " month, " + String.valueOf(dayOfMonth) + " day" ;
                ((TextView)findViewById(R.id.txt)).setText(txt);
            }
        }, 2023, 0, 1).show();
    }

    public void OpenTimePickerDialog(View view) {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String txt = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                ((TextView)findViewById(R.id.txt)).setText(txt);
            }
        }, 12, 0, true).show();
    }

    public void OpenCustomDialog(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Custom Dialog");

        Button dialogButton1 = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Ты выбрал да", Snackbar.LENGTH_SHORT)
                        .setAction("Выключить", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .show();
                dialog.dismiss();
            }
        });


        Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonNO);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Ты выбрал нет", Snackbar.LENGTH_SHORT)
                        .setAction("Выключить", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}