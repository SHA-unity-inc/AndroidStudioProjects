package com.example.artemshloma10;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

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
        this.sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        getUserData();
    }


    public void createUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String userName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();

        try {
            String userDataString = sharedPreferences.getString("userData", "[]");
            JSONArray userDataArray = new JSONArray(userDataString);

            for (int i = 0; i < userDataArray.length(); i++) {
                JSONObject userData = userDataArray.getJSONObject(i);
                if (userData.getString("id").equals(id)) {
                    Toast.makeText(this, "ID уже существует", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            JSONObject newUserData = new JSONObject();
            newUserData.put("id", id);
            newUserData.put("userName", userName);

            userDataArray.put(newUserData);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userData", userDataArray.toString());
            editor.apply();

            getUserData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getUserData() {
        try {
            String userDataString = sharedPreferences.getString("userData", "[]");
            JSONArray userDataArray = new JSONArray(userDataString);

            StringBuilder userDataText = new StringBuilder();
            for (int i = 0; i < userDataArray.length(); i++) {
                JSONObject userData = userDataArray.getJSONObject(i);
                userDataText.append(userData.getString("userName")).append(" ").append(userData.getString("id")).append("\n");
            }

            ((TextView)findViewById(R.id.currentPrefs)).setText(userDataText.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String userName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();

        try {
            String userDataString = sharedPreferences.getString("userData", "[]");
            JSONArray userDataArray = new JSONArray(userDataString);

            for (int i = 0; i < userDataArray.length(); i++) {
                JSONObject userData = userDataArray.getJSONObject(i);
                if (userData.getString("id").equals(id) && userData.getString("userName").equals(userName)) {
                    userDataArray.remove(i);
                    break;
                }
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userData", userDataArray.toString());
            editor.apply();

            getUserData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void changeUserNameById(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String newUserName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();

        try {
            String userDataString = sharedPreferences.getString("userData", "[]");
            JSONArray userDataArray = new JSONArray(userDataString);

            for (int i = 0; i < userDataArray.length(); i++) {
                JSONObject userData = userDataArray.getJSONObject(i);
                if (userData.getString("id").equals(id)) {
                    userData.put("userName", newUserName);
                    break;
                }
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userData", userDataArray.toString());
            editor.apply();

            getUserData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void GoToDB(View view){
        Intent intent = new Intent(this, DBActivity.class);
        startActivity(intent);
    }
}