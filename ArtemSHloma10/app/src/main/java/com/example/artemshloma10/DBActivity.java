package com.example.artemshloma10;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DBActivity extends AppCompatActivity {

    private UserDataHelper userDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        userDataHelper = new UserDataHelper(this);
    }

    public void createUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String userName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();
        String email = generateRandomEmail();
        String phoneNumber = generateRandomPhoneNumber();
        String address = generateRandomAddress();
        int age = generateRandomAge();
        String gender = generateRandomGender();
        String registrationDate = String.valueOf(System.currentTimeMillis());

        SQLiteDatabase db = userDataHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("user_name", userName);
        values.put("email", email);
        values.put("phone_number", phoneNumber);
        values.put("address", address);
        values.put("age", age);
        values.put("gender", gender);
        values.put("registration_date", registrationDate);

        db.insert("user_data", null, values);
        db.close();
    }

    public void getUserData(View view) {
        SQLiteDatabase db = userDataHelper.getReadableDatabase();
        Cursor cursor = db.query("user_data", null, null, null, null, null, null);

        StringBuilder userDataText = new StringBuilder();
        while (cursor.moveToNext()) {
            userDataText.append("User ID: ").append(cursor.getString(cursor.getColumnIndex("id"))).append("\n");
            userDataText.append("Username: ").append(cursor.getString(cursor.getColumnIndex("user_name"))).append("\n");
            userDataText.append("Email: ").append(cursor.getString(cursor.getColumnIndex("email"))).append("\n");
            userDataText.append("Phone Number: ").append(cursor.getString(cursor.getColumnIndex("phone_number"))).append("\n");
            userDataText.append("Address: ").append(cursor.getString(cursor.getColumnIndex("address"))).append("\n");
            userDataText.append("Age: ").append(cursor.getInt(cursor.getColumnIndex("age"))).append("\n");
            userDataText.append("Gender: ").append(cursor.getString(cursor.getColumnIndex("gender"))).append("\n");
            userDataText.append("Registration Date: ").append(cursor.getString(cursor.getColumnIndex("registration_date"))).append("\n\n");
        }
        cursor.close();
        db.close();

        ((TextView)findViewById(R.id.currentPrefs)).setText(userDataText);
    }

    public void deleteUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();

        SQLiteDatabase db = userDataHelper.getWritableDatabase();
        db.delete("user_data", "id = ?", new String[]{id});
        db.close();
    }

    public void changeUserNameById(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String newUserName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();

        SQLiteDatabase db = userDataHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", newUserName);
        db.update("user_data", values, "id = ?", new String[]{id});
        db.close();
    }

    // Методы для генерации случайных значений
    private String generateRandomEmail() {
        return "user" + new Random().nextInt(1000) + "@example.com";
    }

    private String generateRandomPhoneNumber() {
        return "+1 555-" + String.format("%04d", new Random().nextInt(10000));
    }

    private String generateRandomAddress() {
        String[] streets = {"First", "Second", "Third", "Fourth", "Fifth"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        return streets[new Random().nextInt(streets.length)] + " Street, " + cities[new Random().nextInt(cities.length)];
    }

    private int generateRandomAge() {
        return new Random().nextInt(80) + 20;
    }

    private String generateRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[new Random().nextInt(genders.length)];
    }
}
