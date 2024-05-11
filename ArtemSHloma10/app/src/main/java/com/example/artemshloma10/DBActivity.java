package com.example.artemshloma10;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.sql.*;
import java.util.Random;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dbactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/androidDB";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void createUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String userName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();
        String email = generateRandomEmail();
        String phoneNumber = generateRandomPhoneNumber();
        String address = generateRandomAddress();
        int age = generateRandomAge();
        String gender = generateRandomGender();
        Date registrationDate = new Date(System.currentTimeMillis());
        createUserData(id, userName, email, phoneNumber, address, age, gender, registrationDate);
    }

    public void getUserData(View view) {
        getUserData();
    }

    public void deleteUserData(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        deleteUserData(id);
    }

    public void changeUserNameById(View view) {
        String id = ((EditText)findViewById(R.id.userNumberInput)).getText().toString();
        String newUserName = ((EditText)findViewById(R.id.userNameInput)).getText().toString();
        changeUserNameById(id, newUserName);
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
        return new Random().nextInt(80) + 18; // Генерация возраста от 18 до 97 лет
    }

    private String generateRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[new Random().nextInt(genders.length)];
    }

    public static void createUserData(String id, String userName, String email, String phoneNumber, String address, int age, String gender, Date registrationDate) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_data (id, user_name, email, phone_number, address, age, gender, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, id);
            stmt.setString(2, userName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, address);
            stmt.setInt(6, age);
            stmt.setString(7, gender);
            stmt.setDate(8, registrationDate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserData() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user_data")) {
            StringBuilder userDataText = new StringBuilder();
            while (rs.next()) {
                userDataText.append("User ID: ").append(rs.getString("id")).append("\n");
                userDataText.append("Username: ").append(rs.getString("user_name")).append("\n");
                userDataText.append("Email: ").append(rs.getString("email")).append("\n");
                userDataText.append("Phone Number: ").append(rs.getString("phone_number")).append("\n");
                userDataText.append("Address: ").append(rs.getString("address")).append("\n");
                userDataText.append("Age: ").append(rs.getInt("age")).append("\n");
                userDataText.append("Gender: ").append(rs.getString("gender")).append("\n");
                userDataText.append("Registration Date: ").append(rs.getDate("registration_date")).append("\n\n");
            }
            ((TextView)findViewById(R.id.currentPrefs)).setText(userDataText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserData(String id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_data WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeUserNameById(String id, String newUserName) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE user_data SET user_name = ? WHERE id = ?")) {
            stmt.setString(1, newUserName);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
