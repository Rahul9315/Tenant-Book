package com.example.tenantbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername , editTextPassword;
    Button buttonLogin, buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // Login logic
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                //  login Details here
                if (LoginUser(username,password)) {
                    // Login successful
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Login failed
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }

            }

        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create account logic here
                Toast.makeText(MainActivity.this, "Create Account clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean LoginUser(String username, String password) {
        DbHelper dbHelper = new DbHelper(this);

        // Assuming you have a SQLiteDatabase instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sqlQuery = "SELECT " + DbHelper.USERDETAILS_FULLNAME +
                " FROM " + DbHelper.TABLE_USER_DETAILS +
                " WHERE " + DbHelper.USERDETAILS_USERNAME + "=? AND " + DbHelper.USERDETAILS_PASSWORD + "=?";

        String[] selectionArgs = {username, password};

        try {
            Cursor cursor = db.rawQuery(sqlQuery, selectionArgs);

            // Check if the cursor is not null and has at least one row
            boolean loginSuccessful = cursor != null && cursor.moveToFirst();

            // Close the cursor and database
            if (cursor != null) {
                cursor.close();
            }
            db.close();

            return loginSuccessful;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            e.printStackTrace();
            db.close();
            return false;
        }
    }

}